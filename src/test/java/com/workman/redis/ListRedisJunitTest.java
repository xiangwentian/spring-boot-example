package com.workman.redis;

import com.alibaba.fastjson.JSON;
import com.workman.BaseTestRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ListRedisJunitTest extends BaseTestRunner {
    @Autowired
    private ListRedisService listRedisService;

    String key = "list-1";

    @Test
    public void add() {
        String key = "list-1";

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        listRedisService.rightAdd(key, list);

//        List<String> list2 = new ArrayList<String>();
//        list2.add("1");
//        list2.add("2");
//        list2.add("3");
//        listRedisService.rightAdd(key, list2);

//        String list3 = "list3";
//        listRedisService.leftAdd(key, list3);
    }

    @Test
    public void getIndex() {
        Integer index = -1;
        String result = listRedisService.get(key, index);
        System.out.println("list get index result--->>>" + result);
    }

    @Test
    public void getRange() {
        Integer begin = 0;
        Integer end = 1;
        List list = listRedisService.getRange(key, begin, end);
        if (null == list) {
            System.out.println("redist list getRange ---->>> is null");
            return;
        }
        System.out.println("redist list getRange--->>>" + JSON.toJSONString(list));
    }

    @Test
    public void lpop() {
        Object list = listRedisService.leftPop(key);
        if (null == list) {
            System.out.println("redist list lpop--->>> is null");
            return;
        }
        System.out.println("redist list lpop--->>>" + JSON.toJSONString(list));
    }

    @Test
    public void rpop(){
        Object list = listRedisService.rightPop(key);
        if (null == list) {
            System.out.println("redist list rpop--->>> is null");
            return;
        }
        System.out.println("redist list rpop--->>>" + JSON.toJSONString(list));
    }
}
