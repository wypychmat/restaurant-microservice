package com.wypychmat.menu.repository;


import com.wypychmat.menu.controller.dto.DishDto;
import com.wypychmat.menu.domain.MenuRepository;
import com.wypychmat.menu.domain.Dish;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryMenuRepository implements MenuRepository {
    private final Map<Integer, Dish> menu;

    public InMemoryMenuRepository() {
        menu = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Dish> findItemById(int id) {
        Dish dish = menu.get(id);
        if (dish != null) {
            return Optional.of(dish);
        }
        return Optional.empty();
    }

    @Override
    public List<Dish> getAll() {
        return new ArrayList<>(menu.values());
    }

    @Override
    public Integer addDish(DishDto dishDto) {
        Integer id = menu.keySet().stream()
                .max(Integer::compare)
                .orElse(0);
        Dish dish = new Dish(++id, dishDto.getName(), dishDto.getPrice());
        menu.put(id, dish);
        return id;
    }
}
