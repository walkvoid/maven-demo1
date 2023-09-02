package com.mavendemo1.module2.product.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2023/9/3
 * @desc
 */
public class ProductAddReq {

    @JsonProperty("product_name")
    public String productName;
}
