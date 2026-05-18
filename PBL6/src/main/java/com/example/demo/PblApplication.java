package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class PblApplication {

    public static void main(String[] args) {

//        ApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(AppConfig.class);

        ApplicationContext applicationContext = SpringApplication.run(PblApplication.class, args);

        MemberService memberService =
                applicationContext.getBean(MemberService.class);

        System.out.println(memberService);
    }
}