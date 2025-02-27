package com.car.carentity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;

	private String model;
	private String brand;
	private double pricePerDay;
	private boolean available;

	@OneToOne(mappedBy = "car")
	@JsonManagedReference
	@JsonIgnore
	private UserDetails user;

	@ManyToOne
	@JoinColumn(name = "location_id")
	@JsonIgnore
	private Location location;

}
