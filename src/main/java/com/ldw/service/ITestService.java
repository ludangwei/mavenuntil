package com.ldw.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
public interface ITestService {

    String select(String sql);
    void  insertBySql(String sql);
    List<Map<String,Object>> selectData(String sql);
}
