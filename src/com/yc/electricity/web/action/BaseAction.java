package com.yc.electricity.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.electricity.util.TUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Chuan on 3/20/16.
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>, ServletRequestAware, ServletResponseAware {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected T entity;

    public BaseAction() {
        // Convert generic T
        Class<T> clazz =TUtils.getActualType(this.getClass());
        try {
            entity = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getModel() {
        return entity;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}
