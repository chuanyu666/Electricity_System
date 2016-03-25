package com.yc.electricity.dao.impl;

import com.yc.electricity.dao.ICommonMsgDao;
import com.yc.electricity.domain.CommonMsg;
import org.springframework.stereotype.Repository;

/**
 * Created by yuchuan on 3/19/16.
 */
@Repository(ICommonMsgDao.DAO_NAME)
public class CommonMsgDaoImpl extends BaseDaoImpl<CommonMsg> implements ICommonMsgDao{
}
