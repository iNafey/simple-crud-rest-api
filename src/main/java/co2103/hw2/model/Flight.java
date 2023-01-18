package co2103.hw2.model;

import java.util.*;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import co2103.hw2.repo.CrewRepository;

@Entity
@Table (name="Flight")
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightNo;
	
	private String aircraftId;
	
	private int noOfPassengers;
	
	private int noOfCrew;
	
	private String origin;
	
	private String destination;
	
	@OneToMany(mappedBy="usedOn", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Ticket> flight_tickets = new ArrayList<Ticket>();
	
	
	@ManyToMany(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name="crew_on_flight",
    joinColumns = { @JoinColumn(name = "fk_flight") },
    inverseJoinColumns = { @JoinColumn(name = "fk_crew") })
	private Set<Crew> crew = new HashSet<Crew>();
	
	
	@OneToOne(mappedBy="flight", cascade=CascadeType.REMOVE)
	private Maintenance maintenance;
	
	
	
	public String toString() {
		
		String crewNames = "";
		
		List<Crew> crewList = new ArrayList<>(crew);
		
		for (Crew member : crew) {
			if (member != crewList.get(crewList.size()-1)) crewNames += member.getName()+", ";
			
			else crewNames += member.getName();
			}
		
		String ticketNumbers = "";
		for (Ticket ticket : flight_tickets) {
			if (ticket != flight_tickets.get(flight_tickets.size()-1)) ticketNumbers += ticket.getTicketNumber()+", ";
			else ticketNumbers += ticket.getTicketNumber();
		}
		
		return "Flight "+flightNo+" is flown by Aircraft "+aircraftId+" with "+noOfPassengers+" passengers and "+noOfCrew+" crew members including: "+crewNames+". The flight is from "+origin+" to "+destination+"<br><br> Valid ticket numbers: "+ticketNumbers;
	}


	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int i) {
		this.flightNo = i;
	}

	public String getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(String aircraftId) {
		this.aircraftId = aircraftId;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public int getNoOfCrew() {
		return noOfCrew;
	}

	public void setNoOfCrew(int noOfCrew) {
		this.noOfCrew = noOfCrew;
	}


	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	
	public Set<Crew> getCrew() {
		return crew;
	}

	public void setCrew(Set<Crew> crew) {
		this.crew = crew;
	}
	
    public void removeCrew(Crew c) {
    	

        for (Flight flight : c.getBeenOn()) {
             flight.crew.remove(c);
        }
    }
	

    
	public List<Ticket> getTickets() {
		return flight_tickets;
	}


	public void setTickets(List<Ticket> flight_tickets) {
		this.flight_tickets = flight_tickets;
	}

	
	public Maintenance getMaintenance() {
		return maintenance;
	}


	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}
	

	
}
