package com.hacettepe.rehabsoft.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
public class FileOperationHelper {

    // This function is used to create a directory in given url, and add filename its end
    public static String createURLWithDirectory(String savingDirectory, String createdDirectoryName, String fileName, String fileType){
        String createdDirectoryURL = savingDirectory + createdDirectoryName;
        File file = new File(createdDirectoryURL);
        boolean bool = file.mkdir();
        return createdDirectoryURL+ "/"+fileName+"."+fileType ;
    }

    // pop file type. append popped string to second parameter which is newFileName
    public static String popFileTypeFromFileName(String filename, StringBuilder newFileName){
        List<String> listToGetFileType =  new LinkedList<>(Arrays.asList(Objects.requireNonNull(filename).split("\\.")));
        String fileType = listToGetFileType.remove(listToGetFileType.size()-1);
        for(String words: listToGetFileType){
            newFileName.append(words);
        }
        return fileType;
    }
    // pop file type. append popped string to second parameter which is newFileName
    public static String popFileTypeFromFileName(String filename){
        List<String> listToGetFileType =  new LinkedList<>(Arrays.asList(Objects.requireNonNull(filename).split("\\.")));
        return listToGetFileType.remove(listToGetFileType.size()-1);
    }

    // This function is used to save any file by giving location url and its name added in url.
    public static String saveFileByDirectory(MultipartFile image, String directory) throws Exception{
        if(image.getContentType() != null){

            log.warn("Dosya kaydetme kismina girdi. Content type: " + image.getContentType());

            byte[] bytes = image.getBytes();
            Path path = Paths.get(directory );
            Files.write(path, bytes);

            return directory;

        }
        else return null;
    }

    // This method is delete folder with its data contains by using PATH.
    public static void deleteDirectoryByPath(String folderPath) throws IOException {

        log.warn("Klasor silme kismina girdi");

        if(Files.exists(Paths.get(folderPath))){
            File mediaFolder = new File(folderPath);
            FileUtils.deleteDirectory(mediaFolder);
        }
    }

    // if read file is problem because of the path, it removes strings until to resource directory.
    public static String splitPathAndMergeStartFromStaticDirectory(String savedUrl) {
        List<String> urlArray = new ArrayList<>(Arrays.asList(savedUrl.split("/")));
        urlArray.remove(0);
        urlArray.remove(0);
        urlArray.remove(0);
        urlArray.remove(0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(urlArray.get(0));
        urlArray.remove(0);

        for(String str:urlArray){
            stringBuilder.append("/").append(str);
        }

        return stringBuilder.toString();
    }

    //This function is used to convert BufferedImage to byte array
    public static byte[] convertToByteArray(BufferedImage bufferedImage, String url) throws IOException {
        String type = popFileTypeFromFileName(url);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, type, baos);
        return baos.toByteArray();
    }
}
