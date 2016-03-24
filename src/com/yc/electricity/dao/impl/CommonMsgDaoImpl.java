package com.yc.electricity.dao.impl;

import com.yc.electricity.dao.ICommonMsgDao;
import com.yc.electricity.dao.IElecTestDao;
import com.yc.electricity.domain.CommonMsg;
import com.yc.electricity.domain.ElecTest;
import org.springframework.stereotype.Repository;

/**
 * Created by yuchuan on 3/19/16.
 */
@Repository(ICommonMsgDao.DAO_NAME)
public class CommonMsgDaoImpl extends CommonDaoImpl<CommonMsg> implements ICommonMsgDao{
}
