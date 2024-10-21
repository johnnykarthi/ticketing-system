package com.encipherhealth.TicketingSystem.service;

import com.encipherhealth.TicketingSystem.model.Ticket;
import com.encipherhealth.TicketingSystem.model.User;
import com.encipherhealth.TicketingSystem.repository.TicketRepository;
import com.encipherhealth.TicketingSystem.repository.UserRepository;
import com.encipherhealth.TicketingSystem.util.TicketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    private TicketRepository repository;

    @Autowired
    private UserRepository userRepo;

    // CRUD  Create, Read, Update, Delete

    public Ticket addTicket(Ticket ticketDemo) {
        ticketDemo.setTicketId(UUID.randomUUID().toString());
        return repository.save(ticketDemo);
    }

    public List<Ticket> findAllTickets() {
        return repository.findAll();
    }

    public Ticket findById(String ticketId) {
        return repository.findById(ticketId).get();
    }

    public List<Ticket> findByApprovalLevel(int approvalLevel) {
        return repository.findByApprovalLevel(approvalLevel);
    }

    public List<Ticket> findByUserId(String userId) {
        return repository.getTicketByUserId(userId);
    }

    public Ticket updateTicket(Ticket ticket) {
        Ticket existingTicket = repository.findById(ticket.getTicketId()).get();
        existingTicket.setTitle(ticket.getTitle());
        existingTicket.setDescription(ticket.getDescription());
        existingTicket.setUrgency(ticket.getUrgency());
        return repository.save(existingTicket);
    }


    public String deleteTicket(String ticketId) {
        repository.deleteById(ticketId);
        return ticketId + " has been Deleted successfully";
    }


    public Object approveTicket(String ticketId, String approveId) {
        Optional<Ticket> ticketDetails = repository.findById(ticketId);
        Optional<User> userDetails = userRepo.findById(approveId);

        if (ticketDetails.isPresent() && userDetails.isPresent()) {
            if (userDetails.get().getLevel() == 0) {
                return "Sorry, You don't have access to approve the ticket";
            }

            if (Objects.equals(ticketDetails.get().getStatus(), "Rejected")) {
                return "Ticket has been already rejected by " + TicketUtil.roles[ticketDetails.get().getApprovalLevel() - 1];
            }

            if (Objects.equals(ticketDetails.get().getStatus(), "Approved")) {
                return "Ticket has been already Approved by " + TicketUtil.roles[ticketDetails.get().getApprovalLevel() - 1];
            }

            if (ticketDetails.get().getApprovalLevel() == userDetails.get().getLevel()) {
                if (ticketDetails.get().getApprovalLevel() == 3) {
                    ticketDetails.get().setStatus("Approved");
                    ticketDetails.get().setMessage("Approved by " + TicketUtil.roles[userDetails.get().getLevel() - 1]);
                } else {
                    ticketDetails.get().setApprovalLevel(userDetails.get().getLevel() + 1);
                    ticketDetails.get().setMessage("Awaiting for " + TicketUtil.roles[userDetails.get().getLevel()]);
                }
                repository.save(ticketDetails.get());
                return ticketDetails.get();
            }

            if (ticketDetails.get().getApprovalLevel() < userDetails.get().getLevel()) {
                return "Please wait for " + TicketUtil.roles[ticketDetails.get().getApprovalLevel() - 1] + " Approval";
            }

        }
        return "Something went wrong";
    }

    public Object rejectTicket(String ticketId, String approveId) {

        Optional<Ticket> ticketDetails = repository.findById(ticketId);
        Optional<User> userDetails = userRepo.findById(approveId);

        if (ticketDetails.isPresent() && userDetails.isPresent()) {

            if (userDetails.get().getLevel() == 0) {
                return "Sorry, You don't have access to reject the ticket";
            }

            if (Objects.equals(ticketDetails.get().getStatus(), "Approved")) {
                return "Ticket has been already Approved by " + TicketUtil.roles[ticketDetails.get().getApprovalLevel() - 1];
            }

            if (Objects.equals(ticketDetails.get().getStatus(), "Rejected")) {
                return "Ticket has been already rejected by " + TicketUtil.roles[ticketDetails.get().getApprovalLevel() - 1];
            }

            if (ticketDetails.get().getApprovalLevel() <= userDetails.get().getLevel()) {
                ticketDetails.get().setApprovalLevel(userDetails.get().getLevel());
                ticketDetails.get().setStatus("Rejected");
                ticketDetails.get().setMessage("Rejected by " + TicketUtil.roles[userDetails.get().getLevel() - 1]);
                repository.save(ticketDetails.get());
                return ticketDetails.get();
            }
        }
        return "Something went wrong";
    }

}
