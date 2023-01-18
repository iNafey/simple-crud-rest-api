package co2103.hw2.model;

import java.util.*;


import javax.persistence.*;


@Entity
@Table(name="Crew")
public class Crew {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int crewId;
	
	private String name;
	
	private String jobTitle;
	
	
	@ManyToMany(mappedBy="crew")
	private Set<Flight> beenOn = new HashSet<>();

	
	public String toString() {
		
		String flightNumbers = "";
		
		List<Flight> flightList = new ArrayList<>(beenOn);
		
		for (Flight f : flightList) {
			if (f != flightList.get(flightList.size()-1)) flightNumbers += f.getFlightNo()+", ";
			
			else flightNumbers += f.getFlightNo();
			}	
			
		return "Crew member "+name+"'s ID is "+crewId+" and their Job Title is "+jobTitle+". They have been on flights (number): "+flightNumbers;
	}
	public int getCrewId() {
		return crewId;
	}

	public void setCrewId(int crewId) {
		this.crewId = crewId;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	

	public Set<Flight> getBeenOn() {
		return beenOn;
	}

	public void setBeenOn(Set<Flight> beenOn) {
		this.beenOn = beenOn;
	}
	
	public void removeFlights(Crew c) {
		
		c.beenOn.clear();
	}


}
