package co2103.hw2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co2103.hw2.model.*;
import co2103.hw2.repo.*;

@SpringBootApplication
public class Hw2Application implements ApplicationRunner{
	

	
	@Autowired
	private TicketRepository ticketRepo;

	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private CrewRepository crewRepo;
	
	@Autowired
	private MaintenanceRepository maintenanceRepo;
	
	//public static List<Object> object = new ArrayList<>();
	
	public static void main(String[] args) {
		SpringApplication.run(Hw2Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//The order in which instances are created and saved in the db is critical to managing the instances and tables.
		
		
		//Create the 1st ticket
		Ticket ticket1 = new Ticket();
		ticket1.setPassengerName("Spiderman");
		ticket1 = ticketRepo.save(ticket1);
		
		//Create the 2nd ticket to show the multiplicity works
		Ticket ticket2 = new Ticket();
		ticket2.setPassengerName("Spiderman");
		ticket2 = ticketRepo.save(ticket2);
		
		Ticket ticket3 = new Ticket();
		ticket3.setPassengerName("John Smith");
		ticket3 = ticketRepo.save(ticket3);
		
		Ticket ticket4 = new Ticket();
		ticket4.setPassengerName("Eva Smith");
		ticket4 = ticketRepo.save(ticket4);
		
		
		//Now create the 1st flight to assign a ticket to it
		Flight flight1 = new Flight();

		flight1.setAircraftId("QA773");
		flight1.setOrigin("Heathrow");
		flight1.setDestination("LA");
		flight1.setNoOfPassengers(10);
		flight1.setNoOfCrew(2);

		
		//Save the flight before saving ticket1 and also before setting a ticket to flight (leads to transient error so follow this order)
		flight1 = flightRepo.save(flight1);
		
		//Now that flight1 exists in db, the flight can be assigned to the ticket 
		ticket1.setUsedOn(flight1);
	
		//Save ticket1 changes so that the ticket can then be assigned to the flight1
		ticket1 = ticketRepo.save(ticket1);
		
		
		
		//Set the ticket1 used to the flight1 and save the changes to flight1 instance
		flight1.getTickets().add(ticket1);
		flight1 = flightRepo.save(flight1);
		
		//Create the 2nd flight to show the multiplicity work again
		Flight flight2 = new Flight();
		flight2.setAircraftId("QA999");
		flight2.setOrigin("LA");
		flight2.setDestination("Heathrow");
		flight2.setNoOfPassengers(20);
		flight2.setNoOfCrew(6);
		
		//Save the initial creation of flight2 - it can't be assigned a ticket since the flight has to first be assigned to a ticket 
		flight2 = flightRepo.save(flight2);
		
		
		
		ticket2.setUsedOn(flight2);
		ticket2 = ticketRepo.save(ticket2);
		
		ticket3.setUsedOn(flight2);
		ticket3 = ticketRepo.save(ticket3);
		
		//Now set the ticket to flight2 and save the updated changes in db
		flight2.getTickets().add(ticket2);
		flight2.getTickets().add(ticket3);
		flight2 = flightRepo.save(flight2);
		
		Flight flight3 = new Flight();
		flight3.setAircraftId("BA123");
		flight3.setOrigin("Heathrow");
		flight3.setDestination("Dubai");
		flight3.setNoOfPassengers(50);
		flight3.setNoOfCrew(7);
		
		//Save the initial creation of flight2 - it can't be assigned a ticket since the flight has to first be assigned to a ticket 
		flight3 = flightRepo.save(flight3);
		
		ticket4.setUsedOn(flight3);
		ticket4 = ticketRepo.save(ticket4);
		
		flight3.getTickets().add(ticket4);
		flight3 = flightRepo.save(flight3);
		
		Maintenance work1 = new Maintenance();
		work1.setFlight(flight1);
		work1.setDescription("Seats cleaned and carpet dry cleaned. Tyres changed and food replaced. Filled the fuel tank to 70%");
		work1.setSupervisorId("EMP2002");
		
		work1 = maintenanceRepo.save(work1);
		
		
		flight1.setMaintenance(work1);
		flight1 = flightRepo.save(flight1);
		
		
		Maintenance work2 = new Maintenance();
		work2.setFlight(flight2);
		work2.setDescription("Fixed toilet, Cleaned the front window and filled up the fuel tank to 80%");
		work2.setSupervisorId("EMP192123");
		
		work2 = maintenanceRepo.save(work2);
		
		
		flight2.setMaintenance(work2);
		
		Maintenance work3 = new Maintenance();
		work3.setFlight(flight3);
		work3.setDescription("Cleaned the windows and filled up the fuel tank to 90%");
		work3.setSupervisorId("EMP20123");
		
		work3 = maintenanceRepo.save(work3);
		
		
		flight3.setMaintenance(work3);
		
		
		//Create a crew member
		Crew pilot = new Crew();
		pilot.setName("Captain");
		pilot.setJobTitle("Pilot");

		
		//We have the same pilot for both flights - 2nd one is a returning flight
		flight1.getCrew().add(pilot);
		flight2.getCrew().add(pilot);
		
		
		pilot.getBeenOn().add(flight1);
		pilot.getBeenOn().add(flight2);
		
		
		//Add pilot in db
		pilot = crewRepo.save(pilot);
		
		//Create another crew member to show multiplicity example.
		Crew co_pilot = new Crew();
		co_pilot.setName("Jack Hudderfield");
		co_pilot.setJobTitle("Co-Pilot");

		
		//Now the inverse of the relationship, we add the crew member to each flight
		flight1.getCrew().add(co_pilot);
		flight2.getCrew().add(co_pilot);
		
		//Finally save the instantiation of co-pilot to db need to do this last because the relationship has to be set between crew and flight
		co_pilot.getBeenOn().add(flight1);
		co_pilot.getBeenOn().add(flight2);
		co_pilot = crewRepo.save(co_pilot);

		//We added 2 crew members to both the flights so these changes need to be saved
		flight1 = flightRepo.save(flight1);
		flight2 = flightRepo.save(flight2);
		
		Crew pilot2 = new Crew();
		pilot2.setName("Mr Scrooge");
		pilot2.setJobTitle("Pilot");
		
		pilot2.getBeenOn().add(flight3);
		pilot2 = crewRepo.save(pilot2);
		
		flight3.getCrew().add(pilot2);
		flight3 = flightRepo.save(flight3);
		
	}

}
