package com.system.aym.common.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public Map<String, Object> index() {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("code", 200);
        response.put("message", "Ok");
        response.put("data", new HashMap<String, String>());
        return response;
    }

}

