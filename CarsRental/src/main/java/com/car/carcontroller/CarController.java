package com.car.carcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.carentity.Car;
import com.car.carentity.Location;
import com.car.carentity.UserDetails;
import com.car.carentity.UserDetailsDTO;
import com.car.carservice.CarService;



@RestController
public class CarController {
	@Autowired
	CarService carService;
	
	
	
	@PostMapping("admin/AddCar")
	public Car addCar(@RequestBody Car car) {
		return carService.addCar(car);
		
		
	}
	
	
	@DeleteMapping("admin/DeleteCar/{model}")
	public String delete(@PathVariable String model) {
		return carService.deleteCar(model);
		
	}
	
	@PutMapping("admin/path/{id}")
	public Car updateCar(@PathVariable int id, @RequestBody Car car)  {
		return carService.updateCar(id, car);
	}
	
	@GetMapping("/admin/getbyid/{id}")
	public List<Car> gettingById(@PathVariable int id){
		return carService.gettingById(id);
	}
	
	@GetMapping("/admin/getbymodel/{model}")
	public List<Car> gettingByModel(@PathVariable String model){
		return carService.gettingByModel(model);
	}
	
	
	@GetMapping("/userorderdetails")
	public List<UserDetailsDTO> userOrderDetails() {
		return carService.userOrderDetails();
	}
	
	
	
	
	//API for user to fetch by location and model
//	@GetMapping("/searchbylocation")
//    public ResponseEntity<List<Car>> getCarsByModelAndLocation(@RequestParam String model,@RequestParam String location) {
//        return ResponseEntity.ok(carService.getCarsByModelAndLocation(model, location));
//    }
	
	
	
	// API to book a car
    @PostMapping("/book/{carId}")
    public UserDetails bookCar(@PathVariable int carId, @RequestBody UserDetails userDetails) {
        return carService.bookCar(carId, userDetails);
    }
	
	
	//API to return a car
    @DeleteMapping("/returningCar")
    public String returnCar(String username) {
		return carService.returnCar(username);
	}
    
    @GetMapping("/find/location")
    public List<Location> findNearbyLocations(String locationName, double radius) {
    	return carService.findNearbyLocations(locationName, radius);
    }
    
}
