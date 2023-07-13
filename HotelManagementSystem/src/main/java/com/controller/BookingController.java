package com.controller;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.BookingRepository;
import com.dao.CustomerRepository;
import com.dao.RoomRepository;
import com.entity.Booking;
import com.entity.BookingRequest;
import com.entity.Customer;
import com.entity.Room;
import com.service.BookingService;

@RequestMapping("/booking")
@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	 private CustomerRepository cusRepository;
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')" )
	@PostMapping("/updatebooking/{bookingId}")
	public ResponseEntity<String> updateBooking(@PathVariable("bookingId") Long bookingId,
	                                            @RequestBody Booking updatedBooking) {
	    return bookingService.updateBooking(bookingId, updatedBooking);
	}
 	 
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')" )
	 // this method is used to accept the request
	  @PostMapping("/accept/{bookingId}")
	    public ResponseEntity<String> acceptBooking(@PathVariable Long bookingId) {
	        bookingService.acceptBooking(bookingId);
	        String message = "Booking accepted";
	        return ResponseEntity.ok(message);
	    }
	 
	  // this is using for sending a request
	
		@PostMapping("/request/{roomNumber}")
	  public ResponseEntity<String> handleBookingRequest(@PathVariable("roomNumber") int roomNumber, @RequestBody Customer customer) {
	      bookingService.bookingRequest(roomNumber, customer);
	      String message = "Request Sent Successfully";
	      return ResponseEntity.ok(message);
	  }
	 
	  // this method is used to search all the pending request
		//@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
		@GetMapping("/pendingBooking")
	    public ResponseEntity<List<Booking>> getPendingBookings() {
	        List<Booking> pendingBookings = bookingService.getPendingBookings();
	        //System.out.println("Pending Data "+pendingBookings);
	        return new ResponseEntity<>(pendingBookings, HttpStatus.OK);
	    }
		@GetMapping("/confirmBooking")
	    public ResponseEntity<List<Booking>> getConfirmBookings() {
	        List<Booking> confirmBookings = bookingService.getConfirmBookings();
	       // System.out.println("Confrim Booking "+confirmBookings);
	        return new ResponseEntity<>(confirmBookings, HttpStatus.OK);
	    }
		
	 
	//	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	 @PostMapping("/createBooking")
	public ResponseEntity<String> bookingCreate(@RequestBody BookingRequest bookingRequest)
	{
		 	Booking booking = bookingRequest.getBooking();
		    Customer customer = bookingRequest.getCustomer();
		    bookingService.createBooking(booking, customer);
		    return ResponseEntity.ok("Booking Successfully");
	}
	 
		@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')" )
		@GetMapping("/getAllBooking")
		public List<Booking> getAllBooking(){
			
			return bookingService.checkAllBooking();
		}


}

