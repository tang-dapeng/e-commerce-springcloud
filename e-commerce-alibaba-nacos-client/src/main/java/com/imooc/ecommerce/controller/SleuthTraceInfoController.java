package com.imooc.ecommerce.controller;

import com.imooc.ecommerce.annotation.IgnoreResponseAdvice;
import com.imooc.ecommerce.service.SleuthTraceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 打印跟踪信息
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/19
 */
@Slf4j
@RestController
@RequestMapping("/sleuth")
public class SleuthTraceInfoController {
    @Resource
    private SleuthTraceInfoService sleuthTraceInfoService;

    /**
     * 打印日志跟踪信息
     * @return
     */
    @GetMapping("/trace-info")
    public void logCurrentTraceInfo() {
        sleuthTraceInfoService.logCurrentTraceInfo();
    }
}
