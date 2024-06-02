package com.springboot.springbootmusicplus.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("歌曲收藏请求")
public class CollectIdRequest {

    private String songName;
    private Integer songId;
    private Integer userId;
}
