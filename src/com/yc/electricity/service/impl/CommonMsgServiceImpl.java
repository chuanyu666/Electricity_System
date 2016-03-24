package com.yc.electricity.service.impl;

import com.yc.electricity.dao.ICommonMsgDao;
import com.yc.electricity.domain.CommonMsg;
import com.yc.electricity.service.ICommonMsgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by yuchuan on 3/19/16.
 */
@Service(value = ICommonMsgService.SERVICE_NAME)
@Transactional(readOnly = true)
public class CommonMsgServiceImpl implements ICommonMsgService{

    @Resource(name = ICommonMsgDao.DAO_NAME)
    private ICommonMsgDao dao;

    @Override
    public CommonMsg findCommonMsg() {
        List<CommonMsg> list = dao.findListByConditionNoPage(null, null, null);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveCommonMsg(CommonMsg commonMsg) {
        List<CommonMsg> list = dao.findListByConditionNoPage(null, null, null);
        if (list != null && !list.isEmpty()) {
            CommonMsg existCommonMsg = list.get(0);
            existCommonMsg.setStationRun(commonMsg.getStationRun());
            existCommonMsg.setDevRun(commonMsg.getDevRun());
            existCommonMsg.setCreateDate(new Date());
            /**
             *   session can't have 2 objects with same ID!!
             *   update will cause error
             *   Maybe we can user hibernate merge method.
             *
             */
//            commonMsg.setComId(list.get(0).getComId());
//            commonMsg.setCreateDate(new Date());
//            dao.update(commonMsg);

        }
        else {
            commonMsg.setCreateDate(new Date());
            dao.save(commonMsg);
        }
    }
}
