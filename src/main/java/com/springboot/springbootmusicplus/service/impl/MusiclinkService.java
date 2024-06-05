package com.springboot.springbootmusicplus.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.springboot.springbootmusicplus.common.response.Response;
import com.springboot.springbootmusicplus.dao.operator.MusiclinkOperator;
import com.springboot.springbootmusicplus.entity.Musiclink;
import com.springboot.springbootmusicplus.model.request.UploadRequest;
import com.springboot.springbootmusicplus.oss.FilenameParser;
import com.springboot.springbootmusicplus.oss.OSSUploader;
import com.springboot.springbootmusicplus.service.IMusiclinkService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private OSSUploader ossUploader;
    @Autowired
    FilenameParser filenameParser;
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
    @Transactional
    public Response<Object> uploadSongs(UploadRequest uploadRequest) {
        if (uploadRequest.getMusic().isEmpty()) {
            return Response.fail("File is empty");
        }
        try {
            Musiclink musiclink=new Musiclink();
            //上传歌曲
            String originalSongName=uploadRequest.getMusic().getOriginalFilename();
            String newSongName=filenameParser.parse(originalSongName);
            String songurl=ossUploader.uploadFile(uploadRequest.getMusic(),newSongName,"music");

            //上传图片
            if(!uploadRequest.getPicture().isEmpty()){
                String originalPicName=uploadRequest.getPicture().getOriginalFilename();
                String newPicName=filenameParser.parse(originalPicName);
                String picurl=ossUploader.uploadFile(uploadRequest.getMusic(),newPicName,"pic");
                musiclink.setMlPhotolink(picurl);
            }else {
                musiclink.setMlPhotolink("https://fireworkz.oss-cn-wulanchabu.aliyuncs.com/pic/blank.jpg");
            }


            musiclink.setMlSinger(uploadRequest.getSinger());
            musiclink.setMlSongname(uploadRequest.getName());
            musiclink.setMlSonglink(songurl);

            musiclinkOperator.insert(musiclink);
            return Response.succ();
        } catch (RuntimeException | ClientException e) {
            e.printStackTrace();
            return Response.fail(500, "File upload failed: " + e.getMessage());
        }

    }


}
