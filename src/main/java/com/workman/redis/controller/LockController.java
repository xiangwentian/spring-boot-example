package com.workman.redis.controller;

import com.workman.redis.annotation.CacheLock;
import com.workman.redis.annotation.CacheParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class LockController {

    @CacheLock(prefix = "test")
    @RequestMapping("/test")
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success- " + token;
    }
}
