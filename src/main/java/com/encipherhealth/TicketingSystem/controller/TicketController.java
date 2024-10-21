package com.encipherhealth.TicketingSystem.controller;

import com.encipherhealth.TicketingSystem.model.Ticket;
import com.encipherhealth.TicketingSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService service;


    // To create a New Ticket

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket addTicket(@RequestBody Ticket ticket){
        return service.addTicket(ticket);
    }

    // To get All Tickets

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> getAllTickets(){
        return service.findAllTickets();
    }

    // To get a Specific Ticket

    @GetMapping("/{ticketId}")
    public Ticket getTicket( @PathVariable String ticketId){
        return service.findById(ticketId);
    }

    // To get all Tickets by UserId

    @GetMapping("/user/{userId}")
    public List<Ticket> getTicketByUserId( @PathVariable String userId){
        return service.findByUserId(userId);
    }

    // To get all Tickets by approvalLevel

    @GetMapping("/approvalLevel/{approvalLevel}")
    public List<Ticket> getTicketBApprovalLevel( @PathVariable int approvalLevel){
        return service.findByApprovalLevel(approvalLevel);
    }

    // To update a Ticket

    @PutMapping
    public Ticket updateTicket(@RequestBody Ticket ticket){
        return service.updateTicket(ticket);
    }

    //  To delete a Ticket

    @DeleteMapping("/{ticketId}")
    public String deleteTicket(@PathVariable String ticketId){
        return service.deleteTicket(ticketId);
    }

    // To approve a ticket

    @PutMapping("/{ticketId}/approve")
    public Object approveTicket(@PathVariable String ticketId, @RequestParam String approveId){
        return service.approveTicket(ticketId,approveId);
    }

    // To reject a ticket

    @PutMapping("/{ticketId}/reject")
    public Object rejectTicket(@PathVariable String ticketId,@RequestParam String approveId){
        return service.rejectTicket(ticketId,approveId);
    }
}
