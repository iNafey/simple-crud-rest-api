package co2103.hw2.model;

import javax.persistence.*;

@Entity
@Table(name="Maintenance")
public class Maintenance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	private String supervisorId;
	
	
	@OneToOne
	@JoinColumn(name="fk_maintenance")
	private Flight flight;
	
	
	
	public String toString() {
		return "Maintenance "+id+" done on Flight "+flight.getFlightNo()+". Work: "+description+". Supervised by "+supervisorId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flightId) {
		this.flight = flightId;
	}
	
	
}
