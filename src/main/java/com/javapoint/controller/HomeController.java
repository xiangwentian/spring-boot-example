package com.javapoint.controller;

import com.javapoint.bean.User;
import com.workman.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {
    //private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        log.info("invoke hello method and success");
        return "hello 8000";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-data");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "getRedisV")
    @ResponseBody
    public String getRedisValue(String key) {
        log.info("invoke getRedisValue method");
        String value = redisUtils.get(key);
        log.info("invoke getRedisValue method,value={}",value);
        return value;
    }

    @RequestMapping(value = "setRedisV")
    @ResponseBody
    public String setRedisValue(String key, String value) {
        log.info("invoke setRedisValue method");
        boolean setResult = redisUtils.set(key, value);
        log.info("invoke setRedisValue method,setResult={}",setResult);
        String getvalue = redisUtils.get(key);
        log.info("invoke setRedisValue method,getvalue={}",getvalue);
        return getvalue;
    }

}
