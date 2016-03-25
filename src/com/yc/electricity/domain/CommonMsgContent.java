package com.yc.electricity.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static org.apache.struts2.interceptor.DateTextFieldInterceptor.DateWord.s;

/**
 * Created by Chuan on 3/24/16.
 */
@Entity
@Table(name = "Elec_CommonMsg_Content")
public class CommonMsgContent implements Serializable{

    private String comId;
    private String type;
    private String content;
    private int orderby;

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOrderby() {
        return orderby;
    }

    public void setOrderby(int orderby) {
        this.orderby = orderby;
    }
}
