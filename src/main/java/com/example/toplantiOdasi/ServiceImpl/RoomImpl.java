package com.example.toplantiOdasi.ServiceImpl;

import com.example.toplantiOdasi.Classes.Room;

import java.util.List;

public interface RoomImpl {

    List<Room> getAll();

    Room getRoom(long id);
    void saveRoom(Room room);

    void deleteRoom(Room room);
}
