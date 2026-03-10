package com.example.zxds.common.util;

/**
 * 通过雪花算法生成全局唯一id
 */
public class IdUtil {
    /**
     * 专门为司机模块的司机表生成主键值
     *
     * @return
     */
    public static long getIdForDriver() {
        return cn.hutool.core.util.IdUtil.getSnowflake(1, 1).nextId();
    }

    /**
     * 专门为司机模块的司机设置表生成主键值
     *
     * @return
     */
    public static long getIdForDriverSettings() {
        return cn.hutool.core.util.IdUtil.getSnowflake(2, 2).nextId();
    }

    /**
     * 专门为司机模块的钱包表生成主键值
     *
     * @return
     */
    public static long getIdForWallet() {
        return cn.hutool.core.util.IdUtil.getSnowflake(3, 3).nextId();
    }
}
