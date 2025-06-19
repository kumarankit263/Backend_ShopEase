package com.Ecommerce.App.Controller;

import com.Ecommerce.App.Model.ShippingDetails;
import com.Ecommerce.App.ModelDTO.ShippingDTO;
import com.Ecommerce.App.Service.ShippingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecom/order-shipping")
public class ShippingController {
    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/{orderId}/{shipperId}")
    public ResponseEntity<ShippingDetails>setShippingDetails(@PathVariable Integer orderId, @PathVariable Integer shipperId, @Valid @RequestBody ShippingDetails shippingDetails){
        ShippingDetails savedShippingDetails=shippingService.setShippingDetails(orderId,shipperId,shippingDetails);
        return new ResponseEntity<>(savedShippingDetails, HttpStatus.CREATED);
    }

    @DeleteMapping("/{shippingId}")
    public ResponseEntity<Void> deleteShippingDetails(@PathVariable Integer shippingId) {
        shippingService.deleteShippingDetails(shippingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{shippingId}")
    public ResponseEntity<ShippingDetails> updateShippingAddress(@PathVariable Integer shippingId,
                                                                 @Valid @RequestBody ShippingDTO shippingDTO) {
        ShippingDetails updatedShippingDetails = shippingService.updateShippingAddress(shippingId, shippingDTO);
        return new ResponseEntity<>(updatedShippingDetails, HttpStatus.OK);
    }
}
