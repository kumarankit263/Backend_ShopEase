package com.Ecommerce.App.Controller;


import com.Ecommerce.App.Model.Shipper;
import com.Ecommerce.App.Service.ShipperService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecom/order-shippers")
public class ShipperController {

    private final ShipperService shipperService;

    public ShipperController(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipper>getShipperById(@PathVariable Integer id){
        Shipper shipper=shipperService.getShipperById(id);
        return ResponseEntity.ok(shipper);
    }
    @GetMapping
    public ResponseEntity<List<Shipper>> getAllShippers() {
        List<Shipper> shippers = shipperService.getAllShippers();
        return ResponseEntity.ok(shippers);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipperById(@PathVariable Integer id) {
        shipperService.deleteShipperById(id);
        return ResponseEntity.ok("Shipper with ID " + id + " successfully deleted.");
    }

    @PostMapping("/add")
    public ResponseEntity<Shipper> saveShipper(@Valid @RequestBody Shipper shipper) {
        Shipper savedShipper = shipperService.saveShipper(shipper);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShipper);
    }
}
