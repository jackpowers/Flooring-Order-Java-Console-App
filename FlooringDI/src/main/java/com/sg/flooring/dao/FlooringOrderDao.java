/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author admin
 */
public interface FlooringOrderDao {

    public List<Order> getAllOrders(LocalDate dateTime)throws FileNotFoundException, OrderDaoException;
    
    public int createOrderNumber(LocalDate dateTime) throws OrderDaoException;

    

    public void putOrderInMemory(Order currentOrder)throws OrderDaoException;

    public Order getOrder(LocalDate dateTime, int orderNumber) throws OrderDaoException;

    public void removeOrder(LocalDate dateTime, int orderNumber)throws OrderDaoException;

    public void saveWork()throws OrderDaoException;
    
}
