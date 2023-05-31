package com.example.proj_common.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RestController
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CosmoController {
}
