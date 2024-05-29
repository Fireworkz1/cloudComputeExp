package com.springboot.springbootmusicplus.service;

import com.springboot.springbootmusicplus.entity.Musiclink;

import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/8 17:40
 */
public interface IMusiclinkService {
    List<Musiclink> getMusiclinkInfoBySinger(String singer);
    Musiclink getMusiclinkInfoById(Integer id);
}
