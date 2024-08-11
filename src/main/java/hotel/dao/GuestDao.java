package hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.entity.Guest;

public interface GuestDao extends JpaRepository<Guest, Long> {

}
