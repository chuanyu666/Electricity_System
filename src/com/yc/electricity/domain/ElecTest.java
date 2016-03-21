package com.yc.electricity.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yuchuan on 3/19/16.
 */
@Entity
@Table(name = "Elec_Test")
public class ElecTest implements Serializable{

    private String testId;
    private String testName;
    private Date testDate;
    private String testRemark;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid" )
    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getTestRemark() {
        return testRemark;
    }

    public void setTestRemark(String testRemark) {
        this.testRemark = testRemark;
    }
}
