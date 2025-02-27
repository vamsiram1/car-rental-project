package com.car.carentity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDetails {

	@Id
	@GeneratedValue
	@JsonIgnore
	private int userId;
	private String username;
	private boolean hasCar;
	private String address;
	private int hasCarExtraDays;
	private int amountToPay;
	private int extraAmountCharged;
	private Date fromDate;
	private Date returnDate;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id") 
    @JsonBackReference 
    private Car car;
}
