package com.example.spring02.controller.upload;

import com.example.spring02.service.board.BoardService;
import com.example.spring02.util.MediaUtils;
import com.example.spring02.util.UploadFileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Controller
public class AjaxUploadController {
    @Inject
    BoardService boardService;

    @Resource(name = "uploadPath") //from servlet.xml
    String uploadPath;

    //GET
    @RequestMapping(value = "/upload/uploadAjax", method = RequestMethod.GET)
    public String uploadAjax(){
        return "/upload/uploadAjax";
    }
    //POST
    @RequestMapping(value = "/upload/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody //data is returned to body part instead of view.
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception { //view가 아닌 데이터를 리턴하는 Method
        System.out.println("originalName: " + file.getOriginalFilename());
        System.out.println("size: "+file.getSize());
        System.out.println("contentType: " + file.getContentType());
        //After uploading, return the filename.
        return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath, Objects.requireNonNull(file.getOriginalFilename()), file.getBytes()), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/upload/displayFile")
    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
        InputStream in = null;
        ResponseEntity<byte[]> entity = null;
        try {
            //파일의 확장자
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            //미디어타입 검사
            MediaType mType = MediaUtils.getMediaType(formatName);
            HttpHeaders headers = new HttpHeaders();
            in = new FileInputStream(uploadPath + fileName);
            if(mType != null){ //이미지 파일인 경우
                headers.setContentType(mType);
            }else {
                //uuid를 제외한 파일이름
                fileName = fileName.substring(fileName.indexOf("")+1);
                //파일의 컨텐트타입 지정
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                //파일이름을 서유럽언어 형식으로 지정하여 url에서 깨지지 않도록 처리
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
                //헤더에 첨부파일 정보 추가
                headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            }
            //파일내용 리턴
            entity = new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            if(in!=null)
                in.close();
        }
        return entity;
    }

    @ResponseBody
    @RequestMapping(value = "/upload/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) {
        //파일의 확장자
        String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
        MediaType mType = MediaUtils.getMediaType(formatName);
        if(mType!=null){ //이미지 파일이면
            String front = fileName.substring(0,12);
            String end = fileName.substring(14);
            //썸네일 삭제
            File thumbnail = new File(uploadPath + (front+end).replace('/',File.separatorChar));
            if(thumbnail.exists()){
                try {
                    if(thumbnail.delete()){
                        System.out.println("The deletion has been completed.");
                    }else{
                        System.out.println("The deletion has been failed.");
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //파일삭제. If a file exists, try to delete it.
        File file = new File(uploadPath+fileName.replace('/',File.separatorChar));
        if(file.exists()){
            try {
                if(file.delete()){  //if the file is successfully deleted,
                    System.out.println("The deletion has been completed.");
                    //delete the record
                    boardService.deleteFile(fileName);
                }else{
                    System.out.println("The deletion has been failed.");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }
}
