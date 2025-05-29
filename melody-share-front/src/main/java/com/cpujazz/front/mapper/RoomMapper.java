package com.cpujazz.front.mapper;

import com.cpujazz.front.pojo.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    List<Room> selectList();

    int insertOne(Room room);

    Room selectOneByRoomName(String roomName);

    Room selectOneByRoomId(Long roomId);

    List<Room> selectListByRoomName(String roomName);
}
