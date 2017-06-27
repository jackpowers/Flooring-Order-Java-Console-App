/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dao.OrderDaoException;
import com.sg.flooring.dto.Order;
import java.util.List;

/**
 *
 * @author admin
 */
public interface FlooringService {

    public List<Order> getAllOrders(String date)throws OrderDaoException;

    
    public int createOrderNumber() throws OrderDaoException;

    public void addTaxRate(Order currentOrder);

    public void addCostPerSquareFoot(Order currentOrder);
    
    public void addLaborCostPerSquareFoot(Order currentOrder);

    public void addMaterialCost(Order currentOrder);

    

    public void addLaborCost(Order currentOrder);

    public void addTax(Order currentOrder);

    public void addTotal(Order currentOrder);

    public void putOrderInMemory(Order currentOrder) throws OrderDaoException;

    public Order getOrder(String date, int orderNumber) throws OrderDaoException;

    public void removeOrder(String date, int orderNumber) throws OrderDaoException;

    public void saveWork() throws OrderDaoException;

    
    
}
