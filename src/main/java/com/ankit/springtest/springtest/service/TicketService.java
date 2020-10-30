package com.ankit.springtest.springtest.service;


import com.ankit.springtest.springtest.dao.TicketDao;
import com.ankit.springtest.springtest.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketDao dao;

    public void book(Ticket ticket){

        dao.save(ticket);
    }

    public List<Ticket> allBookings(){

        return dao.findAll();
    }
    public void updateTicket(@RequestBody Ticket ticket){
        Optional<Ticket> optional= dao.findById(ticket.getId());
        Ticket temp=null;
        if(optional.isPresent()) {
            temp=optional.get();
            temp.setAmount(ticket.getAmount());
            temp.setCategory(ticket.getCategory());
            dao.save(temp);
        }
        else {
            throw new RuntimeException("Did not find employee id - " +ticket.getId());
        }
    }

    public Ticket findByID(int id){
        Optional<Ticket> optional = dao.findById(id) ;
        Ticket temp=null;
        System.out.println(optional.isPresent());
        if(optional.isPresent()){
            temp=optional.get();

        }
        else {
            throw new RuntimeException("Did not find employee id - " +id);
        }
        return temp;
    }
}
