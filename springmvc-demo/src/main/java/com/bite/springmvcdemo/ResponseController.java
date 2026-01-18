package com.bite.springmvcdemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("/res")
//@RestController = @Controller+@ResponseBody
@Controller // 用于展示页面
public class ResponseController {
    @RequestMapping("/index")
    public String index(){
        return "/index.html";
    }

    /**
     * 返回数据
     * @return
     */
    @ResponseBody //用于返回数据
    @RequestMapping("/indexData")
    public String indexData(){
        return "返回数据";
    }

    /**
     * 返回HTML代码块
     * @return
     */
    @RequestMapping("/returnHtml")
    @ResponseBody
    public String returnHtml() {
        return "<h1>Hello,HTML~</h1>";
    }

    /**
     * 返回json
     * @return
     */
    @RequestMapping("/getMap")
    @ResponseBody
    public HashMap<String, String> getMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("k1","v1");
        hashMap.put("k2","v2");
        hashMap.put("k3","v3");
        hashMap.put("k4","v4");

        return hashMap;
    }

    /**
     * 设置状态码，状态码不影响页面展示
     * @param response
     * @return
     */
    @RequestMapping(value = "/setStatus")
    @ResponseBody
    public String setStatus(HttpServletResponse response) {
        response.setStatus(401);
        return "设置状态码成功";
    }
}
