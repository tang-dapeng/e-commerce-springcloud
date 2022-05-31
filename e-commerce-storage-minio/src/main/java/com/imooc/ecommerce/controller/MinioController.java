package com.imooc.ecommerce.controller;

import com.imooc.ecommerce.utils.MinioUtil;
import com.imooc.ecommerce.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tangdapeng
 * @description
 * @create 2022/05/28
 */
@Slf4j
@RestController("/minio")
public class MinioController {
    @Resource
    private MinioUtil minIoUtils;

    // 存储桶名称
    private static final String MINIO_BUCKET = "img";

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResponse upload(@RequestParam(value = "files") MultipartFile files){
        try {
            return CommonResponse.success(minIoUtils.upload(files,MINIO_BUCKET,null));
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @GetMapping("/download")
    public void download(@RequestParam("minFileName")String minFileName, HttpServletResponse response){
        minIoUtils.download(response,"img",minFileName);
    }
}
