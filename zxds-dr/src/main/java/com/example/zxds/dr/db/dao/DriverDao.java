package com.example.zxds.dr.db.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface DriverDao {
    /**
     * 根据driverId/openId查询司机数量
     * @param params
     * @return 大于0表示司机已注册
     */
    long hasDriver(Map<String, Object> params);

    /**
     * 注册新司机
     * @param params
     * @return
     */
    int registerNewDriver(Map<String, Object> params);
}




