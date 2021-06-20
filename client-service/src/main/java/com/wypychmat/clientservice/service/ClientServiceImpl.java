package com.wypychmat.clientservice.service;

import com.wypychmat.clientservice.client.OrderClient;
import com.wypychmat.clientservice.controller.dto.ClientDto;
import com.wypychmat.clientservice.domain.Client;
import com.wypychmat.clientservice.domain.ClientRepository;
import com.wypychmat.clientservice.domain.Discount;
import com.wypychmat.clientservice.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final OrderClient orderClient;

    public ClientServiceImpl(ClientRepository clientRepository, OrderClient orderClient) {
        this.clientRepository = clientRepository;
        this.orderClient = orderClient;
    }

    @Override
    public void createClient(ClientDto clientDto) {
        Client client = new Client(clientDto.getName(), Discount.noDiscount, 0);
        clientRepository.save(client);
    }

    @Override
    public Optional<Client> getClient(Integer id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public Optional<Double> getClientDiscount(Integer id) {
        return clientRepository.getClientDiscount(id);
    }

    @Override
    public void increaseDiscount(Integer id) {
        Optional<Client> clientById = clientRepository.getClientById(id);
        if (clientById.isPresent()) {
            Client client = clientById.get();
            client.setActualLevelPointCounter(client.getActualLevelPointCounter() + 1);
            checkIncreaseDiscount(client);
        }
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.getClients();
    }

    @Override
    public List<Order> getOrders(Integer id) {
        return orderClient.getOrdersByClient(id);
    }

    private void checkIncreaseDiscount(Client client) {
        int actualLevelPointCounter = client.getActualLevelPointCounter();
        Discount clientDiscount = client.getDiscount();
        boolean reachedLevel = actualLevelPointCounter >= clientDiscount.getPointsToReach();
        boolean isNotMaxValue = clientDiscount.ordinal() < Discount.values().length - 1;
        if (reachedLevel && isNotMaxValue) {
            applyNewDiscount(client, clientDiscount.ordinal());
        }
    }

    private void applyNewDiscount(Client client, int actualDiscountOrdinal) {
        Discount newDiscount = Discount.values()[actualDiscountOrdinal + 1];
        client.setDiscount(newDiscount);
        client.setActualLevelPointCounter(0);
    }

}
