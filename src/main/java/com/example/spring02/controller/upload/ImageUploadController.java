package com.example.spring02.controller.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

@Controller
public class ImageUploadController {
    @RequestMapping("imageUpload.do")
    public void imageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        OutputStream out=null;
        PrintWriter printWriter = null;

        String fileName = upload.getOriginalFilename();
        byte[] bytes = upload.getBytes();

        ServletContext application = request.getSession().getServletContext();
        String uploadPath = application.getRealPath("/WEB-INF/views/images/");

        out = new FileOutputStream(new File(uploadPath+fileName));
        out.write(bytes);

        printWriter = response.getWriter();
        String fileUrl = request.getContextPath()+"/images/"+fileName;
        printWriter.println("{\"filename\" : \"" + fileName + "\", \"upload\" : 1, \"url\":\""+fileUrl+"\"}");
        printWriter.flush();
    }
}
