package hotel.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotelId;

	private String hotelName;
	private String hotelAddress;
	private String hotelCity;
	private String hotelState;
	private String hotelZip;
	private String hotelPhone;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "hotel_guest", joinColumns = @JoinColumn(name = "hotel_id"), inverseJoinColumns = @JoinColumn(name = "guest_id"))
	private Set<Guest> guests = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Employee> employees = new HashSet<>();

}
