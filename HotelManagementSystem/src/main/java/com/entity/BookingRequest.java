package com.entity;

public class BookingRequest {
	
	Booking booking;
	Customer customer;
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "BookingRequest [booking=" + booking + ", customer=" + customer + "]";
	}
	public BookingRequest(Booking booking, Customer customer) {
		super();
		this.booking = booking;
		this.customer = customer;
	}
	public BookingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
