package com.Ecommerce.App.Service;

import com.Ecommerce.App.Exception.OrdersException;
import com.Ecommerce.App.Model.Orders;
import com.Ecommerce.App.ModelDTO.OrdersDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrdersService {
    public OrdersDTO placeOrder(Integer userid) throws OrdersException;

    public Orders getOrdersDetails(Integer orderId) throws OrdersException;

    public List<Orders> viewAllOrderByDate(Date date) throws OrdersException;

    public void deleteOrders(Integer userId, Integer orderId) throws OrdersException;
}
