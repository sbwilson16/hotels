package hotel.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotel.controller.model.HotelData;
import hotel.controller.model.HotelEmployee;
import hotel.controller.model.HotelGuest;
import hotel.dao.EmployeeDao;
import hotel.dao.GuestDao;
import hotel.dao.HotelDao;
import hotel.entity.Employee;
import hotel.entity.Guest;
import hotel.entity.Hotel;


@Service
public class HotelService {

	@Autowired 
	private HotelDao hotelDao;
	
	@Autowired
	private GuestDao guestDao;
	
	@Autowired 
	private EmployeeDao employeeDao; 
	
	public HotelData saveHotel(HotelData hotelData) {
		
		Long hotelId = hotelData.getHotelId();
			
			Hotel hotel = findOrCreateHotel(hotelId);
			copyHotelFields(hotel, hotelData);
			
			Hotel dbHotel = hotelDao.save(hotel);
			
			return new HotelData(dbHotel);
			
		}

		private void copyHotelFields(Hotel hotel, HotelData hotelData) {
			
			hotel.setHotelId(hotelData.getHotelId());
			hotel.setHotelName(hotelData.getHotelName());
			hotel.setHotelAddress(hotelData.getHotelAddress());
			hotel.setHotelCity(hotelData.getHotelCity());
			hotel.setHotelState(hotelData.getHotelState());
			hotel.setHotelZip(hotelData.getHotelZip());
			hotel.setHotelPhone(hotelData.getHotelPhone());
		}
		
		private Hotel findOrCreateHotel(Long HotelId) {
			Hotel hotel;
			
			if(Objects.isNull(HotelId)) {
				hotel = new Hotel();
			}else {
				hotel = findHotelById(HotelId);
			}
			return hotel;
		}
		public Hotel findHotelById(Long hotelId){
			return hotelDao.findById(hotelId).orElseThrow(() -> new NoSuchElementException("Hotel with Id=" + hotelId + " dose not exist."));
			
		}
		@Transactional(readOnly = false)
		public HotelEmployee saveEmployee(Long hotelId, HotelEmployee hotelEmployee) {
		
			Hotel hotel = findHotelById(hotelId);
			Long employeeId = hotelEmployee.getEmployeeId();
			Employee employee = findOrCreateEmployee(hotelId, employeeId);
			
			copyEmployeeFields(employee, hotelEmployee);
			employee.setHotel(hotel);
			hotel.getEmployees().add(employee);
			
			return new HotelEmployee(employeeDao.save(employee));

	}
		private void copyEmployeeFields(Employee employee, HotelEmployee hotelEmployee) {
			employee.setEmployeeId(hotelEmployee.getEmployeeId());
			employee.setEmployeeFristName(hotelEmployee.getEmployeeFirstName());
			employee.setEmployeeLastName(hotelEmployee.getEmployeeLastName());
			employee.setEmployeePhone(hotelEmployee.getEmployeePhone());
			employee.setEmployeeJobTitle(hotelEmployee.getEmployeeJobTitle());
			
			}
		private Employee findOrCreateEmployee(Long hotelId, Long employeeId) {
			if (Objects.isNull(employeeId)) {
				return new Employee();
				
			}
				return findingEmployeeById(hotelId, employeeId);
			
	}

		private Employee findingEmployeeById(Long hotelId, Long employeeId) {
			Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> 
			new NoSuchElementException("No employee found"));
			if (employee.getHotel().getHotelId() != hotelId) {
				throw new IllegalArgumentException("Employee with Id" + employee + " does not work at this Hotel.");
			}
			return employee; 
		}
		@Transactional(readOnly = false)
		public List<HotelData> retrieveAllHotels(){
			List<Hotel> hotels = hotelDao.findAll();
			List<HotelData> result = new LinkedList<>();
			for (Hotel hotel : hotels) {
				HotelData psd = new HotelData(hotel);
						psd.getGuests().clear();
				        psd.getEmployees().clear();
				        result.add(psd);     
			}
			  return result; 
		}
		
		
		@Transactional(readOnly = false)
		public HotelGuest saveGuest(Long hotelId, HotelGuest hotelGuest) {
			Hotel hotel = findHotelById(hotelId);
			Long guestId = hotelGuest.getGuestId();
			Guest guest = findOrCreateGuest(hotelId, guestId);
			
			copyGuestFields(guest, hotelGuest);
			guest.getHotels().add(hotel);
			hotel.getGuests().add(guest);
			
			return new HotelGuest(guestDao.save(guest));
			
		}
		private void copyGuestFields(Guest guest, HotelGuest hotelGuest) {
			guest.setGuestId(hotelGuest.getGuestId());
			guest.setGuestFirstName(hotelGuest.getGuestFirstName());
			guest.setGuestLastName(hotelGuest.getGuestLastName());
			guest.setGuestEmail(hotelGuest.getGuestEmail());
		}
		private Guest findOrCreateGuest(Long HotelId, Long GuestId) {
			if (Objects.isNull(GuestId)) {
				return new Guest();
				
			}
				return findGuestById(HotelId,GuestId);
			
		}
		private Guest findGuestById(long hotelId, long GuestId) {
			Guest guest = guestDao.findById(GuestId).orElseThrow(() ->
			new NoSuchElementException("No Guest found"));
			boolean flag = false;
			for (Hotel Hotel : guest.getHotels()){
				if (Hotel.getHotelId() == hotelId) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				throw new IllegalArgumentException("Guest with id" + GuestId + " is not avalible ");
			} 
			return guest; 
		}
		@Transactional(readOnly = true)
		public HotelData retrieveHotelById(Long hotelId) {
			Hotel hotel = findHotelById(hotelId);
			return new HotelData(hotel);
		}
		@Transactional(readOnly = false)
		public void deleteHotelById(Long hotelId) {
			Hotel hotel = findHotelById(hotelId);
			hotelDao.delete(hotel);
		}

		
	
}
