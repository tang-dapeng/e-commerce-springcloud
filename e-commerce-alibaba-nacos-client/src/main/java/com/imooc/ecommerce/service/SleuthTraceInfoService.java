package com.imooc.ecommerce.service;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 使用代码更直观的看到 Sleuth 生成的相关跟踪信息
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/19
 */
@Slf4j
@Service
public class SleuthTraceInfoService {
    /*brave.Tracer 跟踪对象*/
    private final Tracer tracer;

    public SleuthTraceInfoService(Tracer tracer) {
        this.tracer = tracer;
    }

    public void logCurrentTraceInfo() {
        log.info("Sleuth trace id: [{}]",tracer.currentSpan().context().traceId());
        log.info("Sleuth span id: [{}]",tracer.currentSpan().context().spanId());
    }

}
