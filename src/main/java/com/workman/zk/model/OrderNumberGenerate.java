package com.workman.zk.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumberGenerate {

    //全局订单ID
    private static Integer count = 0;

    public String getNumber(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        return format.format(new Date())+"-"+ ++count;
    }
}
