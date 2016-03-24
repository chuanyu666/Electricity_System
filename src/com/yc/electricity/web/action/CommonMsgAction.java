package com.yc.electricity.web.action;

import com.yc.electricity.domain.CommonMsg;
import com.yc.electricity.domain.ElecTest;
import com.yc.electricity.service.ICommonMsgService;
import com.yc.electricity.service.IElecTestService;
import com.yc.electricity.util.ValueStackUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


/**
 * Created by Chuan on 3/19/16.
 */
@ParentPackage("struts-default")
@Namespace(value = "/system")
@Controller
@Scope(value = "prototype")
public class CommonMsgAction extends BaseAction<CommonMsg>{

    private CommonMsg commonMsg = this.getModel();

    @Resource(name = ICommonMsgService.SERVICE_NAME)
    private ICommonMsgService service;

    @Action(value = "commonMsgHome",
            results = {
                        @Result(name = SUCCESS, location = "/WEB-INF/page/system/actingIndex.jsp")
    })
    public String home() {
        CommonMsg findCommonMsg = service.findCommonMsg();
        ValueStackUtil.pushValueStack(findCommonMsg);
        return SUCCESS;
    }

    @Action(value = "saveCommonMsg",
            results = {
                    @Result(name = SUCCESS, type = "redirectAction", location = "commonMsgHome")
            })
    public String save() {
        service.saveCommonMsg(commonMsg);
        return SUCCESS;
    }
}
