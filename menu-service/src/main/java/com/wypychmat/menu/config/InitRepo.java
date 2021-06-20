package com.wypychmat.menu.config;

import com.wypychmat.menu.controller.dto.DishDto;
import com.wypychmat.menu.domain.MenuRepository;
import org.springframework.stereotype.Component;

@Component
public class InitRepo {

    public InitRepo(MenuRepository menuRepository) {
        menuRepository.addDish(new DishDto("Pizza", 10d));
        menuRepository.addDish(new DishDto("Burger", 5d));
        menuRepository.addDish(new DishDto("HotDog", 2.5));
    }
}
