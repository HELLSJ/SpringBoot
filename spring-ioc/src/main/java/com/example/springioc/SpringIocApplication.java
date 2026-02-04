package com.example.springioc;

import com.example.springioc.component.UserComponent;
import com.example.springioc.controller.HelloController;
import com.example.springioc.controller.UController;
import com.example.springioc.repo.UserRepo;
import com.example.springioc.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringIocApplication {

    public static void main(String[] args) {
//        //三种方式得到同一个对象，单例模式
//        //方法一
        ApplicationContext context = SpringApplication.run(SpringIocApplication.class, args); //Spring运行环境
//        HelloController bean = context.getBean(HelloController.class);
//        bean.sayHi(); //Spring调用这个Bean实例的sayHi()方法
//        //方法二
//        HelloController helloController = (HelloController) context.getBean("helloController");
//        helloController.sayHi(); //通过bean的名称调用sayHi()方法
//        //方法三
//        HelloController helloController1 = context.getBean("helloController", HelloController.class);
//        helloController1.sayHi();

//        //方法一
//        UController bean = context.getBean(UController.class);
//        bean.sayHi(); //Spring调用这个Bean实例的sayHi()方法
//        //方法二
//        UController uController = (UController) context.getBean("UController"); //如果第一和第二个字母都是大写，它的bean就跟原来一样，首字母不需要小写
//        uController.sayHi(); //通过bean的名称调用sayHi()方法
//        //方法三
//        UController uController1 = context.getBean("UController", UController.class);
//        uController1.sayHi();

        UserService bean = context.getBean(UserService.class);
        bean.doService();

        UserComponent bean1 = context.getBean(UserComponent.class);
        bean1.doComponent();

        UserComponent bean2 = context.getBean(UserComponent.class);
        bean2.doComponent();

        UserRepo bean3 = context.getBean(UserRepo.class);
        bean3.doRepo();
    }
}
