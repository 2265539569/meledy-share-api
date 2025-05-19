package com.cpujazz.front.controller;

import com.cpujazz.front.pojo.dto.RoomAddDto;
import com.cpujazz.front.pojo.result.ResponseResult;
import com.cpujazz.front.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/list")
    public ResponseResult list() {
        return roomService.list();
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody RoomAddDto roomAddDto) {
        return roomService.add(roomAddDto);
    }
}
