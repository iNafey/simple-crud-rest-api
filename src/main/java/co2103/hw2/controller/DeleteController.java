package co2103.hw2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co2103.hw2.model.*;
import co2103.hw2.repo.*;

@Controller
public class DeleteController {
	
	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private CrewRepository crewRepo;
	
	@Autowired
	private MaintenanceRepository maintenanceRepo;
	
		
	@GetMapping(value="/deleteTicket")
	public String deleteTicket(@RequestParam int id) {
		
		ticketRepo.deleteById(id);
		return "redirect:/list";
	}
	
	@GetMapping(value="/deleteFlight")
	public String deleteFlight(@RequestParam int id) {
		
		flightRepo.deleteById(id);
		return "redirect:/list";
	}

	@GetMapping(value="/deleteCrew")
	public String deleteCrew(@RequestParam int id) {
		crewRepo.deleteById(id);
		return "redirect:/list";
	}
	
	@GetMapping(value="/deleteMaintenance")
	public String deleteMaintenance(@RequestParam int id) {
		maintenanceRepo.deleteById(id);
		return "redirect:/list";
	}

	
}
