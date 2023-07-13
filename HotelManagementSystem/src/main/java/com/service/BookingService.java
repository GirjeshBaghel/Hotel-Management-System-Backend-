package com.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.AdminRepo;
import com.dao.BookingRepository;
import com.dao.CustomerRepository;
import com.dao.RoomRepository;
import com.entity.Admin;
import com.entity.Booking;
import com.entity.Customer;
import com.entity.EmailService;

import com.entity.Room;
import com.entity.SmsService;

@Service
public class BookingService {
	
	

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private CustomerRepository customerRepository;
		
	@Autowired
	private AdminRepo adminRepo;
	
	// send the request to admin 
	public void bookingRequest(int roomNumber, Customer customer) {
	    Booking booking = new Booking();
	    booking.setRoomNo(roomNumber);
	    booking.setStatus("Pending");
	    LocalDateTime checkInDateTime = customer.getCheckInDateTime();
	    LocalDateTime checkOutDateTime = customer.getCheckOutDateTime();
	    booking.setCheckInDateTime(checkInDateTime);
	    booking.setCheckOutDateTime(checkOutDateTime);
	    Room room = roomRepository.findByRoomNo(roomNumber);
	    long numberOfNights = Duration.between(checkInDateTime, checkOutDateTime).toDays();
	    double totalPrice = numberOfNights * room.getPricePerDay(); // Assuming the price per night is 2000
	    
	    booking.setNights(numberOfNights);
	    booking.setTotalprice(totalPrice);
	    Optional<Customer> existingCustomer = customerRepository.findByCusEmail(customer.getCusEmail());
	    if (existingCustomer.isPresent()) {
	        customer = existingCustomer.get();
	    } else {
	        customer = customerRepository.save(customer);
	    }
	    customer.setCheckInDateTime(checkInDateTime);
	    customer.setCheckOutDateTime(checkOutDateTime);
	    booking.setCustomer(customer);	
	    customer.getBookings().add(booking); 
	    bookingRepository.save(booking);
	    
	    if (room != null) {
	        room.setBooking(booking);
	        room.setCustomer(customer);
	        roomRepository.save(room);
	    }
	}

	 // get All Pending Bookings
	 public List<Booking> getPendingBookings() {
	        return bookingRepository.findPendingBookings();
	    }

	 
	 // accepting the customer request
	 public void acceptBooking(Long bookingId) {
		    Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
	        if (optionalBooking.isPresent()) {
	            Booking booking = optionalBooking.get();
	            Customer customer = booking.getCustomer();
	            customer.setActive(true);
	            booking.setStatus("Confirm");
	            List<Room> rooms = booking.getRooms();
	            if (rooms != null) {
	                for (Room room : rooms) {
	                    room.setActive(true);
	                }
	            }

	            bookingRepository.save(booking);
	            	            
	            EmailService emailService = new EmailService("girjeshbaghel62586@gmail.com", "xavljjywsnffwrcp","smtp.gmail.com", "587");
		        emailService.sendBookingConfirmationEmail(customer.getCusEmail(), booking); // Send booking confirmation email
		        SmsService smsService = new SmsService();
		        System.out.println("Sms method Working");
		         smsService.sendSms(customer.getCusPhone(), "Hello Aniket");
		       
	        }
	       
	    }
	 
	 
	 // update details of customer
	 public ResponseEntity<String> updateBooking(Long bookingId, Booking updatedBooking) {
		    Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		    if (optionalBooking.isPresent()) {
		        Booking booking = optionalBooking.get();
		        booking.setBookingType(updatedBooking.getBookingType());
		        booking.setTotalprice(updatedBooking.getTotalprice());
		        booking.setStatus(updatedBooking.getStatus());
		        booking.setNoOfperson(updatedBooking.getNoOfperson());
		        Customer updatedCustomer = updatedBooking.getCustomer();
		        if (updatedCustomer != null) {
		            Customer customer = booking.getCustomer();
		            if (customer == null) {
		                customer = new Customer();
		                customer.setBookings(Collections.singletonList(booking));
		            }
		            customer.setAddress(updatedBooking.getCustomer().getAddress());
		            customer.setCity(updatedBooking.getCustomer().getCity());
		            customer.setCountry(updatedBooking.getCustomer().getCountry());
		            customer.setRoomType(updatedBooking.getCustomer().getRoomType());
		            customer.setTotalguest(updatedBooking.getCustomer().getTotalguest());
		            customer.setAdults(updatedBooking.getCustomer().getAdults());
		            customer.setChildren(updatedBooking.getCustomer().getChildren());
		            customer.setDecumentId(updatedBooking.getCustomer().getDecumentId());
		            customerRepository.save(customer);
		            booking.setCustomer(customer);
		        }
		        bookingRepository.save(booking);

		        return ResponseEntity.ok("Booking updated successfully.");
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found.");
		    }
		}
	 
	 // this method is used to Offline Booking
	 public void createBooking(Booking booking, Customer customer) {
		    Optional<Customer> existingCustomer = customerRepository.findByCusEmail(customer.getCusEmail());
		    if (existingCustomer.isPresent()) {
		        customer = existingCustomer.get();
		    } else {
		        customer = customerRepository.save(customer);
		    }
		    
		    booking.setCustomer(customer);
		    customer.setCheckInDateTime(booking.getCheckInDateTime());
		    customer.setCheckOutDateTime(booking.getCheckOutDateTime());
		    customerRepository.save(customer);
		    bookingRepository.save(booking);
		    Room room = roomRepository.findByRoomNo(booking.getRoomNo());
		    long numberOfNights = Duration.between(booking.getCheckInDateTime(), booking.getCheckOutDateTime()).toDays();
		    double totalPrice = numberOfNights * room.getPricePerDay(); 
		    booking.setNights(numberOfNights);
		    booking.setTotalprice(totalPrice);
		    room.setActive(true);
		    room.setCustomer(customer);
		    room.setBooking(booking);
		    roomRepository.save(room);
		    System.out.print("Sms Method is Running10");
		    EmailService emailService = new EmailService("girjeshbaghel62586@gmail.com", "xavljjywsnffwrcp","smtp.gmail.com", "587");
		      emailService.sendBookingConfirmationEmail(customer.getCusEmail(), booking);
	        // Send booking confirmation email
	        System.out.print("Sms Method is Running6");
	        SmsService smsService = new SmsService();
	         smsService.sendSms(customer.getCusPhone(), "Hello Aniket");

		}
	 
		public List<Booking> checkAllBooking() {
		List<Booking> booking = (List<Booking>) bookingRepository.findAll();
		return booking;
	}
	 
	  //get All Confirm Booking
	 public List<Booking> getConfirmBookings() {
	        return bookingRepository.findConfirmBookings();
	    }
	
	 
	 

	
//	public Booking bookRoom(int room_id,long customerId,Booking booking)
//	{
//		Room room = roomRepository.findById(room_id).get();
//		
//		Customer customer = customerRepository.findById(customerId).get();
//		
//		 if (room != null && customer != null) {
//		        // Associate the room and customer with the booking
//			 ArrayList<Room> bookedRooms = new ArrayList<>();
//			    bookedRooms.add(room);
//			    booking.setRoom(bookedRooms);
//		        booking.setCustomer(customer);
//		        
//		        
//		        return booking;
//		    }
//		
//		return booking;
//		
//	}
	 
//		public Booking bookRoom(int room_no, long adminId,Booking booking)
//		{
//			Room room = roomRepository.findById(room_no).get();
//			Admin customer = adminRepo.findById(adminId).get();
//			if(room != null && customer !=null)
//			{
//				//booking.setRoom(room);
//				//booking.setAdmin(customer);
//				booking.setTotalprice(room.getPricePerDay());
	//
//				booking.setStatus("Booked");
//				Booking bookedRoom = bookingRepository.save(booking); 
//				return bookedRoom;
//			}
//			return null;
//		}
	//	
//		public Booking bookRoom1(int room_no, String cusName, String cusEmail, String cusPhone) {
//		    Room room = roomRepository.findById(room_no).orElse(null);
//		    
//		    if (room != null) {
//		                
//		        Booking booking = new Booking();
//		     //   booking.setRoom(room);
//		       // booking.setTotalprice(room.getPricePerDay());
//		      //  booking.setStatus("Booked");
//		        
//		        Customer customer = new Customer();
//		        customer.setCusName(cusName);
//		        customer.setCusEmail(cusEmail);
//		        customer.setCusPhone(cusPhone);
//		        customerRepository.save(customer);
//		        booking.setCustomer(customer);
//		        
//		        Booking bookedRoom = bookingRepository.save(booking);
//		        return bookedRoom;
//		    }
//		    
//		    return null;
//		}

	
}
