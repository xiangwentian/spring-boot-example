package com.workman.Event.eventlistener;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 测试的controller
 * @date 2019/7/24 17:13
 */
@Controller
@RequestMapping("/test")
public class TestController {


    @Resource
    private ApplicationContext applicationContext;

    private static ExecutorService serviceUpdateExecutor
            = Executors.newFixedThreadPool(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("thead test name");
//            t.setDaemon(true);
            return t;
        }
    });

    @ResponseBody
    @RequestMapping("/testListener")
    public String testListener() {
        //new thread
        serviceUpdateExecutor.execute(() -> {
            applicationContext.publishEvent(new AddDataEvent(this, TestController.class, "test"));
        });
        serviceUpdateExecutor.shutdown();
        System.out.println("main:" + System.currentTimeMillis());
        return "success";
    }
}

