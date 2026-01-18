package com.bite.springmvcdemo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


@RequestMapping("/request")
@RestController
public class RequestController {
    @RequestMapping("/hello")
    public String say(){
        return "hello, Spring MVC";
    }

    @RequestMapping("/r1")
    public String r1(String name){
        return "接收到参数，name："+name;
    }
    @RequestMapping("/r2")
    public String r2(Student student){
        return "接收到参数, student: "+student;
    }

    @RequestMapping("/r6")
    public String r6(@RequestParam("name") String userName){
        return "接收到参数, name: "+userName;
    }

    @RequestMapping("/r7")
    public String r7(@RequestParam(value="name", required = false) String userName){
        return "接收到参数, name: "+userName;
    }

    @RequestMapping("/r8")
    public String r8(String[] array){
        return "接收到参数, array: "+ Arrays.toString(array);
    }

    @RequestMapping("/r9")
    public String r9(@RequestParam List<String> list){
        // 接收list需要一个注解，必传参数
        return "接收到参数, list: "+ list;
    }

    /**
     * 接受json
     * @param student
     * @return
     */
    @RequestMapping("/r10")
    public String r10(@RequestBody Student student){
        //把整个请求正文作为一个整体来接收
        return "接收到参数, student: "+student;
    }

    /**
     * 从路径中获取参数
     * @return
     */

    @RequestMapping("/article/{articleId}")
    public String r11(@PathVariable("articleId") Integer articleId){
        return "接收到参数, articleId: "+articleId;
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("/r12")
    public String r12(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        return "接收到参数, 文件名称: "+originalFilename;
    }
}
