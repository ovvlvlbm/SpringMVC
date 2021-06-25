package com.example.spring02.controller.upload;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

@Controller
public class UploadController {

    @Resource(name="uploadPath")    //ref to the tag which has uploadPath id from servlet.xml.
    String uploadPath;

    //GET
    @RequestMapping(value = "/upload/uploadForm", method= RequestMethod.GET)
    public String uploadForm(){
        return "upload/uploadForm";
    }

    //POST
    @RequestMapping(value = "/upload/uploadForm", method = RequestMethod.POST)
    public ModelAndView uploadForm(MultipartFile file,ModelAndView mav) throws Exception{
        //Name of an attached file.
        String savedName = file.getOriginalFilename();
        //Add UUID to filename.
        savedName = uploadFile(savedName, file.getBytes());
        //Name of JSP page
        mav.setViewName("upload/uploadResult");

        mav.addObject("savedName", savedName);
        return mav; //Forwarding to uploadResult.jsp
    }

    private String uploadFile(String originalName, byte[] fileData) throws Exception{
        //uuid(Universal Unique Identifier, 범용 고유 식별자)
        UUID uid = UUID.randomUUID();
        String savedName = uid +"_"+originalName;
        File target=new File(uploadPath, savedName);
        FileCopyUtils.copy(fileData, target);
        return savedName;

    }

}
