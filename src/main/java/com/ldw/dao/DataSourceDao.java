package com.ldw.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface DataSourceDao {
    String select(String sql);

    void  insertBySql(String sql);

    List<Map<String,Object>> selectData(String sql);
}
