package com.car.carrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.car.carentity.Car;
@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {
	@Query("SELECT c FROM Car c WHERE LOWER(c.model) = LOWER(:model)")
	List<Car> findByModelIgnoreCase(@Param("model") String model);
	
	@Query("SELECT c FROM Car c WHERE c.model = :model AND c.location = :location AND c.available = true")
    List<Car> findByModelAndLocation(@Param("model") String model, @Param("location") String location);


	
}
