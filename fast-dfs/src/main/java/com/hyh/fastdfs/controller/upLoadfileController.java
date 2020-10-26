package com.hyh.fastdfs.controller;

import com.hyh.fastdfs.utils.FastDFSClient;
import com.hyh.fastdfs.utils.IMoocJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SuppressWarnings("all")
@RestController
@RequestMapping(value = "/file")
public class upLoadfileController {
    @Autowired
    FastDFSClient fastDFSClient = new FastDFSClient();

    @RequestMapping(value = "/fileupload")
    public IMoocJSONResult uploadFile(MultipartFile file) throws IOException {
        if(file == null){
            return IMoocJSONResult.errorMsg("文件为空上传失败！");
        }

        System.out.println(file.getName());
        System.out.println(file.getSize());
        String path = fastDFSClient.uploadFile(file);
        return IMoocJSONResult.ok("Path:" + path);
    }
}
