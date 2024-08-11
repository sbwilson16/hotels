package hotel.controller.model;

import hotel.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class HotelEmployee {
	
	

		private Long employeeId;
		private String employeeFirstName;
		private String employeeLastName;
		private String employeePhone;
		private String employeeJobTitle;
		
		public HotelEmployee (Employee employee) {
			
		   employeeId = employee.getEmployeeId();
		   employeeFirstName = employee.getEmployeeFristName();
		   employeeLastName = employee.getEmployeeLastName();
		   employeePhone = employee.getEmployeePhone(); 
		   employeeJobTitle = employee.getEmployeeJobTitle();
			
		}
	 
}
