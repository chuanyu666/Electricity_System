package com.yc.electricity.web.action;

import com.yc.electricity.domain.ElecTest;
import com.yc.electricity.service.IElecTestService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import static org.apache.struts2.interceptor.DateTextFieldInterceptor.DateWord.s;

/**
 * Created by Chuan on 3/19/16.
 */
@ParentPackage("struts-default")
@Namespace(value = "/test")
@Controller
@Scope(value = "prototype")
public class ElecTestAction extends BaseAction<ElecTest>{

    private ElecTest elecTest = this.getModel();

    @Resource(name = IElecTestService.SERVICE_NAME)
    private IElecTestService elecTestService;

    @Action(value = "elecTestSave",results = {
            @Result(name = SUCCESS, location = "/system/testAdd.jsp"),
            @Result(name = INPUT, location = "/system/testAdd.jsp")
    })
    public String save() {
        elecTestService.saveElecTest(elecTest);
        System.out.println(elecTest.getTestDate());
        String testDate = request.getParameter("testDate");
        System.out.println(testDate);
        return SUCCESS;
    }
}
