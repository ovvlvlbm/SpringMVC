package com.example.spring02.util;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

public class UploadFileUtils {
    public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
        UUID uid = UUID.randomUUID(); //uuid 객체
        //파일이름 앞에 uuid 추가
        String savedName = uid.toString()+"_"+originalName;
        //저장할 디렉토리
        String savedPath = calcPath(uploadPath);

        File target = new File(uploadPath+savedPath, savedName);
        FileCopyUtils.copy(fileData, target);
        String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
        String uploadedFileName = null;
        //이미지 파일이면
        if(MediaUtils.getMediaType(formatName)!=null){
            //썸네일을 생성한다.
            uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
        }else{
            uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
        }
        return uploadedFileName;
    }

    private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
        //파일의 이름
        String iconName = uploadPath + path + File.separator + fileName;
        //디렉토리 구분자를 /(File.separator)로 변경.
        return iconName.substring(uploadPath.length()).replace(File.separatorChar,'/');
    }

    private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
        BufferedImage sourceImg = ImageIO.read(new File(uploadPath+path,fileName));
        BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
        String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
        File newFile = new File(thumbnailName);
        String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
        ImageIO.write(destImg, formatName.toUpperCase(), newFile);

        return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar,'/');
    }

    private static String calcPath(String uploadPath){
        Calendar cal = Calendar.getInstance(); //현재 날짜 인스턴스
        //연도
        String yearPath = File.separator + cal.get(Calendar.YEAR);
        //월
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH));
        //일
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
        //디렉토리 생성
        makeDir(uploadPath, yearPath, monthPath, datePath);
        return datePath;
    }

    private static void makeDir(String uploadPath, String... paths){
        //존재하는 디렉토리인 경우, 생성하지 않음
        if(new File(paths[paths.length - 1]).exists()){
            return;
        }
        //새로운 디렉토리인 경우,
        for(String path : paths){
            File dirPath = new File(uploadPath + path);
            if(!dirPath.exists()){
                dirPath.mkdir(); //디렉토리 생성
            }
        }
    }


}
