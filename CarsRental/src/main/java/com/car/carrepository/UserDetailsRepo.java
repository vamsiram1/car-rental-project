package com.car.carrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.car.carentity.UserDetails;
import com.car.carentity.UserDetailsDTO;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {

	@Query("SELECT u FROM UserDetails u JOIN u.car c WHERE c.model IS NOT NULL")
	List<UserDetailsDTO> fetchUserDetailsWithCarModel();
	
	@Query("SELECT c FROM UserDetails c WHERE LOWER(c.username) = LOWER(:username)")
	UserDetails findByUserNameIgnoreCase(@Param("username") String username);

}
