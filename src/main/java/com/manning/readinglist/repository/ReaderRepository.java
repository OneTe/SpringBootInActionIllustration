package com.manning.readinglist.repository;

import com.manning.readinglist.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangcheng  on 2018/6/22.
 */
public interface ReaderRepository extends JpaRepository<Reader,String> {
}
