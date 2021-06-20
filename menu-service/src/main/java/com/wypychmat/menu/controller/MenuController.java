package com.wypychmat.menu.controller;

import com.wypychmat.menu.controller.dto.DishDto;
import com.wypychmat.menu.domain.Dish;
import com.wypychmat.menu.domain.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping
    public List<Dish> getDishes() {
        log.info("get Dishes");
        return menuRepository.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDish(@PathVariable int id) {
        Optional<Dish> dish = menuRepository.findItemById(id);
        return dish
                .map(body -> {
                    log.info("Found dish by id: {}", id);
                    return ResponseEntity.ok(body);
                })
                .orElseGet(() -> {
                    log.info("Not found dish");
                  return ResponseEntity.ok().build();
                });
    }


    @PostMapping
    public Integer addDish(@RequestBody DishDto dish) {
        log.info("Adding dish");
        return menuRepository.addDish(dish);
    }
}
