package com.encipherhealth.TicketingSystem.repository;

import com.encipherhealth.TicketingSystem.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketRepository extends MongoRepository<Ticket,String> {

    List<Ticket> findByApprovalLevel(int approvalLevel);

    @Query("{ userId: ?0 }")
    List<Ticket> getTicketByUserId(String userId);
}
