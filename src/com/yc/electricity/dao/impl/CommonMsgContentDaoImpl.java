package com.yc.electricity.dao.impl;

import com.yc.electricity.dao.ICommonMsgContentDao;
import com.yc.electricity.dao.ICommonMsgDao;
import com.yc.electricity.domain.CommonMsgContent;
import org.springframework.stereotype.Repository;

/**
 * Created by yuchuan on 3/19/16.
 */
@Repository(ICommonMsgContentDao.DAO_NAME)
public class CommonMsgContentDaoImpl extends BaseDaoImpl<CommonMsgContent> implements ICommonMsgContentDao{
}
