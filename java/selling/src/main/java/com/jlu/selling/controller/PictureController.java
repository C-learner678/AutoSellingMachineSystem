package com.jlu.selling.controller;

import com.jlu.selling.bean.RespBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@RestController
public class PictureController {
    String pathname = "D:\\java\\AutoSellingMachineSystem\\images";
    int id = 1;

    @RequestMapping(value = "/image/getImage/{id}", method = RequestMethod.GET)
    public void getImage(@PathVariable int id, HttpServletResponse response) throws IOException {
        try {
            String filename = id + ".jpg";

            File file = new File(pathname, filename);
            FileInputStream fis;
            fis = new FileInputStream(file);

            long size = file.length();
            byte[] data = new byte[(int) size];
            fis.read(data, 0, (int) size);
            fis.close();
            response.setContentType("image/jpg");
            OutputStream out = response.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/image/setId", method = RequestMethod.POST)
    public RespBean upload(@RequestBody Map<String, String> params){
        this.id = Integer.parseInt(params.get("id"));
        return new RespBean("success", "设置图片ID成功!");
    }

    @RequestMapping(value = "/image/uploadImage")
    public RespBean upload(@RequestParam("file") MultipartFile file) throws IOException {
        if(file != null){
            String fileName = file.getOriginalFilename();
            if(fileName != null && fileName != ""){
                int split = fileName.lastIndexOf(".");
                String suffix = fileName.substring(split+1,fileName.length());
                if("jpg".equals(suffix)){
                    try {
                        String filename = this.id + ".jpg";
                        File file_server = new File(pathname, filename);
                        if (file_server.exists()) {
                            file_server.delete();
                        }
                        file.transferTo(file_server);
                    }catch (Exception e){
                        e.printStackTrace();
                        return new RespBean("error", "上传失败!");
                    }
                    System.out.println("文件上传成功");
                    return new RespBean("success", "上传成功!");
                }else{
                    System.out.println("文件格式有误");
                    return new RespBean("error", "上传失败!");
                }
            }else{
                System.out.println("文件名为空");
                return new RespBean("error", "上传失败!");
            }
        }else{
            System.out.println("未收到文件");
            return new RespBean("error", "上传失败!");
        }
    }
}
