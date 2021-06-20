package com.wypychmat.menu.domain;

import com.wypychmat.menu.controller.dto.DishDto;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {

     Optional<Dish> findItemById(int id);

     List<Dish> getAll();

     Integer addDish(DishDto dishDto);


}
