package com.example.zxds.dr.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import com.example.zxds.common.exception.ZxdsException;
import com.example.zxds.common.util.IdUtil;
import com.example.zxds.common.util.MicroAppUtil;
import com.example.zxds.dr.db.dao.DriverDao;
import com.example.zxds.dr.db.dao.DriverSettingsDao;
import com.example.zxds.dr.db.dao.WalletDao;
import com.example.zxds.dr.db.pojo.DriverSettingsEntity;
import com.example.zxds.dr.db.pojo.WalletEntity;
import com.example.zxds.dr.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverDao driverDao;
    private final DriverSettingsDao driverSettingsDao;
    private final WalletDao walletDao;
    private final MicroAppUtil microAppUtil;

    @Override
    @Transactional
    public long registerNewDriver(Map<String, Object> params) {

        // 获取openId
        // 从params中获取临时授权码code
        String code = MapUtil.getStr(params, "code");
        if (code == null || code.isBlank()) {
            throw new ZxdsException("必须提供临时授权码");
        }
        // 根据授权码获取openId
        String openId = microAppUtil.getOpenId(code);
        if (openId == null || openId.isBlank()) {
            throw new ZxdsException("获取openId失败");
        }

        // 判断司机是否注册
        int count = driverDao.registerNewDriver(Map.of("openId", openId));
        if (count > 0) {
            // 表示司机已经注册了
            throw new ZxdsException("该微信无法注册");
        }

        // 调用IdUtil生成司机主键id
        long driverId = IdUtil.getIdForDriver();
        String nickname = MapUtil.getStr(params, "nickname");
        String photo = MapUtil.getStr(params, "photo");
        // 保存司机基本信息
        int rows = driverDao.registerNewDriver(Map.of("id", driverId, "nickname", nickname, "photo", photo));
        if (rows != 1) {
            throw new ZxdsException("保存司机基本信息失败");
        }

        // 保存司机基本设置信息
        DriverSettingsEntity dse = new DriverSettingsEntity();
        dse.setId(IdUtil.getIdForDriverSettings());
        dse.setDriverId(driverId);
        JSONObject json = new JSONObject();
        // 不自动抢单
        json.set("autoAccept", 0);
        // 定向接单
        json.set("orientation", "");
        // 启用自动播报订单
        json.set("listenService", true);
        // 司机不挑单
        json.set("orderDistance", 0);
        // 只接收司机5公里以内的单子
        json.set("rangeDistance", 5);

        dse.setSettings(json.toString());
        rows = driverSettingsDao.insertDriverSettings(dse);
        if (rows != 1) {
            throw new ZxdsException("保存司机默认设置失败");
        }

        // 保存司机钱包信息
        WalletEntity we = new WalletEntity();
        we.setId(IdUtil.getIdForWallet());
        we.setDriverId(driverId);
        we.setBalance(new BigDecimal(0));
        we.setPassword(null);
        rows = walletDao.insert(we);
        if (rows != 1) {
            throw new ZxdsException("保存司机钱包信息失败");
        }

        return driverId;
    }
}
