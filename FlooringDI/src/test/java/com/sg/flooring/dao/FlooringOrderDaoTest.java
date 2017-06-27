/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class FlooringOrderDaoTest {
     private FlooringOrderDao orderDao = new FlooringOrderDaoImpl();

    
    public FlooringOrderDaoTest() {
    }
    
    
    @Test
    public void testGetAllOrders() throws FileNotFoundException, OrderDaoException {
        LocalDate dateTime = LocalDate.parse("01/01/1980", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        int count = orderDao.getAllOrders(dateTime).size();
        
        assertEquals(4, count);
        assertFalse(count == 3);
        assertTrue(count == 4);
        assertFalse(count == 5);
        
           }

    /**
     * Test of createOrderNumber method, of class FlooringOrderDao.
     */
    @Test
    public void testCreateOrderNumber() throws OrderDaoException {
        LocalDate dateTime = LocalDate.parse("01/01/1980", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        int orderNumber = orderDao.createOrderNumber(dateTime);
        
        assertEquals(5, orderNumber);
        assertFalse(orderNumber == 3);
        assertTrue(orderNumber == 5);
        assertFalse(orderNumber == 6);
    }

    
}
