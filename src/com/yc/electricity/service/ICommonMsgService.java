package com.yc.electricity.service;


import com.yc.electricity.domain.CommonMsg;

/**
 * Created by yuchuan on 3/19/16.
 */
public interface ICommonMsgService {

    public static final String SERVICE_NAME = "com.yc.electricity.service.impl.CommonMsgServiceImpl";

    CommonMsg findCommonMsg();
    void saveCommonMsg(CommonMsg commonMsg);
}
