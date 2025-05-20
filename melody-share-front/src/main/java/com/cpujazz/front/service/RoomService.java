package com.cpujazz.front.service;

import com.cpujazz.front.pojo.dto.RoomAddDto;
import com.cpujazz.front.pojo.result.ResponseResult;

public interface RoomService {

    ResponseResult list();

    ResponseResult add(RoomAddDto roomAddDto);

    ResponseResult join(Long roomId, String roomPassword);

    ResponseResult info();

    ResponseResult exit();
}
