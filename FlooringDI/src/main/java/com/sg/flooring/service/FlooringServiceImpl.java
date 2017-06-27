/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dao.FlooringOrderDao;
import com.sg.flooring.dao.FlooringProductDao;
import com.sg.flooring.dao.FlooringStateDao;
import com.sg.flooring.dao.OrderDaoException;
import com.sg.flooring.dto.Order;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FlooringServiceImpl implements FlooringService {

    FlooringOrderDao orderDao;
    FlooringProductDao productDao;
    FlooringStateDao stateDao;

    public FlooringServiceImpl(FlooringOrderDao orderDao, FlooringProductDao productDao, FlooringStateDao stateDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.stateDao = stateDao;

    }

    @Override
    public List<Order> getAllOrders(String date) throws OrderDaoException {

        try {
            LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            return orderDao.getAllOrders(dateTime);
        } catch (FileNotFoundException ex) {
            throw new OrderDaoException("Could not load order.");
        } catch (OrderDaoException ex) {
            throw new OrderDaoException("Could not load order.");
        }
    }

    @Override
    public int createOrderNumber() throws OrderDaoException {
        LocalDate dateTime = LocalDate.now();
        int orderNumber = orderDao.createOrderNumber(dateTime);
        return orderNumber;
    }

    @Override
    public void addTaxRate(Order currentOrder) {
        String state = currentOrder.getState();
        currentOrder.setTaxRate(stateDao.getTaxRate(state));
    }

    @Override
    public void addCostPerSquareFoot(Order currentOrder) {
        String productType = currentOrder.getProductType();
        currentOrder.setCostPerSquareFoot(productDao.getCostPerSquareFoot(productType));

    }

    @Override
    public void addLaborCostPerSquareFoot(Order currentOrder) {
        String productType = currentOrder.getProductType();
        currentOrder.setLaborCostPerSquareFoot(productDao.getLaborCostPerSquareFoot(productType));

    }

    @Override
    public void addMaterialCost(Order currentOrder) {
        BigDecimal costPerSquareFoot = currentOrder.getCostPerSquareFoot();
        BigDecimal area = currentOrder.getArea();

        BigDecimal materialCost = area.multiply(costPerSquareFoot).setScale(2, RoundingMode.HALF_UP);

        currentOrder.setMaterialCost(materialCost);

    }

    @Override
    public void addLaborCost(Order currentOrder) {

        BigDecimal laborCostPerSquareFoot = currentOrder.getLaborCostPerSquareFoot();
        BigDecimal area = currentOrder.getArea();

        BigDecimal laborCost = area.multiply(laborCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
        currentOrder.setLaborCost(laborCost);
    }

    @Override
    public void addTax(Order currentOrder) {
        BigDecimal laborCost = currentOrder.getLaborCost();
        BigDecimal materialCost = currentOrder.getMaterialCost();

        BigDecimal cost = laborCost.add(materialCost).setScale(2, RoundingMode.HALF_UP);
        BigDecimal taxRate = currentOrder.getTaxRate();
        BigDecimal tax = cost.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        currentOrder.setTax(tax);

    }

    @Override
    public void addTotal(Order currentOrder) {
        BigDecimal laborCost = currentOrder.getLaborCost();
        BigDecimal materialCost = currentOrder.getMaterialCost();

        BigDecimal cost = laborCost.add(materialCost).setScale(2, RoundingMode.HALF_UP);
        BigDecimal tax = currentOrder.getTax();
        BigDecimal total = cost.add(tax).setScale(2, RoundingMode.HALF_UP);
        currentOrder.setTotal(total);

    }

    @Override
    public void putOrderInMemory(Order currentOrder) throws OrderDaoException {
        orderDao.putOrderInMemory(currentOrder);
    }

    @Override
    public Order getOrder(String date, int orderNumber) throws OrderDaoException {

        LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        return orderDao.getOrder(dateTime, orderNumber);
    }

    @Override
    public void removeOrder(String date, int orderNumber) throws OrderDaoException {
        LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        orderDao.removeOrder(dateTime, orderNumber);
    }

    @Override
    public void saveWork() throws OrderDaoException {
        orderDao.saveWork();
    }
}
