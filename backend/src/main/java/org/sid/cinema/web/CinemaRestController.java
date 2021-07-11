package org.sid.cinema.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

@CrossOrigin("*")
@org.springframework.web.bind.annotation.RestController
public class CinemaRestController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@PostMapping("/payerTicket")
	@Transactional
	public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm) {
		List<Ticket> listTickets=new ArrayList<>();
		ticketForm.getTickets().forEach(id->{
			Ticket ticket =ticketRepository.findById(id).get();
			ticket.setNomClient(ticketForm.getNomClient());
			ticket.setReserve(true);
			ticket.setCodePayement(ticketForm.getCodepayement());
			ticketRepository.save(ticket);
			listTickets.add(ticket);
		}
		);
		return listTickets;
	}
	@Data
	class TicketForm{
		private Integer codepayement;
		private String nomClient;
		private List<Long> tickets=new ArrayList<Long>();
	}
}
