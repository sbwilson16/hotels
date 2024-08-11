package hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.entity.Hotel;

public interface HotelDao extends JpaRepository<Hotel, Long> {

	
}
