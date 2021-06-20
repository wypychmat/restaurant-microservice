package com.wypychmat.clientservice.service;

import com.wypychmat.clientservice.domain.Client;
import com.wypychmat.clientservice.domain.ClientRepository;
import com.wypychmat.clientservice.domain.Discount;
import org.springframework.stereotype.Service;

@Service
public class InitUser {
    private final ClientRepository clientRepository;

    public InitUser(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        createInitUsers();
    }


    private void createInitUsers() {
        clientRepository.save(new Client("A", Discount.noDiscount,0));
        clientRepository.save(new Client("B", Discount.noDiscount,0));
        clientRepository.save(new Client("C", Discount.noDiscount,0));
    }
}
