package hotel.controllor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hotel.controller.model.HotelData;
import hotel.controller.model.HotelEmployee;
import hotel.controller.model.HotelGuest;
import hotel.service.HotelService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hotels")
@Slf4j

public class HotelControllor {
	@Autowired
	private  HotelService hotelService;
	
	@PostMapping("/hotels")
	@ResponseStatus(code = HttpStatus.CREATED)
	public HotelData insertHotel(
	@RequestBody HotelData hotelData) { 
		log.info("Creating hotel {}", hotelData);
		return hotelService.saveHotel(hotelData);
	}
   
   @PutMapping("/hotels/{hotelId}")
   public HotelData updateHotel
   (@PathVariable Long hotelId,
		   @RequestBody HotelData hotelData) {
	   
	   hotelData.setHotelId(hotelId);
	   log.info("updating hotel {}", hotelData);
	   
	   return hotelService.saveHotel(hotelData);
}
  @PostMapping ("/{hotelId}/employee")
  @ResponseStatus(code = HttpStatus.CREATED)
  public HotelEmployee insertEmployee
          (@PathVariable Long hotelId,
		  @RequestBody HotelEmployee hotelEmployee) {
		  log.info("Creating employee {} for hotel with ID ()", hotelEmployee.getEmployeeId(), hotelId);
  
  return hotelService.saveEmployee(hotelId, hotelEmployee);
  }
  
  @PostMapping("/{hotelId}/guest")
  @ResponseStatus(code = HttpStatus.CREATED)
  public  HotelGuest insertGuest
       (@PathVariable Long hotelId,
		  @RequestBody HotelGuest hotelGuest) {
	  log.info("CreatingGuest () for hotel with ID ()", hotelGuest.getGuestId(), hotelId);
	  
	  return hotelService.saveGuest(hotelId, hotelGuest);
  }
  @GetMapping
  public List<HotelData> retrieveAllHotels() {
	  log.info("Retrieving all Hotels.");
	  return hotelService.retrieveAllHotels();
	  
  }
  @GetMapping("/{hotelId}")
  public HotelData retrieveHotelById(@PathVariable Long hotelId) {
	  log.info("Retrieving Hotel with ID= {}", hotelId);
	  return hotelService.retrieveHotelById(hotelId);
	  
  }
  @DeleteMapping("/{hotelId}")
  public Map<String, String> deleteHotelById
  (@PathVariable Long hotelId){
  log.info("Deleting hotel with Id= " + hotelId + ".");
  hotelService.deleteHotelById(hotelId);
  return Map.of("Message", "Delection of contributor with ID=" + hotelId + " was successful.");
  }

}
