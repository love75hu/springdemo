package cn.mediinfo.springdemo.service;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientScopeService {

    @Query("select id from clientscope")
    List<String> Get();
}
