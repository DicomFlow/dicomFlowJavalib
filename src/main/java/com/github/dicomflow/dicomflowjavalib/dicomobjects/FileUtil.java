package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by ricardobarbosa on 09/07/17.
 */

class FileUtil {
    public static String encodeFileToBase64Binary(String filePath){
        String encodedFile = null;
        try {
            File file = new File(filePath);
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedFile = Base64.encodeBase64String(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedFile;
    }

    public static byte[] decodeFileToBinaryBase64(String encodedFile){
        try {
            byte[] bytea = Base64.decodeBase64(encodedFile);
            return bytea;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
