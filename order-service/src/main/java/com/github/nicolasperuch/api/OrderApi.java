package com.github.nicolasperuch.api;

import com.github.nicolasperuch.api.dto.PizzaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class OrderApi {

    @PostMapping("/pizza")
    public ResponseEntity<?> createPizzaOrder(@RequestBody PizzaDto pizzaDto){
        return ok(pizzaDto);
    }
}
