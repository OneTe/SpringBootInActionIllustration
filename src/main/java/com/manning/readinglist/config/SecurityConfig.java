package com.manning.readinglist.config;

import com.manning.readinglist.repository.ReaderRepository;
import com.manning.readinglist.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by wangcheng  on 2018/6/22.
 */
@Profile("development")//这里要求运行时激活development Profile，这样才能应用该配置，设置spring.profiles.active的属性就能激活，见application.yml配置
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private ReaderRepository readerRepository;

    @Autowired
    public SecurityConfig(ReaderRepository readerRepository){
        this.readerRepository = readerRepository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/").access("hasRole('READER')") //要求登录者有READER角色
                .antMatchers("/**").permitAll()    //其他所有请求路径向所有用户开放了访问权限
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")    //设置登录表单的路径
                .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(new UserDetailsService() {      //定义自定义UserDetailService
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return readerRepository.getOne(username);
            }
        });
    }

}
