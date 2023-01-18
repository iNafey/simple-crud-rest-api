package co2103.hw2.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import co2103.hw2.Hw2Application;
import co2103.hw2.model.*;
import co2103.hw2.repo.*;

@Controller
public class ListController {
	
	
	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private CrewRepository crewRepo;
	
	@Autowired
	private MaintenanceRepository maintenanceRepo;
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		
		
		model.addAttribute("ticketList", ticketRepo.findAll());
		
		model.addAttribute("flightList", flightRepo.findAll());
		
		model.addAttribute("crewList", crewRepo.findAll());
		
		model.addAttribute("maintenanceList", maintenanceRepo.findAll());
		
		
		return "list";
	}
	
}
