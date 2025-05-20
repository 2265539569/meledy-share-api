package com.cpujazz.front.mapper;

import com.cpujazz.front.pojo.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    List<Room> selectAllList();

    int insertOne(Room room);

    Room selectByRoomName(String roomName);

    Room selectByRoomId(Long roomId);

    Room selectByUserId(Long userId);
}
