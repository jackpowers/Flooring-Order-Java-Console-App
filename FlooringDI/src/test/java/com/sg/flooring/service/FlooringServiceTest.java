/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dto.Order;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author admin
 */
public class FlooringServiceTest {
   
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
     FlooringService service = ctx.getBean("serviceLayer", FlooringService.class);
     
     Order testOrder = new Order(1);

     
     
    
    public FlooringServiceTest() {
    }
    
    @Test
    public void testAddTaxRate() {
        BigDecimal taxRateTest = new BigDecimal("0.0625");
        testOrder.setState("OH");
        service.addTaxRate(testOrder);
        BigDecimal taxRate = testOrder.getTaxRate();
        assertEquals(taxRateTest, taxRate );
        
        
    }

    /**
     * Test of addCostPerSquareFoot method, of class FlooringService.
     */
    @Test
    public void testAddCostPerSquareFoot() {
        BigDecimal costPerSquareFootTest = new BigDecimal("2.25");
        testOrder.setProductType("Carpet");
        service.addCostPerSquareFoot(testOrder);
        BigDecimal costPerSquareFoot = testOrder.getCostPerSquareFoot();
        assertEquals(costPerSquareFootTest, costPerSquareFoot );
    }

    
    @Test
    public void testAddLaborCostPerSquareFoot() {
        BigDecimal laborCostPerSquareFootTest = new BigDecimal("2.10");
        testOrder.setProductType("Carpet");
        service.addLaborCostPerSquareFoot(testOrder);
        BigDecimal laborCostPerSquareFoot = testOrder.getLaborCostPerSquareFoot();
        assertEquals(laborCostPerSquareFootTest, laborCostPerSquareFoot );
    }

    /**
     * Test of addMaterialCost method, of class FlooringService.
     */
    @Test
    public void testAddMaterialCost() {
        BigDecimal materialCostTest = new BigDecimal("2205.00");
        testOrder.setProductType("Carpet");
        testOrder.setCostPerSquareFoot(new BigDecimal("2.25"));
        testOrder.setArea(new BigDecimal("980"));
        service.addMaterialCost(testOrder);
        BigDecimal materialCost = testOrder.getMaterialCost();
        assertEquals(materialCostTest, materialCost );
    }

    /**
     * Test of addLaborCost method, of class FlooringService.
     */
    @Test
    public void testAddLaborCost() {
         BigDecimal laborCostTest = new BigDecimal("2058.00");
        testOrder.setProductType("Carpet");
        testOrder.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        testOrder.setArea(new BigDecimal("980"));
        service.addLaborCost(testOrder);
        BigDecimal laborCost = testOrder.getLaborCost();
        assertEquals(laborCostTest, laborCost );
    }

    /**
     * Test of addTax method, of class FlooringService.
     */
    @Test
    public void testAddTax() {
        BigDecimal taxTest = new BigDecimal("266.44");
        testOrder.setLaborCost(new BigDecimal("2058.00"));
        testOrder.setMaterialCost(new BigDecimal("2205.00"));
        testOrder.setTaxRate(new BigDecimal("0.0625"));
        service.addTax(testOrder);
        BigDecimal tax = testOrder.getTax();
        assertEquals(taxTest , tax);
    }

    /**
     * Test of addTotal method, of class FlooringService.
     */
    @Test
    public void testAddTotal() {
                BigDecimal totalTest = new BigDecimal("4529.44");
                testOrder.setLaborCost(new BigDecimal("2058.00"));
                testOrder.setMaterialCost(new BigDecimal("2205.00")); 
                testOrder.setTax(new BigDecimal("266.44"));
                service.addTotal(testOrder);
                BigDecimal total = testOrder.getTotal();
                assertEquals(totalTest, total);
                

    }

    /**
     * Test of putOrderInMemory method, of class FlooringService.
     */
    
    
}
