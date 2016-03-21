package com.yc.electricity.dao.impl;

import com.yc.electricity.dao.IElecTestDao;
import com.yc.electricity.domain.ElecTest;
import org.springframework.stereotype.Repository;

/**
 * Created by yuchuan on 3/19/16.
 */
@Repository(IElecTestDao.DAO_NAME)
public class ElecTestDaoImpl extends CommonDaoImpl<ElecTest> implements IElecTestDao{
}
