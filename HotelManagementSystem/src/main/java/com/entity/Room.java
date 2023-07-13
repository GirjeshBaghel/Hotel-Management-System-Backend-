package com.entity;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Room {
	
		@Id
		private int roomNo;
	    private int bed;
	    private boolean hasAC;
	    private boolean hasTV;
	    private boolean hasWIFI;
	    private boolean hasGizer;
	    private boolean hasPhone;
	    private String room_type;
	    private Long pricePerDay;
	    private boolean isActive;
	    

	    @ManyToOne
	    @JoinColumn(name = "booking_id")
	    @JsonIgnoreProperties("room")
	    private Booking booking;
	    
	    @ManyToOne()
		@JsonIgnoreProperties("room")
	    @JoinColumn(name="customerId")
	   private Customer customer;


		public int getRoom_no() {
			return roomNo;
		}


		public void setRoom_no(int room_no) {
			this.roomNo = room_no;
		}


		public Booking getBooking() {
			return booking;
		}


		public void setBooking(Booking booking) {
			this.booking = booking;
		}


		public int getBed() {
			return bed;
		}


		public void setBed(int bed) {
			this.bed = bed;
		}


		public boolean isHasAC() {
			return hasAC;
		}


		public void setHasAC(boolean hasAC) {
			this.hasAC = hasAC;
		}


		public boolean isHasTV() {
			return hasTV;
		}


		public void setHasTV(boolean hasTV) {
			this.hasTV = hasTV;
		}


		public boolean isHasWIFI() {
			return hasWIFI;
		}


		public void setHasWIFI(boolean hasWIFI) {
			this.hasWIFI = hasWIFI;
		}


		public boolean isHasGizer() {
			return hasGizer;
		}


		public void setHasGizer(boolean hasGizer) {
			this.hasGizer = hasGizer;
		}


		public boolean isHasPhone() {
			return hasPhone;
		}


		public void setHasPhone(boolean hasPhone) {
			this.hasPhone = hasPhone;
		}


		public String getRoom_type() {
			return room_type;
		}


		public void setRoom_type(String room_type) {
			this.room_type = room_type;
		}


		public Long getPricePerDay() {
			return pricePerDay;
		}


		public void setPricePerDay(Long pricePerDay) {
			this.pricePerDay = pricePerDay;
		}


		public boolean isActive() {
			return isActive;
		}


		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}


		public Customer getCustomer() {
			return customer;
		}


		public void setCustomer(Customer customer) {
			this.customer = customer;
		}


		@Override
		public String toString() {
			return "Room [room_no=" + roomNo + ", bed=" + bed + ", hasAC=" + hasAC + ", hasTV=" + hasTV + ", hasWIFI="
					+ hasWIFI + ", hasGizer=" + hasGizer + ", hasPhone=" + hasPhone + ", room_type=" + room_type
					+ ", pricePerDay=" + pricePerDay + ", isActive=" + isActive + ", customer=" + customer + "]";
		}


		public Room() {
			super();
			// TODO Auto-generated constructor stub
		}    

	   			    
}
