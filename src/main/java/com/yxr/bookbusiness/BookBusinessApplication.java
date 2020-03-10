package com.yxr.bookbusiness;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.yxr.bookbusiness.dao")
@EnableTransactionManagement
public class BookBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookBusinessApplication.class, args);
    }

}
