package com.wypychmat.clientservice.domain;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> getClients();

    void save(Client client);

    Optional<Client> getClientById(Integer id);

    Optional<Double> getClientDiscount(Integer id);
}
