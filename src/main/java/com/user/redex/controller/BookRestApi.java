package com.user.redex.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api use to perform crud operation
 * @author Nabeel Ahmed
 */
@RestController
@RequestMapping(value = "/book")
public class BookRestApi {

    private Logger logger = LoggerFactory.getLogger(BookRestApi.class);
}
