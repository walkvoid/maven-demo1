package com.mavendemo1.module2.product.service;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author qingshan1993
 * @version 1.0
 * @date 2023/9/3
 * @desc
 */
public class ProductService {

    @JsonProperty("_name")
    private String name;

    public void fourParamsMethod(String p1, String p2, String p3, Integer p4) {
        p3 = p1 +p2;
    }

}
