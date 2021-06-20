package com.wypychmat.clientservice.controller;

import com.wypychmat.clientservice.controller.dto.ClientDto;
import com.wypychmat.clientservice.domain.Client;
import com.wypychmat.clientservice.domain.Order;
import com.wypychmat.clientservice.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }


    @PostMapping
    public void createClient(@RequestBody ClientDto clientDto) {

        clientService.createClient(clientDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {

        return clientService
                .getClient(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok().build());
    }

    @PutMapping("/discount/{id}")
    public void increaseDiscountCounter(@PathVariable Integer id) {

        clientService.increaseDiscount(id);
    }

    @GetMapping("/discount/{id}")
    public ResponseEntity<Double> getClientDiscount(@PathVariable Integer id) {

        return clientService
                .getClientDiscount(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok().build());
    }


    @GetMapping("/orders/{id}")
    public List<Order> getClientOrders(@PathVariable Integer id) {
        return clientService.getOrders(id);
    }
}
