package com.wangpin.ice.sqlit_demo.constant;

/**
 * Created by wangpi on 7/6/2016.
 */
public enum GenderEnum {

    MALE("male"),FEMALE("female");

    private String name;

    private GenderEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
