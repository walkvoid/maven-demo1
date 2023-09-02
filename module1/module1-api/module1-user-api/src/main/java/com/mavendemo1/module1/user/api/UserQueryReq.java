package com.mavendemo1.module1.user.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2023/9/2
 * @desc
 */
public class UserQueryReq implements Serializable {

    @JsonProperty("_name")
    private String name;
}
