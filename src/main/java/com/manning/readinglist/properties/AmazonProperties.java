package com.manning.readinglist.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wangcheng  on 2018/6/23.
 */
@Component
//IDEA提示找不到"spring boot Configuration Annotation Proessor not found in classpath"，
// 参考：https://blog.csdn.net/expect521/article/details/77151094
@ConfigurationProperties("amazon")//注入带amazon前缀的属性
public class AmazonProperties {
    private String associateId;

    public String getAssociateId() {
        return associateId;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }
}
