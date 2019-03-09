package com.github.nicolasperuch.api;

import com.github.nicolasperuch.api.dto.PizzaDto;
import com.github.nicolasperuch.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Requests a pizza")
    @PostMapping("/pizza")
    public ResponseEntity<?> createPizzaOrder(@RequestBody PizzaDto pizzaDto){
        return orderService.requestOrder(pizzaDto);
    }
}
