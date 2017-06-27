/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FlooringOrderDaoImpl implements FlooringOrderDao {

    public static final String DELIMITER = "::";

    private Map<String, Map<Integer, Order>> ordersMap = new HashMap<>();

//    private Map<Integer, Order> orders = new HashMap<>();
    public FlooringOrderDaoImpl() {

    }

    @Override
    public List<Order> getAllOrders(LocalDate date) throws FileNotFoundException, OrderDaoException {

        try {
            loadFile(date);
        } catch (FileNotFoundException ex) {
            throw new OrderDaoException("Could not load order data.");
        } catch (OrderDaoException ex) {
            throw new OrderDaoException("Could not load order data.");
        }
        Map<Integer, Order> orders = new HashMap<>();
        //turns date into string for ordersMap

        String dateString = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));

        // Gets orders in ordersmap for that date, 
        orders = ordersMap.get(dateString);

        return new ArrayList<Order>(orders.values());
    }

    private void loadFile(LocalDate date) throws FileNotFoundException, OrderDaoException {

        String fileName = createFileName(date);
        String dateString = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        Map<Integer, Order> orders = new HashMap<>();
        ordersMap.put(dateString, orders);

        Scanner scanner;

        if (ordersMap.containsKey(dateString)) {

            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));

            String currentLine;
            String[] currentTokens;
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));
                currentOrder.setCustomerName(currentTokens[1]);
                currentOrder.setState(currentTokens[2]);
                currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                currentOrder.setProductType(currentTokens[4]);
                currentOrder.setArea(new BigDecimal(currentTokens[5]));
                currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
                currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));

                currentOrder.setTax(new BigDecimal(currentTokens[10]));
                currentOrder.setTotal(new BigDecimal(currentTokens[11]));

                orders.put(currentOrder.getOrderNumber(), currentOrder);
            }

            ordersMap.put(dateString, orders);
            scanner.close();

        }

    }

    private String createFileName(LocalDate date) {
        String fileName = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return "orders_" + fileName + ".txt";
    }

    private void writeFile() throws OrderDaoException, IOException {

        PrintWriter out = null;
        Map<Integer, Order> orders = new HashMap<>();

        for (String key : ordersMap.keySet()) {

            orders = ordersMap.get(key);

            try {
                out = new PrintWriter("orders_" + key + ".txt");
            } catch (IOException e) {
                throw new OrderDaoException("Could not save order data.");
            }
            for (Order currentOrder : orders.values()) {
                out.println(currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getState() + DELIMITER
                        + currentOrder.getTaxRate() + DELIMITER
                        + currentOrder.getProductType() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getCostPerSquareFoot() + DELIMITER
                        + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCost() + DELIMITER
                        + currentOrder.getTax() + DELIMITER
                        + currentOrder.getTotal());
                out.flush();

            }

        }

        out.close();

    }

    @Override
    public void putOrderInMemory(Order currentOrder) throws OrderDaoException {

        try {
            Map<Integer, Order> orders = new HashMap<>();

            LocalDate date = LocalDate.now();

            String dateToday = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));

            if (!ordersMap.containsKey(dateToday)) {

                ordersMap.put(dateToday, orders);

            }

            orders = ordersMap.get(dateToday);

            orders.put(currentOrder.getOrderNumber(), currentOrder);

            writeFile();
        } catch (OrderDaoException ex) {
            throw new OrderDaoException("Could not save order data.");
        } catch (IOException ex) {
            throw new OrderDaoException("Could not save order data.");
        }

    }

    @Override
    public Order getOrder(LocalDate dateTime, int orderNumber) throws OrderDaoException {
        try {
            loadFile(dateTime);
        } catch (FileNotFoundException ex) {
            throw new OrderDaoException("Could not load order data.");
            // data persistence exception(message)
        } catch (OrderDaoException ex) {
            throw new OrderDaoException("Could not load order data.");
        }
        Map<Integer, Order> orders = new HashMap<>();

        String dateString = dateTime.format(DateTimeFormatter.ofPattern("MMddyyyy"));

        // Gets orders in ordersmap for that date, 
        orders = ordersMap.get(dateString);

        Order currentOrder = orders.get(orderNumber);

        return currentOrder;
    }

    @Override
    public void removeOrder(LocalDate dateTime, int orderNumber) throws OrderDaoException {
        try {
            loadFile(dateTime);
        } catch (FileNotFoundException ex) {
            throw new OrderDaoException("Could not remove order.");
            // data persistence exception(message)
        } catch (OrderDaoException ex) {
            throw new OrderDaoException("Could not remove order.");
        }
        Map<Integer, Order> orders = new HashMap<>();

        String dateString = dateTime.format(DateTimeFormatter.ofPattern("MMddyyyy"));

        // Gets orders in ordersmap for that date, 
        orders = ordersMap.get(dateString);

        orders.remove(orderNumber);
        saveWork();

    }

    @Override
    public void saveWork() throws OrderDaoException {
        try {
            writeFile();
        } catch (OrderDaoException ex) {
            throw new OrderDaoException("Could not save order.");
        } catch (IOException ex) {
            throw new OrderDaoException("Could not save order.");
        }
    }

    @Override
    public int createOrderNumber(LocalDate dateTime) throws OrderDaoException {

        try {

            int orderNumber;
            try {
                loadFile(dateTime);
            } catch (FileNotFoundException e) {
                orderNumber = 0;
                return orderNumber + 1;

            } catch (OrderDaoException e) {
                orderNumber = 0;
                return orderNumber + 1;

            }

            orderNumber = getAllOrders(dateTime).size();
            return orderNumber + 1;
        } catch (FileNotFoundException ex) {
            throw new OrderDaoException("Could not create order.");

        } catch (OrderDaoException ex) {
            throw new OrderDaoException("Could not create order.");

        }
    }

}
