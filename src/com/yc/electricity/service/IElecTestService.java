package com.yc.electricity.service;

import com.yc.electricity.domain.ElecTest;

import java.util.List;
import java.util.Map;


/**
 * Created by yuchuan on 3/19/16.
 */
public interface IElecTestService {

    public static final String SERVICE_NAME = "com.yc.electricity.service.impl.ElecTestServiceImpl";

    void saveElecTest(ElecTest elecTest);
    ElecTest findElecTestById(String id);
    void updateElecTest(ElecTest elecTest);
    void deleteElecTestByIds(String... ids);
    void deleteElecTestByList(List<ElecTest> list);
    List<ElecTest> findElecTestListByConditionNoPage(ElecTest elecTest);
}
