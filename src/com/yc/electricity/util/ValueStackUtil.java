package com.yc.electricity.util;

import com.opensymphony.xwork2.ActionContext;

/**
 * Created by Chuan on 3/23/16.
 */
public class ValueStackUtil {

    public static void pushValueStack(Object obj) {
        ActionContext.getContext().getValueStack().push(obj);
    }
}
