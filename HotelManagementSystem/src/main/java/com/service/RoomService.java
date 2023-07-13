package com.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Exception.ResourceNotFoundException;
import com.dao.RoomRepository;
import com.entity.Room;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	public Room saveRoom(Room room)
	{
		return roomRepository.save(room);
	}
	
	
	public List<Room> getAllRoom() {
		List<Room> room = (List<Room>) roomRepository.findAll();
		return room;
	}
	
	public String deleteRoom(int room_no) {
		String message=null;
		
		Optional<Room> room = roomRepository.findById(room_no);
		if(room.isPresent()) {
			roomRepository.deleteById(room_no);
			message=new String("Room deleted successfully!!");
		}
		else {
			throw new ResourceNotFoundException("Room"," Id",room_no);
		}
		return message;
	}

	
	public Room updateRoom(int room_no, Room room) {
		Room existingRoom = roomRepository.findById(room_no).orElseThrow(()->new ResourceNotFoundException("Room", "id", room_no));
		existingRoom.setRoom_no(room.getRoom_no());
		existingRoom.setBed(room.getBed());
		existingRoom.setHasGizer(room.isHasGizer());
		existingRoom.setHasTV(room.isHasTV());
		existingRoom.setHasPhone(room.isHasPhone());
		existingRoom.setHasWIFI(room.isHasWIFI());
		existingRoom.setHasAC(room.isHasAC());
		existingRoom.setPricePerDay(room.getPricePerDay());
		return existingRoom;
		
	}


}
