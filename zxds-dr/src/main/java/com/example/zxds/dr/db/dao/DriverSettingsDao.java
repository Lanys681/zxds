package com.example.zxds.dr.db.dao;

import com.example.zxds.dr.db.pojo.DriverSettingsEntity;

import java.util.Map;

public interface DriverSettingsDao {
    /**
     * 保存司机默认设置
     * @param dse
     * @return
     */
    int insertDriverSettings(DriverSettingsEntity dse);
}




