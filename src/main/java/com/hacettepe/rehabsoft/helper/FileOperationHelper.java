package com.hacettepe.rehabsoft.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileOperationHelper {

    private final GoogleDriveHelper googleDriveHelper;

    // This function is used to create a directory in given url, and add filename its end
    public String createURLWithDirectory(String createdDirectoryName, String fileName, String fileType){
        return createdDirectoryName+"/"+fileName+"."+fileType ;
    }

    // pop file type. append popped string to second parameter which is newFileName
    public String popFileTypeFromFileName(String filename, StringBuilder newFileName){
        List<String> listToGetFileType =  new LinkedList<>(Arrays.asList(Objects.requireNonNull(filename).split("\\.")));
        String fileType = listToGetFileType.remove(listToGetFileType.size()-1);
        for (int i = 0; i < listToGetFileType.size(); i++) {
            newFileName.append(listToGetFileType.get(i));
            if(i!=listToGetFileType.size()-1){
                newFileName.append('.');
            }
        }
        return fileType;
    }
    // pop file type. append popped string to second parameter which is newFileName
    public static String popFileTypeFromFileName(String filename){
        List<String> listToGetFileType =  new LinkedList<>(Arrays.asList(Objects.requireNonNull(filename).split("\\.")));
        return listToGetFileType.remove(listToGetFileType.size()-1);
    }

    // This function is used to save any file by giving location url and its name added in url.
    public String saveFileByDirectory(MultipartFile image, String directory) throws Exception{
        if(image.getContentType() != null){
            log.warn("Dosya kaydetme kismina girdi. Content type: " + image.getContentType());
            googleDriveHelper.saveFile(image, directory);
            return directory;
        }
        else return null;
    }

    // This method is delete folder with its data contains by using PATH.
    public void deleteDirectoryByPath(String folderPath) throws Exception {

        log.warn("Klasor silme kismina girdi");
        googleDriveHelper.deleteFolder(folderPath);
    }

    public void deleteFileByPath(String filePath){

        log.warn("Dosya silme kismina girdi");
        googleDriveHelper.deleteFile(filePath);
    }

    public byte[] readFileAsByte(String filePath) {
        return googleDriveHelper.readFileAsByteArray(filePath);
    }

    public Long getSizeOfFile(String filePath){
        return googleDriveHelper.getSizeOfFile(filePath);
    }

    //This function is used to convert BufferedImage to byte array
    public static byte[] convertToByteArray(BufferedImage bufferedImage, String url) throws IOException {
        String type = popFileTypeFromFileName(url);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, type, baos);
        return baos.toByteArray();
    }
}
