package com.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long customerId;
	private String cusName;
	private String cusEmail;
	private String cusPhone;
	private LocalDateTime checkInDateTime;
	private LocalDateTime checkOutDateTime;
	private String address;
	private String city;
	private String country;
	private String roomType;
	private int totalguest;
	private int adults;
	private int children;
	private String decumentId;
	
	private boolean isActive;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();
	
	
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings.clear();
        if (bookings != null) {
            this.bookings.addAll(bookings);
            for (Booking booking : bookings) {
                booking.setCustomer(this);
            }
        }
    }	

	    public void addBooking(Booking booking) {
	        bookings.add(booking);
	        booking.setCustomer(this);
	    }

	    public void removeBooking(Booking booking) {
	        bookings.remove(booking);
	        booking.setCustomer(null);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getTotalguest() {
		return totalguest;
	}

	public void setTotalguest(int totalguest) {
		this.totalguest = totalguest;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public String getDecumentId() {
		return decumentId;
	}

	public void setDecumentId(String decumentId) {
		this.decumentId = decumentId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", cusName=" + cusName + ", cusEmail=" + cusEmail + ", cusPhone="
				+ cusPhone + ", checkInDateTime=" + checkInDateTime + ", checkOutDateTime=" + checkOutDateTime
				+ ", address=" + address + ", city=" + city + ", country=" + country + ", roomType=" + roomType
				+ ", totalguest=" + totalguest + ", adults=" + adults + ", children=" + children + ", decumentId="
				+ decumentId + ", isActive=" + isActive + ", bookings=" + bookings + "]";
	}

	public Customer(long customerId, String cusName, String cusEmail, String cusPhone, LocalDateTime checkInDateTime,
			LocalDateTime checkOutDateTime, String address, String city, String country, String roomType,
			int totalguest, int adults, int children, String decumentId, boolean isActive, List<Booking> bookings) {
		super();
		this.customerId = customerId;
		this.cusName = cusName;
		this.cusEmail = cusEmail;
		this.cusPhone = cusPhone;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
		this.address = address;
		this.city = city;
		this.country = country;
		this.roomType = roomType;
		this.totalguest = totalguest;
		this.adults = adults;
		this.children = children;
		this.decumentId = decumentId;
		this.isActive = isActive;
		this.bookings = bookings;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

		
	
}
