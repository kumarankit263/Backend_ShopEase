package com.Ecommerce.App.ServiceImpl;

import com.Ecommerce.App.Exception.ShippingException;
import com.Ecommerce.App.Model.Orders;
import com.Ecommerce.App.Model.Shipper;
import com.Ecommerce.App.Model.ShippingDetails;
import com.Ecommerce.App.ModelDTO.ShippingDTO;
import com.Ecommerce.App.Repository.OrderRepository;
import com.Ecommerce.App.Repository.ShipperRepository;
import com.Ecommerce.App.Repository.ShippingRepository;
import com.Ecommerce.App.Service.ShippingService;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements ShippingService {
    private final ShippingRepository shippingRepository;

    private final OrderRepository orderRepository;

    private final ShipperRepository shipperRepository;

    public ShippingServiceImpl(ShippingRepository shippingRepository, OrderRepository orderRepository, ShipperRepository shipperRepository) {
        this.shippingRepository = shippingRepository;
        this.orderRepository = orderRepository;
        this.shipperRepository = shipperRepository;
    }


    @Override
    public ShippingDetails setShippingDetails(Integer orderId, Integer shipperId, ShippingDetails shippingDetails) throws ShippingException {
        if (shippingDetails == null)
            throw new ShippingException("ShippingDetails cannot be null");

        Orders existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ShippingException("Order not found"));

        Shipper existingShipper = shipperRepository.findById(shipperId)
                .orElseThrow(() -> new ShippingException("Shipper not found"));

        // Save the ShippingDetails entity first
        shippingDetails.setOrders(existingOrder);
        shippingDetails.setShipper(existingShipper);
        shippingDetails = shippingRepository.save(shippingDetails);

        // Now update the Orders entity with the saved ShippingDetails
        existingOrder.setShippingDetails(shippingDetails);
        orderRepository.save(existingOrder);

        return shippingDetails;
    }

    @Override
    public ShippingDetails updateShippingAddress(Integer shippingId, ShippingDTO shippingDTO) throws ShippingException {
        ShippingDetails existing = shippingRepository.findById(shippingId)
                .orElseThrow(() -> new ShippingException("Shipping detail not found"));

        existing.setState(shippingDTO.getState());
        existing.setAddress(shippingDTO.getAddress());
        existing.setCity(shippingDTO.getCity());
        existing.setPostalCode(shippingDTO.getPostalCode());
        return shippingRepository.save(existing);
    }

    @Override
    public void deleteShippingDetails(Integer shippingId) throws ShippingException {
        ShippingDetails existing = shippingRepository.findById(shippingId)
                .orElseThrow(() -> new ShippingException("Shipping detail not found"));

        shippingRepository.delete(existing);
    }
}
