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
    //xml 리소스에서 bean의 id가 uploadPath인 태그 참조
    @Resource(name="uploadPath")
    String uploadPath;

    @RequestMapping(value = "/upload/uploadForm", method= RequestMethod.GET)
    public String uploadForm(){
        return "upload/uploadForm";
    }

    @RequestMapping(value = "/upload/uploadForm", method = RequestMethod.POST)
    public ModelAndView uploadForm(MultipartFile file,ModelAndView mav) throws Exception{
        String savedName = file.getOriginalFilename();
        savedName = uploadFile(savedName, file.getBytes());
        mav.setViewName("upload/uploadResult");
        mav.addObject("savedName", savedName);
        return mav; //uploadResult.jsp로 포워딩
    }

    private String uploadFile(String originalName, byte[] fileData) throws Exception{
        //uuid(Universal Unique IDentifier, 범용 고유 식별자)
        UUID uid = UUID.randomUUID();
        String savedName = uid.toString()+"_"+originalName;
        File target=new File(uploadPath, savedName);
        FileCopyUtils.copy(fileData, target);
        return savedName;

    }

}
