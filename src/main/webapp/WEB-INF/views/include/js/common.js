function checkImageType(fileName){
    var pattern = /jpg|gif|png|jpeg/;
    return fileName.match(pattern);
}
function getFileInfo(fullName){
    var fileName, imgsrc, getLink, fileLink;
    if(checkImageType(fullName)){
        imgsrc="/spring02/upload/displayFile?fileName="+fullName;
        fileLink=fullName.substr(14);
        var front=fullName.substr(0,12);
        var end=fullName.substr(14);
        getLink="/spring02/upload/displayFile?fileName="+front+end;
    }else{
        fileLink=fullName.substr(12);
        getLink="/spring02/upload/displayFile?fileName="+fullName;
    }
    fileName=fileLink.substr(fileLink.indexOf("_")+1);
    return {fileName:fullName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
}