package com.github.dicomflow.dicomflowjavalib.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Created by ricardobarbosa on 09/07/17.
 */

public class FileUtil {
    public static String encodeFileToBase64Binary(String filePath) throws IOException {
        String encodedFile = null;

        File file = new File(filePath);
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        encodedFile = Base64.encodeBase64String(bytes);

        return encodedFile;
    }

    public static byte[] decodeFileToBinaryBase64(String encodedFile){
        return Base64.decodeBase64(encodedFile);
    }
}
