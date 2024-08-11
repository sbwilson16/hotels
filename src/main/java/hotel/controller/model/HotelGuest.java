package hotel.controller.model;

import hotel.entity.Guest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HotelGuest {
	
        private Long guestId;
		private String guestFirstName;
		private String guestLastName;
		private String guestEmail;
		
		public HotelGuest(Guest guest) {
			
			guestId = guest.getGuestId();
			guestFirstName = guest.getGuestFirstName();
			guestLastName = guest.getGuestLastName();
			guestEmail = guest.getGuestEmail();		
		}

		
}
