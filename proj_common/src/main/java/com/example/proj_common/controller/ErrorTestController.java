package com.example.proj_common.controller;

import com.example.proj_common.annotation.CosmoController;
import org.springframework.web.bind.annotation.GetMapping;

@CosmoController
public class ErrorTestController {


    @GetMapping("/error/test")
    public String errorTest(){

        int i = 1/0;

        return "false";
    }

    @GetMapping("/StringTest")
    public Boolean stringTest(String str){
        return false;
    }


}
