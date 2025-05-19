package com.cpujazz.front.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMemberMapper {

    Integer countRoomMembers(Long roomId);
}
