package com.ankit.springtest.springtest.controller;


import com.ankit.springtest.springtest.entity.Ticket;
import com.ankit.springtest.springtest.service.TicketService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketDao;

//    @PostMapping("/bookTickets")
//    public String bookTicket(@RequestBody Ticket ticket){
//        ticketDao.book(ticket);
//        return "ticket booked ";
//    }

//    @GetMapping("/viewTickets")
//    public List<Ticket> findAll(){
//
//        return ticketDao.allBookings();
//    }

//    @PutMapping("/update")
//    public String updateTicket(@RequestBody Ticket ticket)
//    {
//        ticketDao.updateTicket(ticket);
//        return "Ticket Updated";
//    }

    @GetMapping("/viewTickets")
    public String ticketView(Model theModel){
        List<Ticket> list=ticketDao.allBookings();
        theModel.addAttribute("tickets", list);
        return "ticket/viewTicket";
    }

    @GetMapping("/bookTicket")
    public String bookTicket(Model model){
        model.addAttribute("ticket",new Ticket());
        return "ticket/bookTicket";
    }

    @PostMapping("/book")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket){
        System.out.println("called" +ticket.getAmount());
        ticketDao.book(ticket);
        return "redirect:/ticket/viewTickets";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("ticketid") int id, Model theModel)
    {
        Ticket ticket=ticketDao.findByID(id);
        theModel.addAttribute("ticket",ticket);
        return "ticket/bookTicket";

    }
    @PutMapping("/updateTicket")
    public String updateTicket(@RequestBody Ticket ticket)
    {
    ticketDao.updateTicket(ticket);
    return "ticket with ID "+ticket.getId()+" has been updated";
    }

    @GetMapping("/deleteTicket")
    public String deletedTicket(@RequestParam("ticketid") int id)
    {
    ticketDao.deleteTicker(id);
    return "redirect:/ticket/viewTickets";
    }
}
