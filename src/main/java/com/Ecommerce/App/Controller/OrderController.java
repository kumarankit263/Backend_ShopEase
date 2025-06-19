package com.Ecommerce.App.Controller;


import com.Ecommerce.App.Model.Orders;
import com.Ecommerce.App.ModelDTO.OrdersDTO;
import com.Ecommerce.App.Service.OrdersService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ecom/orders")
public class OrderController {
    private final OrdersService ordersService;

    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/placed/{userId}")
    public ResponseEntity<?>addOrderToCart(@PathVariable Integer userId){
        OrdersDTO placeOrder=ordersService.placeOrder(userId);
        return ResponseEntity.ok(placeOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrdersDetails(@PathVariable("orderId") Integer orderId) {
        Orders order = ordersService.getOrdersDetails(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Orders>>viewAllOrderByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        List<Orders>orders=ordersService.viewAllOrderByDate(date);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
    @DeleteMapping("/users/{userId}/{orderId}")
    public ResponseEntity<String> deleteOrders(@PathVariable Integer userId, @PathVariable Integer orderId) {
        ordersService.deleteOrders(userId, orderId);
        return new ResponseEntity<>("Order successfully deleted.", HttpStatus.OK);
    }
}
