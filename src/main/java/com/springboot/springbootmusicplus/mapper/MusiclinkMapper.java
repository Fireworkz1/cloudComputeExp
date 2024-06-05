package com.springboot.springbootmusicplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.springbootmusicplus.entity.Musiclink;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MusiclinkMapper extends BaseMapper<Musiclink> {
    @Insert("INSERT INTO musiclink ( ml_songName, ml_singer, ml_songLink, ml_lyricLink, ml_photoLink) " +
            "VALUES ( #{ml_songName}, #{ml_singer}, #{ml_songLink}, #{ml_lyricLink}, #{ml_photoLink})")
    void insertMusiclink(String ml_songName, String ml_singer, String ml_songLink, String ml_lyricLink, String ml_photoLink);
}
