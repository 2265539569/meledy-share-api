package com.cpujazz.front.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.cpujazz.common.enumeration.ResponseMessage;
import com.cpujazz.common.enumeration.RoomPermission;
import com.cpujazz.common.enumeration.RoomStatus;
import com.cpujazz.front.mapper.RoomMapper;
import com.cpujazz.front.mapper.RoomMemberMapper;
import com.cpujazz.front.mapper.UserMapper;
import com.cpujazz.front.pojo.dto.RoomAddDto;
import com.cpujazz.front.pojo.dto.RoomDto;
import com.cpujazz.front.pojo.entity.Room;
import com.cpujazz.front.pojo.entity.User;
import com.cpujazz.front.pojo.result.ResponseResult;
import com.cpujazz.front.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomMapper roomMapper;
    private final UserMapper userMapper;
    private final RoomMemberMapper roomMemberMapper;

    @Override
    public ResponseResult list() {
        List<Room> roomList = roomMapper.selectAllList();
        List<RoomDto> roomDtoList = BeanUtil.copyToList(roomList, RoomDto.class);
        for (RoomDto roomDto : roomDtoList) {
            User user = userMapper.selectByUserId(roomDto.getOwnerId());
            roomDto.setOwnerName(user.getNickname());
            roomDto.setCount(roomMemberMapper.countRoomMembers(roomDto.getId()));
        }
        return ResponseResult.success(roomDtoList);
    }

    @Override
    public ResponseResult add(RoomAddDto roomAddDto) {
        // 重复房间名字检查
        Room room = roomMapper.selectByRoomName(roomAddDto.getRoomName());
        if (!Objects.isNull(room)) {
            return ResponseResult.error(ResponseMessage.ERROR_ROOM_EXISTS);
        }
        room = BeanUtil.copyProperties(roomAddDto, Room.class);
        if (!room.getRoomPass().isEmpty()) {
            room.setIsPublic(RoomPermission.PRIVATE.getCode());
        } else room.setIsPublic(RoomPermission.PUBLIC.getCode());
        room.setCreatedAt(new Date());
        room.setUpdatedAt(new Date());
        Long userId = StpUtil.getLoginIdAsLong();
        room.setOwnerId(userId);
        room.setStatus(RoomStatus.INACTIVE.getStatus());
        int count = roomMapper.insertOne(room);
        if (count == 1) {
            return ResponseResult.success(ResponseMessage.SUCCESS_ROOM_INSERT);
        }
        return ResponseResult.error();
    }
}
