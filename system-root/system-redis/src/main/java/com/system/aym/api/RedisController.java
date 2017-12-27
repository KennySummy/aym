package com.system.aym.api;

import com.system.aym.common.bean.ResponseData;
import com.system.aym.common.controller.BaseController;
import com.system.aym.service.RedisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class RedisController extends BaseController {

    @Resource
    public RedisService service;

    @RequestMapping(value = "/set/{key}/{value}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData set(@PathVariable String key, @PathVariable String value) {
        String flag = service.set(key, value);
        if (flag == "SUCCESS") {
            return ResponseData.ok();
        } else {
            return ResponseData.badRequest();
        }
    }

    @RequestMapping(value = "/setex/{key}/{liveTime}/{value}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData setex(@PathVariable String key, @PathVariable int liveTime, @PathVariable String value) {
        String flag = service.setex(key, liveTime, value);
        if (flag == "SUCCESS") {
            return ResponseData.ok();
        } else {
            return ResponseData.badRequest();
        }
    }

    @RequestMapping(value = "/setnx/{key}/{value}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData setnx(@PathVariable String key, @PathVariable String value) {
        String flag = service.setnx(key, value);
        if (flag == "SUCCESS") {
            return ResponseData.ok();
        } else {
            return ResponseData.badRequest();
        }
    }

    @RequestMapping(value = "/get/{key}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData get(@PathVariable String key) {
        String value = service.get(key);
        if (value != null) {
            return ResponseData.ok().putDataValue(key, value);
        } else {
            return ResponseData.notFound();
        }
    }

    @RequestMapping(value = "/delete/{key}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(@PathVariable String key) {
        String flag = service.delete(key);
        if (flag == "SUCCESS") {
            return ResponseData.ok();
        } else {
            return ResponseData.badRequest();
        }
    }

    @RequestMapping(value = "/expire/{key}/{liveTime}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData expire(@PathVariable String key, @PathVariable int liveTime) {
        long flag = service.expire(key, liveTime);
        if (flag == 1l) {
            return ResponseData.ok();
        } else {
            return ResponseData.badRequest();
        }
    }

    @RequestMapping(value = "/append/{key}/{value}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData append(@PathVariable String key, @PathVariable String value) {
        String flag = service.append(key, value);
        if (flag == "SUCCESS") {
            return ResponseData.ok();
        } else {
            return ResponseData.badRequest();
        }
    }

    @RequestMapping(value = "/getrange/{key}/{start}/{end}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getrange(@PathVariable String key, @PathVariable long start, @PathVariable long end) {
        String flag = service.getrange(key, start, end);
        if (flag == "SUCCESS") {
            return ResponseData.ok();
        } else {
            return ResponseData.badRequest();
        }
    }

    @RequestMapping(value = "/exists/{key}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData exists(@PathVariable String key) {
        boolean flag = service.exists(key);
        if (flag) {
            return ResponseData.ok();
        } else {
            return ResponseData.badRequest();
        }
    }

}
