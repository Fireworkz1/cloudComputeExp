package com.springboot.springbootmusicplus.service.impl;

import com.springboot.springbootmusicplus.dao.operator.MusiclinkOperator;
import com.springboot.springbootmusicplus.entity.Musiclink;
import com.springboot.springbootmusicplus.mapper.MusiclinkMapper;
import com.springboot.springbootmusicplus.service.IMusiclinkService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/8 17:41
 */
@Service
public class MusiclinkService implements IMusiclinkService {

    @Autowired
    private MusiclinkOperator musiclinkOperator;
    @Resource
    private MusiclinkMapper musiclinkMapper;

    @Override
    public Musiclink getMusiclinkInfoById(Integer id) {
        return musiclinkOperator.getMusiclinkInfoById(id);
    }

    public List<Musiclink> getMusiclinkInfoBySongName(String songName) {
        return musiclinkOperator.getMusiclinkInfoBySongName(songName);
    }

    public List<Musiclink> getMusiclinkList() {
        List<Musiclink> list = musiclinkOperator.list();
        return CollectionUtils.isNotEmpty(list) ? list : null;
    }
    @Override
    public List<Musiclink> getMusiclinkInfoBySinger(String singer) {
        return musiclinkOperator.getMusiclinkInfoBySinger(singer);
    }

    @Override
    public void uploadSongs(Musiclink musiclink) {
        musiclinkMapper.insertMusiclink(musiclink.getMlId(), musiclink.getMlSongname(), musiclink.getMlSinger(), musiclink.getMlSonglink(), musiclink.getMlLyriclink(), musiclink.getMlPhotolink());
    }
}
