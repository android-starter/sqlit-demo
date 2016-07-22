package com.wangpin.ice.sqlit_demo.bean;

import com.wangpin.ice.sqlit_demo.layer.Employeelayer;

/**
 * Created by wangpi on 7/6/2016.
 */
public class EmployeeBean {

    public EmployeeBean(){}


    public EmployeeBean(int id, String name, String gender){
        this(name, gender);
        this.id = id;
    }

    public EmployeeBean(String name, String gender){
        this.name = name;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;
}
