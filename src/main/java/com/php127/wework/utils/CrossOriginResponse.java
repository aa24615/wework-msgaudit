package com.php127.wework.utils;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@CrossOrigin
public @interface CrossOriginResponse {

}