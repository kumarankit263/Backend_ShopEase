package com.Ecommerce.App.Service;

import com.Ecommerce.App.Exception.ShippingException;
import com.Ecommerce.App.Model.ShippingDetails;
import com.Ecommerce.App.ModelDTO.ShippingDTO;

public interface ShippingService {

    public ShippingDetails setShippingDetails(Integer orderId, Integer shipperId, ShippingDetails shippingDetails) throws ShippingException;

    public ShippingDetails updateShippingAddress(Integer shippingId, ShippingDTO shippingDTO)throws ShippingException;

    public void deleteShippingDetails(Integer shippingId)throws ShippingException;
}
