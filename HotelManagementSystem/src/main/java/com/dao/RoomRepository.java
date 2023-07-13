package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Room;

@Repository
public interface RoomRepository  extends JpaRepository<Room, Integer>
{
	Room findByRoomNo(int roomNo);
	
	
	 @Query("SELECT r FROM Room r WHERE r.isActive = 0")
	    List<Room> findpendingRoom();
	 
	 @Query("SELECT r FROM Room r WHERE r.isActive = 1")
	    List<Room> findbookingRoom();
	 
	 
	 
}
