package com.springboot.springbootmusicplus.controller;

import com.springboot.springbootmusicplus.common.enums.FailEnums;
import com.springboot.springbootmusicplus.common.response.Response;
import com.springboot.springbootmusicplus.entity.Musiclink;
import com.springboot.springbootmusicplus.model.request.SongRearchRequest;
import com.springboot.springbootmusicplus.service.impl.MusiclinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @PostMapping("/getSongRearch")
    @ApiOperation(value = "歌曲搜索功能", httpMethod = "POST")
    public Response<List<Musiclink>> getSongRearch(@RequestBody SongRearchRequest request) {

        List<Musiclink> musicList = musiclinkService.getMusiclinkInfoBySongName(request.getSongName());
        if (CollectionUtils.isEmpty(musicList)) {
            return Response.fail(FailEnums.NOT_EXISTS_ERROR.getCode(), "歌曲不存在！");
        }
        return Response.succ(musicList);
    }

}
