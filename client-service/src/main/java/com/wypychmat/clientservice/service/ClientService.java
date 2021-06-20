package com.wypychmat.clientservice.service;

import com.wypychmat.clientservice.controller.dto.ClientDto;
import com.wypychmat.clientservice.domain.Client;
import com.wypychmat.clientservice.domain.Order;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    void createClient(ClientDto clientDto);

    Optional<Client> getClient(Integer id);

    Optional<Double> getClientDiscount(Integer id);

    void increaseDiscount(Integer id);

    List<Client> getClients();

    List<Order> getOrders(Integer id);
}
