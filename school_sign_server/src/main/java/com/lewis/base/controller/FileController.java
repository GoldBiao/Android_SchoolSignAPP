package com.lewis.base.controller;


import com.lewis.base.config.Contance;
import com.lewis.base.entity.ComResult;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;



@RestController
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    //上传头像
    @RequestMapping(value = "/upload")
    public  ComResult<String> upload(@RequestParam("file") MultipartFile file) {
        ComResult<String> comResult = new ComResult<>();
        comResult.setCode(0);

        try {
            if (file.isEmpty()) {
                comResult.setMsg("文件为空");

                return comResult;
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
          //
            String filePath = Contance.IMAGE_PATH;
            //String filePath = "/Users/Administrator/Desktop/res/";
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            comResult.setMsg("上传成功");
            comResult.setData(dest.getName());

            return comResult;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        comResult.setMsg("上传失败");
        return comResult;
    }


}