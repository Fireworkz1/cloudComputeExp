package com.springboot.springbootmusicplus.model.request;

import com.springboot.springbootmusicplus.entity.Musiclink;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadRequest {

    String name;
    String singer;
    MultipartFile music;
    MultipartFile picture;

}
