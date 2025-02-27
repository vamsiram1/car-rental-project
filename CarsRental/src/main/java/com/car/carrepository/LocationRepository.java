package com.car.carrepository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.car.carentity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM location " +
                   "WHERE earth_distance(ll_to_earth(:lat, :lon), ll_to_earth(latitude, longitude)) <= :radius * 1000",
            nativeQuery = true)
    List<Location> findLocationsWithinRadius(@Param("lat") double latitude, 
                                             @Param("lon") double longitude, 
                                             @Param("radius") double radius);
}
