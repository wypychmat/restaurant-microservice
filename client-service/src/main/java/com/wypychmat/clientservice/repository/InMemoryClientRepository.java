package com.wypychmat.clientservice.repository;

import com.wypychmat.clientservice.domain.Client;
import com.wypychmat.clientservice.domain.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryClientRepository implements ClientRepository {
    private final Map<Integer, Client> clients;

    public InMemoryClientRepository() {
        clients = new ConcurrentHashMap<>();
    }

    @Override
    public List<Client> getClients() {
        return new ArrayList<>(clients.values());
    }

    @Override
    public void save(Client client) {
        Integer id = clients.keySet().stream().max(Integer::compare).orElse(0);
        client.setId(++id);
        clients.put(id, client);
    }

    @Override
    public Optional<Client> getClientById(Integer id) {
        Client client = clients.get(id);
        if (client == null) {
            return Optional.empty();
        }
        return Optional.of(client);
    }

    @Override
    public Optional<Double> getClientDiscount(Integer id) {
        Client client = clients.get(id);
        if (client != null) {
            return Optional.of(client.getDiscount().getDiscount());
        }
        return Optional.empty();
    }
}
