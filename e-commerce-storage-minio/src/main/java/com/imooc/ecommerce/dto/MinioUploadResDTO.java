package com.imooc.ecommerce.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * MinIoUploadResDTO dto
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/28
 * @date 2022/05/28
 */
@Data
public class MinioUploadResDTO implements Serializable {
    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 475040120689218785L;

    /**
     * 文件名
     */
    private String minFileName;
    /**
     * 文件url
     */
    private String minFileUrl;

    /**
     * @param minFileName 文件名
     * @param minFileUrl  文件url
     */
    public MinioUploadResDTO(String minFileName, String minFileUrl) {
        this.minFileName = minFileName;
        this.minFileUrl = minFileUrl;
    }
}
