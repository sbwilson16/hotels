package hotel.controller.model;

import java.util.HashSet;
import java.util.Set;

import hotel.entity.Employee;
import hotel.entity.Guest;
import hotel.entity.Hotel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HotelData {
	
		private Long hotelId;
		private String hotelName;
		private String hotelAddress;
		private String hotelCity;
		private String hotelState;
		private String hotelZip;
		private String hotelPhone;
		private Set<HotelGuest> guests = new HashSet<>();
		private Set<HotelEmployee> employees = new HashSet<>();
		
		
		public HotelData(Hotel hotel) {
			hotelId = hotel.getHotelId();
			hotelName = hotel.getHotelName();
			hotelAddress = hotel.getHotelAddress();
			hotelCity = hotel.getHotelCity();
			hotelState = hotel.getHotelState();
			hotelZip = hotel.getHotelZip();
			hotelPhone = hotel.getHotelPhone();
			
			for (Guest guest : hotel.getGuests()) {
				guests.add(new HotelGuest(guest));
			}
			for (Employee employee : hotel.getEmployees()) {
				HotelEmployee hotelEmployee = new HotelEmployee(employee);
				employees.add(hotelEmployee);
			}
		}
		
}
