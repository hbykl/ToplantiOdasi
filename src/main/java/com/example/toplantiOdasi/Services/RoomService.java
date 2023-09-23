package com.example.toplantiOdasi.Services;

import com.example.toplantiOdasi.Classes.Room;
import com.example.toplantiOdasi.Repositorys.RoomRepository;
import com.example.toplantiOdasi.ServiceImpl.RoomImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements RoomImpl {

    RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository){
        this.roomRepository=roomRepository;
    }

    @Override
    public List<Room> getAll(){
        return roomRepository.findAll();
    }

    @Override
    public Room getRoom(long id) {
        Optional<Room>room=roomRepository.findById(id);
        Room room1=null;
        if(room.isPresent()){
            room1=room.get();
        }
        else System.out.println("kullanıcı yok");
        return room1;
    }

    @Override
    public void saveRoom(Room room){
        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Room room){
        roomRepository.delete(room);
    }


}
