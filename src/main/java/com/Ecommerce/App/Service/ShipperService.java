package com.Ecommerce.App.Service;

import com.Ecommerce.App.Exception.ShipperException;
import com.Ecommerce.App.Model.Shipper;

import java.util.List;

public interface ShipperService {

    public void deleteShipperById(Integer id) throws ShipperException;

    public Shipper saveShipper(Shipper shipper) throws ShipperException;

    public Shipper getShipperById(Integer id) throws ShipperException;

    public List<Shipper> getAllShippers() throws ShipperException;
}
