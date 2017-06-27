/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.controller;

import com.sg.flooring.dao.DaoException;
import com.sg.flooring.dao.FlooringOrderDao;
import com.sg.flooring.dao.FlooringProductDao;
import com.sg.flooring.dao.FlooringStateDao;
import com.sg.flooring.dao.OrderDaoException;
import com.sg.flooring.dto.Order;
import com.sg.flooring.service.FlooringService;
import com.sg.flooring.ui.FlooringView;
import com.sg.flooring.ui.UserIO;
import com.sg.flooring.ui.UserIOImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FlooringController {

    FlooringView view;

    private FlooringOrderDao orderDao;

    private FlooringProductDao productDao;

    private FlooringStateDao stateDao;

    private FlooringService service;

    Order currentOrder;

    public FlooringController(FlooringService service, FlooringView view) {
        this.service = service;
        this.view = view;
    }
    private UserIO io = new UserIOImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:

                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveWork();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage("Could not execute command.");
        }
    }

    private int getMenuSelection() {
        return view.getMenuSelection();
    }

    private void displayOrders() {
        String date = view.getDate();
        try {
            List<Order> orderList = service.getAllOrders(date);
            view.displayOrders(orderList);
        } catch (Exception e) {
            view.displayErrorMessage("Could not display order.");
        }
    }

    private void addOrder() {
        try {
            view.displayAddOrderBanner();
            int orderNumber = service.createOrderNumber();
            currentOrder = view.addOrder(orderNumber);
            addTaxRate(currentOrder);
            addCostPerSquareFoot(currentOrder);
            addLaborCostPerSquareFoot(currentOrder);
            addMaterialCost(currentOrder);
            addLaborCost(currentOrder);
            addTax(currentOrder);
            addTotal(currentOrder);
            putOrderInMemory(currentOrder);

            view.displayAddOrderSuccessBanner();
        } catch (OrderDaoException ex) {
            view.displayErrorMessage("Could not add order.");
        }

    }

    private void editOrder() {
        try {
            view.displayEditOrderBanner();
            String date = view.getDate();
            int orderNumber = view.getOrderNumber();
            Order currentOrder = service.getOrder(date, orderNumber);
            view.getEditedOrder(currentOrder);
            addTaxRate(currentOrder);
            addCostPerSquareFoot(currentOrder);
            addLaborCostPerSquareFoot(currentOrder);
            addMaterialCost(currentOrder);
            addLaborCost(currentOrder);
            addTax(currentOrder);
            addTotal(currentOrder);
            putOrderInMemory(currentOrder);
            view.displayEditSuccessBanner();
        } catch (OrderDaoException ex) {
            view.displayErrorMessage("Could not edit order.");
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommand();
    }

    private void removeOrder() {
        try {
            view.displayRemoveOrderBanner();
            String date = view.getDate();
            int orderNumber = view.getOrderNumber();
            service.removeOrder(date, orderNumber);
            view.displayRemoveOrderSuccessBanner();
        } catch (OrderDaoException ex) {
            view.displayErrorMessage("Could not remove order.");
        }
    }

    private void saveWork() {
        try {
            service.saveWork();
        } catch (OrderDaoException ex) {
            view.displayErrorMessage("Could not save order.");
        }
    }

    private void exitMessage() {
        view.exitMessage();
    }

    private void addTaxRate(Order currentOrder) {
        service.addTaxRate(currentOrder);
    }

    private void addCostPerSquareFoot(Order currentOrder) {
        service.addCostPerSquareFoot(currentOrder);
    }

    private void addLaborCostPerSquareFoot(Order currentOrder) {
        service.addLaborCostPerSquareFoot(currentOrder);
    }

    private void addMaterialCost(Order currentOrder) {
        service.addMaterialCost(currentOrder);
    }

    private void addLaborCost(Order currentOrder) {
        service.addLaborCost(currentOrder);
    }

    private void addTax(Order currentOrder) {
        service.addTax(currentOrder);
    }

    private void addTotal(Order currentOrder) {
        service.addTotal(currentOrder);
    }

    private void putOrderInMemory(Order currentOrder) {
        try {
            service.putOrderInMemory(currentOrder);
        } catch (OrderDaoException ex) {
            view.displayErrorMessage("Could not put order in memory");
        }
    }

}
