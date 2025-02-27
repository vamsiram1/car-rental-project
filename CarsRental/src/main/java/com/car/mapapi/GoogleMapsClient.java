package com.car.mapapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleMapsClient", url = "https://maps.googleapis.com/maps/api/geocode")
public interface GoogleMapsClient {
	@GetMapping(value = "/json")
    String getGeocode(@RequestParam("address") String address, 
            @RequestParam("key") String apiKey);
}
