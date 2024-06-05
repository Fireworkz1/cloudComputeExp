package com.springboot.springbootmusicplus.entity;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@Data
public class Musiclink implements Serializable {
    private static final long serialVersionUID = -5149909946147476012L;
    @ApiModelProperty("mlId")
    private Integer mlId;
    @ApiModelProperty("mlSongname")
    private String mlSongname;
    @ApiModelProperty("mlSinger")
    private String mlSinger;
    @ApiModelProperty("mlSonglink")
    private String mlSonglink;
    @ApiModelProperty("mlLyriclink")
    private String mlLyriclink;
    @ApiModelProperty("mlPhotolink")
    private String mlPhotolink;
}