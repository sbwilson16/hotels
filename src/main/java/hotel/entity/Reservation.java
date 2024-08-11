package hotel.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable 
@Data
@NoArgsConstructor 
public class Reservation {
  private Long hotelId;
  private Long guestId;
 


public Reservation(Reservation reservation) {
	this.hotelId = reservation.hotelId;
	this.guestId = reservation.guestId;
  }
}
