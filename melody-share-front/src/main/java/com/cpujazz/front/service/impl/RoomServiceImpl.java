package com.cpujazz.front.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.cpujazz.common.enumeration.ResponseMessage;
import com.cpujazz.common.enumeration.RoomPermission;
import com.cpujazz.front.mapper.RoomMapper;
import com.cpujazz.front.mapper.RoomMemberMapper;
import com.cpujazz.front.mapper.UserMapper;
import com.cpujazz.front.pojo.dto.RoomAddDto;
import com.cpujazz.front.pojo.dto.RoomDto;
import com.cpujazz.front.pojo.entity.Room;
import com.cpujazz.front.pojo.entity.RoomMember;
import com.cpujazz.front.pojo.entity.User;
import com.cpujazz.front.pojo.result.ResponseResult;
import com.cpujazz.front.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomMapper roomMapper;
    private final UserMapper userMapper;
    private final RoomMemberMapper roomMemberMapper;

    public List<RoomDto> RoomToDto(List<Room> roomList) {
        List<RoomDto> roomDtoList = BeanUtil.copyToList(roomList, RoomDto.class);
        for (RoomDto roomDto : roomDtoList) {
            User user = userMapper.selectByUserId(roomDto.getOwnerId());
            roomDto.setOwnerName(user.getNickname());
            roomDto.setCount(roomMemberMapper.countRoomMembers(roomDto.getId()));
        }
        return roomDtoList;
    }

    @Override
    public ResponseResult list() {
        List<Room> roomList = roomMapper.selectList();
        return ResponseResult.success(RoomToDto(roomList));
    }

    @Override
    public ResponseResult add(RoomAddDto roomAddDto) {
        // 重复房间名字检查
        Room room = roomMapper.selectOneByRoomName(roomAddDto.getRoomName());
        if (!Objects.isNull(room)) {
            return ResponseResult.error(ResponseMessage.ERROR_ROOM_NAME_EXISTS);
        }
        room = BeanUtil.copyProperties(roomAddDto, Room.class);
        if (!room.getRoomPassword().isEmpty()) {
            room.setIsPublic(RoomPermission.PRIVATE.getCode());
        } else room.setIsPublic(RoomPermission.PUBLIC.getCode());
        room.setCreatedAt(new Date());
        room.setUpdatedAt(new Date());
        Long userId = StpUtil.getLoginIdAsLong();
        room.setOwnerId(userId);
        int count = roomMapper.insertOne(room);
        if (count == 1) {
            return ResponseResult.success(ResponseMessage.SUCCESS_ROOM_INSERT);
        }
        return ResponseResult.error();
    }

    @Override
    @Transactional
    public ResponseResult join(Long roomId, String roomPassword) {
        // 1. 检查房间是否存在
        Room room = roomMapper.selectOneByRoomId(roomId);
        if (Objects.isNull(room)) {
            return ResponseResult.error(ResponseMessage.ERROR_ROOM_NAME_NOT_EXISTS);
        }

        // 2. 检查房间密码是否正确
        if (!room.getRoomPassword().equals(roomPassword)) {
            return ResponseResult.error(ResponseMessage.ERROR_ROOM_PASSWORD);
        }

        // 3. 获取当前用户ID
        Long userId = StpUtil.getLoginIdAsLong();

        // 4. 检查用户是否已经存在于其他房间中
        Long existingRoomId = roomMemberMapper.selectRoomIdByUserId(userId);
        if (existingRoomId != null && !existingRoomId.equals(roomId)) {
            return ResponseResult.error(ResponseMessage.ERROR_USER_ALREADY_IN_ROOM);
        }

        // 5. 检查用户是否已经存在于目标房间中
        RoomMember existingMember = roomMemberMapper.selectByUserIdAndRoomId(userId, roomId);
        if (existingMember != null) {
            return ResponseResult.error(ResponseMessage.ERROR_ROOM_USER_EXISTS);
        }

        // 6. 创建新的房间成员记录
        RoomMember roomMember = new RoomMember();
        roomMember.setRoomId(roomId);
        roomMember.setUserId(userId);
        roomMember.setJoinTime(new Date());

        // 7. 插入房间成员记录
        int count = roomMemberMapper.insertUserIdByRoomId(roomMember);
        if (count == 1) {
            return ResponseResult.success(ResponseMessage.SUCCESS_ROOM_USER_JOIN);
        }

        return ResponseResult.error();
    }

    @Override
    public ResponseResult info() {
        Long userId = StpUtil.getLoginIdAsLong();
        RoomMember roomMember = roomMemberMapper.selectByUserId(userId);
        if (Objects.isNull(roomMember)) {
            return ResponseResult.error(ResponseMessage.ERROR_ROOM_NOT_EXISTS);
        }
        Room room = roomMapper.selectOneByRoomId(roomMember.getRoomId());
        RoomDto roomDto = BeanUtil.copyProperties(room, RoomDto.class);
        return ResponseResult.success(roomDto);
    }

    @Override
    public ResponseResult exit() {
        Long userId = StpUtil.getLoginIdAsLong();
        RoomMember roomMember = roomMemberMapper.selectByUserId(userId);
        if (Objects.isNull(roomMember)) {
            return ResponseResult.error();
        }
        boolean isDeleted = roomMemberMapper.deleteById(roomMember.getId());
        if (isDeleted) {
            return ResponseResult.success(ResponseMessage.SUCCESS_ROOM_USER_EXIT);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult search(String roomName) {
        List<Room> roomList = roomMapper.selectListByRoomName(roomName);
        if (roomList.isEmpty()) {
            return ResponseResult.error();
        }
        return ResponseResult.success(RoomToDto(roomList));
    }
}
