package com.ether.data.service;

import com.ether.data.dto.ApiLogDto;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ApiService {
    PageInfo<Map> apiLog(ApiLogDto dto);
}
