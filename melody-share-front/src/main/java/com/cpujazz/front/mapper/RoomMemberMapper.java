package com.cpujazz.front.mapper;

import com.cpujazz.front.pojo.entity.RoomMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMemberMapper {

    Integer countRoomMembers(Long roomId);

    int insertUserIdByRoomId(RoomMember roomMember);

    Long selectRoomIdByUserId(Long userId);

    RoomMember selectByUserIdAndRoomId(Long userId, Long roomId);

    RoomMember selectByUserId(Long userId);

    boolean deleteById(Long id);
}
