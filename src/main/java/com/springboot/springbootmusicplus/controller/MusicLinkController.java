package com.springboot.springbootmusicplus.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.springbootmusicplus.common.enums.FailEnums;
import com.springboot.springbootmusicplus.common.page.PageResponse;
import com.springboot.springbootmusicplus.common.response.Response;
import com.springboot.springbootmusicplus.common.utils.PageUtil;
import com.springboot.springbootmusicplus.entity.Musiclink;
import com.springboot.springbootmusicplus.model.request.UploadInfo;
import com.springboot.springbootmusicplus.model.request.UploadRequest;
import com.springboot.springbootmusicplus.service.impl.MusiclinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


/**
 * @author linliquan
 * @description:
 * @create 2021/5/8 14:37
 */
@Slf4j
@RestController
@Api(tags = "歌曲信息")
@RequestMapping("/musicLink")
public class MusicLinkController {

    @Autowired
    private MusiclinkService musiclinkService;

    @PostMapping("/getMusicLinkList")
    @ApiOperation(value = "从数据库中获取歌曲数据，在榜单中显示", httpMethod = "POST")
    public Response<PageResponse<Musiclink>> getMusicLinkList(@RequestParam(defaultValue = "1", required = false) Integer pageNo,
                                                              @RequestParam(defaultValue = "10", required = false) Integer pageSize) {

        // 使用PageHelper分页插件
        PageUtil.startPage(pageNo, pageSize);
        List<Musiclink> musicList = musiclinkService.getMusiclinkList();
        if (CollectionUtils.isEmpty(musicList)) {
            return Response.fail(FailEnums.NOT_EXISTS_ERROR.getCode(), "查询榜单列表歌曲为空！");
        }
        log.info("查询榜单列表歌曲：{}", JSON.toJSONString(musicList));
        PageResponse<Musiclink> pageResponse = PageUtil.convertPageData(musicList);
        pageResponse.setList(musicList);
        return Response.succ(pageResponse);
    }

    @PostMapping("/getSongRearch")
    @ApiOperation(value = "歌曲搜索功能", httpMethod = "POST")
    public Response<List<Musiclink>> getSongRearch(@RequestParam(required = false) String songName) {
        log.info("搜索的歌曲名：{}", songName);
        List<Musiclink> musicList = musiclinkService.getMusiclinkInfoBySongName(songName);
        if (CollectionUtils.isEmpty(musicList)) {
            return Response.fail(FailEnums.NOT_EXISTS_ERROR.getCode(), "歌曲不存在！");
        }
        log.info("搜索到的歌曲：{}", JSON.toJSONString(musicList));
        return Response.succ(musicList);
    }
    @GetMapping("/getSongsBySinger/{singerName}")
    @ApiOperation(value = "根据歌手名字查询歌曲", httpMethod = "GET")
    public Response<List<Musiclink>> getSongsBySinger(@PathVariable String singerName) {
        List<Musiclink> songs = musiclinkService.getMusiclinkInfoBySinger(singerName);
        if (CollectionUtils.isEmpty(songs)) {
            return Response.fail(FailEnums.NOT_EXISTS_ERROR.getCode(), "没有找到该歌手的歌曲");
        }
        return Response.succ(songs);
    }
    @PostMapping("/uploadSongs")
    @ApiOperation(value = "上传歌曲", httpMethod = "POST")
    public void uploadSongs(@RequestParam("music") MultipartFile music,@RequestParam("picture") MultipartFile picture, @RequestParam("name") String name,@RequestParam("singer") String singer) {

       UploadRequest request=new UploadRequest();
       request.setMusic(music);
       request.setPicture(picture);
       request.setName(name);
       request.setSinger(singer);
        musiclinkService.uploadSongs(request);
    }

}
