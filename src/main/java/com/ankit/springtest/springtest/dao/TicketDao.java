package com.ankit.springtest.springtest.dao;

import com.ankit.springtest.springtest.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDao extends JpaRepository<Ticket,Integer> {



}
