package com.entity;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table
@Entity
public class Booking {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long booking_id;
	@NotBlank
    private LocalDateTime checkInDateTime;
    @NotBlank
	private LocalDateTime checkOutDateTime;
    @NotBlank
    private String bookingType;
    @NotBlank
    private double totalprice;
    @NotBlank
    private String status;
    @NotBlank
    private int noOfperson;
    @NotBlank
    private int roomNo;
    
    private long nights;
    
    private String paymentStatus;
    
    
  
    
//    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
//	@JsonIgnoreProperties("booking")
//	private List<OtherPerson> person;
//    
	@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

   
	@JsonIgnore
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("booking")
	private List<Room> rooms;


		public String getPaymentStatus() {
			return paymentStatus;
		}

		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}

		
	
	
	 public List<Room> getRooms() {
			return rooms;
		}

		public void setRooms(List<Room> rooms) {
			this.rooms = rooms;
		}

		
	public long getBooking_id() {
		return booking_id;
	}

	public long getNights() {
		return nights;
	}

	public void setNights(long nights) {
		this.nights = nights;
	}
	public void setBooking_id(long booking_id) {
		this.booking_id = booking_id;
	}

	public LocalDateTime getCheckInDateTime() {
		return checkInDateTime;
	}

	public void setCheckInDateTime(LocalDateTime checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public LocalDateTime getCheckOutDateTime() {
		return checkOutDateTime;
	}

	public void setCheckOutDateTime(LocalDateTime checkOutDateTime) {
		this.checkOutDateTime = checkOutDateTime;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public int getNoOfperson() {
		return noOfperson;
	}

	public void setNoOfperson(int noOfperson) {
		this.noOfperson = noOfperson;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	@Override
	public String toString() {
		return "Booking [booking_id=" + booking_id + ", checkInDateTime=" + checkInDateTime + ", checkOutDateTime="
				+ checkOutDateTime + ", bookingType=" + bookingType + ", totalprice=" + totalprice + ", noOfperson="
				+ noOfperson + ", roomNo=" + roomNo + ", nights=" + nights + ", status=" + status;
	}

	public Booking(long booking_id, LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime, String bookingType,
			double totalprice, int noOfperson, int roomNo, long nights, String status, Customer customer,
			List<Room> rooms) {
		super();
		this.booking_id = booking_id;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
		this.bookingType = bookingType;
		this.totalprice = totalprice;
		this.noOfperson = noOfperson;
		this.roomNo = roomNo;
		this.nights = nights;
		this.status = status;
		this.customer = customer;
		this.rooms = rooms;
	}

    

	
	
}
