package com.yc.electricity.dao;

import com.yc.electricity.domain.CommonMsg;
import com.yc.electricity.domain.ElecTest;

/**
 * Created by yuchuan on 3/19/16.
 */
public interface ICommonMsgDao extends ICommonDao<CommonMsg>{
    public static final String DAO_NAME = "com.yc.electricity.dao.impl.CommonMsgDaoImpl";
}
