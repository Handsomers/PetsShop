package com.pet.shop.serviceimpl;

import com.google.gson.Gson;
import com.pet.shop.service.upload;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;


/**
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @Package com.pet.shop.serviceimpl
 * @author: angtai（angtai@maihaoche.com）
 * @date: 2019/4/5 2:47 PM
 * @Copyright: 2017-2020 www.maihaoche.com Inc. All rights reserved.
 */
@Service
public  class uploadImpl implements upload {
    @Value("${qiniu.AccessKey}")
    private String accessKey;

    @Value("${qiniu.SecretKey}")
    private String secretKey;

    @Value("${qiniu.Bucket}")
    private String bucket;

    @Override
    public void upload(byte[] data, String name) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());

        UploadManager uploadManager = new UploadManager(cfg);

        try {
            Response response = uploadManager.put(data, name, this.getToken());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                throw new RuntimeException("上传到七牛云失败!");
            }
        }

    }

    @Override
    public void upload(String filePath,String name) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());

        UploadManager uploadManager = new UploadManager(cfg);

        try {
            Response response = uploadManager.put(filePath, name, this.getToken());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                throw new RuntimeException("上传到七牛云失败!");
            }
        }
    }

    /**
     * 获取上传凭证
     * @return
     */
    private String getToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }
}