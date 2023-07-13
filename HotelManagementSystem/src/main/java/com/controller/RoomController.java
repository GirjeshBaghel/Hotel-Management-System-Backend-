package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.RoomRepository;
import com.entity.Booking;
import com.entity.Room;

import com.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomRepository roomRepository;
	
//	@Autowired
//	public CustomerService customerService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllRoom")
	public List<Room> getAllRoom(){
		
		return roomService.getAllRoom();
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	@DeleteMapping("/deleteRoomByRoomNo/{id}")
	public String deleteRoom(@PathVariable("id") int room_no) {
		
		return roomService.deleteRoom(room_no);
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	@PostMapping("/createRoom")
	ResponseEntity<Room> createRoom(@RequestBody Room room){
		
		return new ResponseEntity<Room>(roomService.saveRoom(room),HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/availableRoom")
    public ResponseEntity<List<Room>> getPendingRoom() {
        List<Room> pendingRoom = roomRepository.findpendingRoom();
        //System.out.println("Pending Data "+pendingBookings);
        return new ResponseEntity<>(pendingRoom, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	@GetMapping("/bookedRoom")
    public ResponseEntity<List<Room>> getBookedRoom() {
        List<Room> bookedRoom = roomRepository.findbookingRoom();
        //System.out.println("Pending Data "+pendingBookings);
        return new ResponseEntity<>(bookedRoom, HttpStatus.OK);
    }
	
	
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	@GetMapping("/getByRoomNo/{roomNo}")
	public ResponseEntity<?> getByRoomNo(@PathVariable("roomNo") int roomNo) {
	    Room rooms = roomRepository.findByRoomNo(roomNo);
	    if (rooms == null) {
	        String errorMessage = "No rooms available with the specified room number.";
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	    } else {
	        return ResponseEntity.ok(rooms);
	    }
	}

	
}
