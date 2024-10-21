package com.encipherhealth.TicketingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    private String ticketId;
    private String title;
    private String description;
    private String urgency;
    private int approvalLevel = 1;
    private String status = "Pending";
    private String message = "Awaiting for Designated Approval";
    private String userId;
}
