package com.yc.electricity.web.action;

import com.yc.electricity.domain.CommonMsg;
import com.yc.electricity.service.ICommonMsgService;
import com.yc.electricity.util.ValueStackUtil;
import com.yc.electricity.web.action.form.MenuForm;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


/**
 * Created by Chuan on 3/22/16.
 */
@ParentPackage("struts-default")
@Namespace(value = "/system")
@Controller
@Scope(value = "prototype")
public class MenuFormAction extends BaseAction<MenuForm>{

    private MenuForm menuForm = this.getModel();

    @Resource(name = ICommonMsgService.SERVICE_NAME)
    private ICommonMsgService commonMsgService;

    @Action(value = "menuHome",results = {
            @Result(name = SUCCESS, location = "/WEB-INF/page/menu/home.jsp")
    })
    public String menuHome() {
        System.out.println(menuForm.getName() + "  " + menuForm.getPassword());
        return SUCCESS;
    }

    @Action(value = "menuTitle",results = {
            @Result(name = SUCCESS, location = "/WEB-INF/page/menu/title.jsp")
    })
    public String menuTitle() {
        return SUCCESS;
    }

    @Action(value = "menuLeft",results = {
            @Result(name = SUCCESS, location = "/WEB-INF/page/menu/left.jsp")
    })
    public String menuLeft() {
        return SUCCESS;
    }

    @Action(value = "menuChange",results = {
            @Result(name = SUCCESS, location = "/WEB-INF/page/menu/change.jsp")
    })
    public String menuChange() {
        return SUCCESS;
    }

    @Action(value = "menuLoading",results = {
            @Result(name = SUCCESS, location = "/WEB-INF/page/menu/loading.jsp")
    })
    public String menuLoading() {
        CommonMsg findCommonMsg = commonMsgService.findCommonMsg();
        ValueStackUtil.pushValueStack(findCommonMsg);
        return SUCCESS;
    }

    @Action(value = "alarmStation",results = {
            @Result(name = SUCCESS, location = "/WEB-INF/page/menu/alarmStation.jsp")
    })
    public String alarmStation() {
        CommonMsg findCommonMsg = commonMsgService.findCommonMsg();
        ValueStackUtil.pushValueStack(findCommonMsg);
        return SUCCESS;
    }

    @Action(value = "alarmDevice",results = {
            @Result(name = SUCCESS, location = "/WEB-INF/page/menu/alarmDevice.jsp")
    })
    public String alarmDevice() {
        CommonMsg findCommonMsg = commonMsgService.findCommonMsg();
        ValueStackUtil.pushValueStack(findCommonMsg);
        return SUCCESS;
    }

    @Action(value = "logout",results = {
            @Result(name = SUCCESS, type = "redirect", location = "/index.jsp")
    })
    public String logout() {
        request.getSession().invalidate();
        return SUCCESS;
    }
}
