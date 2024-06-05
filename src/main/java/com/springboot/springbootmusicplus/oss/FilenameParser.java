package com.springboot.springbootmusicplus.oss;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class FilenameParser {
    public String parse(String originalFilename){
        // 提取文件扩展名
        String fileExtension = "";
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex != -1) {
            fileExtension = originalFilename.substring(dotIndex);
        }
        // 提取文件名（不包括扩展名）
        String filenameWithoutExtension = (dotIndex == -1) ? originalFilename : originalFilename.substring(0, dotIndex);
        // 将文件名转换为 MD5 码
        String md5Filename = DigestUtils.md5DigestAsHex(filenameWithoutExtension.getBytes());
        // 组合 MD5 码和文件扩展名，形成新的文件名
        String newFilename = md5Filename + fileExtension;
        System.out.println("New file name: " + newFilename);
        return newFilename;
    }
}
