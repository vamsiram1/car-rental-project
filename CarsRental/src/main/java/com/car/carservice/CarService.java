package com.car.carservice;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.car.carentity.Car;
import com.car.carentity.Location;
import com.car.carentity.UserDetails;
import com.car.carentity.UserDetailsDTO;
import com.car.carrepository.CarRepo;
import com.car.carrepository.LocationRepository;
import com.car.carrepository.UserDetailsRepo;
import com.car.mapapi.GoogleMapsClient;

@Service
public class CarService {

	@Autowired
	CarRepo carRepo;
	
	@Autowired
	LocationRepository locationRepository;

	@Autowired
	GoogleMapsClient googleMapsClient;
	
	@Autowired
	UserDetailsRepo userDetailsRep;
	
	@Autowired
	ModelMapper modelMapper;
	
	//Admin can Add the Car
	public Car addCar(Car car) {
		return carRepo.save(car);
	}

	//Admin Can delete Car
	public String deleteCar(String model) {
		List<Car> byModel = carRepo.findByModelIgnoreCase(model);
		if (byModel == null || byModel.isEmpty()) {
			return "Car Is Not Existed";
		}
		byModel.stream().filter(e -> e.getModel().equalsIgnoreCase(model)).forEach(e -> carRepo.delete(e));
		return "Car Has Been Deleted";
	}
	
	//Admin Can update Car
	public Car updateCar(int id, Car car)  {
		Car carObject = carRepo.findById(id).get();
		car.setId(carObject.getId());
		return carRepo.save(car);
	}

	//Getting car details using id
	public List<Car> gettingById(int id) {
		Optional<Car> byId = carRepo.findById(id);
		List<Car> listOfCars = List.of(byId.get());
		return listOfCars;

	}
	
	//Getting car details using model
	public List<Car> gettingByModel(String model) {
		List<Car> byModelIgnoreCase = carRepo.findByModelIgnoreCase(model);
		return byModelIgnoreCase;
	}

	

	
	

	
	//user order details
	public List<UserDetailsDTO> userOrderDetails() {
		UserDetailsDTO userDetailsDto = new UserDetailsDTO();
		modelMapper.map(UserDetails.class, userDetailsDto);
		 List<UserDetailsDTO> fetchUserDetailsWithCarModel = userDetailsRep.fetchUserDetailsWithCarModel();
		 return fetchUserDetailsWithCarModel;
	}
	
	
	//booking based on id
	public UserDetails bookCar(int carId, UserDetails userDetails) {
        Optional<Car> carOptional = carRepo.findById(carId);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            if (car==null) {
                throw new RuntimeException("Car is not available for booking!");
            }
            car.setAvailable(false); 
            userDetails.setHasCar(true);
            userDetails.setCar(car);
            return userDetailsRep.save(userDetails);
        } else {
            throw new RuntimeException("Car not found!");
        }
    }
	

    // Fetching available cars based on model and location
//	public List<Car> getCarsByModelAndLocation(String model, String location) {
//        return carRepo.findByModelAndLocation(model, location);
//    }
	
	
	
	//returning Car logic
	public String returnCar(String username) {
		UserDetails byUserNameIgnoreCase = userDetailsRep.findByUserNameIgnoreCase(username);
		if(byUserNameIgnoreCase==null) {
			throw new RuntimeException("User "+username+" havn't take any car");
		}
		byUserNameIgnoreCase.getCar().setAvailable(true);
		byUserNameIgnoreCase.getCar().setUser(null);
		carRepo.save(byUserNameIgnoreCase.getCar());
		
		userDetailsRep.delete(byUserNameIgnoreCase);
		return "User Returned Successfully";
	}

	
	
	//logic for getting coordinates using location "name"
	 @Value("${google.maps.api.key}")
	    private String googleMapsApiKey;

	    public double[] getCoordinates(String locationName) {
	        String response = googleMapsClient.getGeocode(locationName, googleMapsApiKey);
	        
	        JSONObject jsonObject = new JSONObject(response);
	        JSONObject location = jsonObject.getJSONArray("results")
	                                        .getJSONObject(0)
	                                        .getJSONObject("geometry")
	                                        .getJSONObject("location");

	        double lat = location.getDouble("lat");
	        double lon = location.getDouble("lng");

	        return new double[]{lat, lon};
	    }
	    
	    //API logic for geting locations based on location name
	    public List<Location> findNearbyLocations(String locationName, double radius) {
	        double[] coordinates = getCoordinates(locationName);
	        return locationRepository.findLocationsWithinRadius(coordinates[0], coordinates[1], radius);
	    }
	
	
	
	
	
}
