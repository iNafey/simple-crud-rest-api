package co2103.hw2.model;

import javax.persistence.*;

import co2103.hw2.*;

@Entity
@Table(name="Ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ticketNumber;
	
	private String passengerName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "fk_plane_ticket")
	private Flight usedOn;
	
	
	public String toString() {
		return "Ticket Number "+ticketNumber+" belongs to Passenger "+passengerName+". Ticket valid on Flight Number "+usedOn.getFlightNo();
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	
	public Flight getUsedOn() {
		return usedOn;
	}

	public void setUsedOn(Flight usedOn) {
		this.usedOn = usedOn;
	}
	
	
}
