package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	 @Query("SELECT b FROM Booking b WHERE b.status = 'Pending'")
	    List<Booking> findPendingBookings();
	 
	 @Query("SELECT b FROM Booking b WHERE b.status = 'Confirm'")
	    List<Booking> findConfirmBookings();
	 
	 
}

