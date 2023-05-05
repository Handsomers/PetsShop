package com.pet.shop.service;


/**
 * 七牛云图片上传
 * @author 昂太
 *
 */
public interface upload {
    /**
     * 七牛云上传
     * @param data 字节流
     * @param name
     */
    void upload(byte[] data,String name);

    /**
     * 七牛云上传
     * @param filePath 文件绝对路径
     */
    void upload(String filePath,String name);
}
