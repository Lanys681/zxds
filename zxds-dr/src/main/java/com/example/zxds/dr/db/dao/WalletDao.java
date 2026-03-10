package com.example.zxds.dr.db.dao;


import com.example.zxds.dr.db.pojo.WalletEntity;

import java.util.HashMap;
import java.util.Map;

public interface WalletDao {
    /**
     * 保存钱包默认信息
     * @param we
     * @return
     */
    int insert(WalletEntity we);

}




