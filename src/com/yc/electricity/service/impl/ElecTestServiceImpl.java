package com.yc.electricity.service.impl;

import com.yc.electricity.dao.IElecTestDao;
import com.yc.electricity.domain.ElecTest;
import com.yc.electricity.service.IElecTestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuchuan on 3/19/16.
 */
@Service(value = IElecTestService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecTestServiceImpl implements IElecTestService{

    @Resource(name = IElecTestDao.DAO_NAME)
    private IElecTestDao elecTestDao;

    @Override
    @Transactional(readOnly = false)
    public void saveElecTest(ElecTest elecTest) {
        elecTestDao.save(elecTest);
    }

    @Transactional(readOnly = false)
    public void updateElecTest(ElecTest elecTest) {
        elecTestDao.update(elecTest);
    }

    @Override
    public ElecTest findElecTestById(String id) {
        return elecTestDao.findObjectById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteElecTestByIds(String... ids) {
        elecTestDao.deleteObjectsByIds(ids);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteElecTestByList(List<ElecTest> list) {
        elecTestDao.deleteObjectsByList(list);
    }

    @Override
    public List<ElecTest> findElecTestListByConditionNoPage(ElecTest elecTest) {
        String condition = "";
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotBlank(elecTest.getTestName())) {
            condition += " and o.testName like ?";
            params.add("%" + elecTest.getTestName() + "%");
        }
        if (StringUtils.isNotBlank(elecTest.getTestRemark())) {
            condition += " and o.testRemark like ?";
            params.add("%" + elecTest.getTestRemark() + "%");
        }

        Map<String,String> orderBy = new LinkedHashMap<>();
        orderBy.put("o.testDate", "asc");
        orderBy.put("o.testRemark", "desc");

        return elecTestDao.findListByConditionNoPage(condition, params.toArray(), orderBy);
    }
}
