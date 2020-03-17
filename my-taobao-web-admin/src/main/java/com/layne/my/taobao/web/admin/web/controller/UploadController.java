package com.layne.my.taobao.web.admin.web.controller;

import com.layne.my.taobao.commons.constant.ConstantUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传控制器啊
 */
@Controller
public class UploadController {
    /**
     * 文件上传
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropzFile, MultipartFile[] editorFiles,HttpServletRequest request){
        Map<String,Object> result=new HashMap<>();
        if(dropzFile!=null){
            result.put("fileName",writeFile(dropzFile,request));
        }
        if(editorFiles!=null){
            List<String> fileNames=new ArrayList();
            for(MultipartFile multipartFile:editorFiles){
                fileNames.add(writeFile(multipartFile,request));
            }
            result.put("errno",0);
            result.put("data",fileNames);
        }
        return result;
    }

    private String writeFile(MultipartFile multipartFile, HttpServletRequest request) {

        //获取文件后缀
        String fileName=multipartFile.getOriginalFilename();
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
        //文件存放路径
        //request.getSession().getServletContext().getRealPath(ConstantUtils.UPLOAD_PATH) 获取项目路径
        String filePath=request.getSession().getServletContext().getRealPath(ConstantUtils.UPLOAD_PATH);
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        //
        file=new File(filePath, UUID.randomUUID()+fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String serverPath = String.format("%s://%s:%s%s%s", request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath(), ConstantUtils.UPLOAD_PATH);
        return serverPath+file.getName();
    }






    /*public Map<String,Object> upload(MultipartFile dropzFile, MultipartFile editorFile,HttpServletRequest request){
        Map<String,Object> result=new HashMap<>();
        MultipartFile myFile = dropzFile ==null ? editorFile:dropzFile;


        //获取文件后缀
        String fileName=myFile.getOriginalFilename();
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
        //文件存放路径
        //request.getSession().getServletContext().getRealPath(ConstantUtils.UPLOAD_PATH) 获取项目路径
        String filePath=request.getSession().getServletContext().getRealPath(ConstantUtils.UPLOAD_PATH);
        System.out.println(filePath);
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        //
        file=new File(filePath, UUID.randomUUID()+fileName);
        try {
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //dropZone上传
        if(dropzFile!=null){
            result.put("fileName",ConstantUtils.UPLOAD_PATH+file.getName());
        }else{
            //wangEditor上传
            result.put("errno",0);
            // 获取服务端路径
            //Scheme：服务端提供的协议：hhtp/https
            //ServerName：服务器名称 localhost/ip/doamin
            //ServerPort：服务器端口 8080
            //ContextPath：上下文路径
            String serverPath = String.format("%s://%s:%s%s%s", request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath(), ConstantUtils.UPLOAD_PATH);
            System.out.println("request.getContextPath()===================="+request.getContextPath());
            result.put("data",new String[]{serverPath+file.getName()});
        }
        return result;
    }*/
}
