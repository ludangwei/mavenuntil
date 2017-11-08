package com.ldw.service;

import com.ldw.dao.DataSourceDao;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class TestService implements ITestService{
    @Autowired
    private DataSourceDao dao;
    @Override
    public String select(String sql) {
        return dao.select(sql);
    }

//    @Transactional
    public void insertBySql(String sql){
        dao.insertBySql(sql);
        System.out.println("sql语句成功执行");
    }

    @Override
    public List<Map<String, Object>> selectData(String sql) {
        return dao.selectData(sql);
    }

}
