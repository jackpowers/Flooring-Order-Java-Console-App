/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.ui;

import com.sg.flooring.dto.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author admin
 */
public class FlooringView {

    private UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public int getMenuSelection() {
        io.print(" ");
        io.print("Welcome to Flooring Ordering System");
        io.print("1. Display Orders");
        io.print("2. Create an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 0, 7);
    }

    

    public void displayOrders(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print("Order Number: " + Integer.toString(currentOrder.getOrderNumber()));
            io.print("Customer Name: " + currentOrder.getCustomerName());
            io.print("State: " + currentOrder.getState());
            io.print("Tax Rate: " + currentOrder.getTaxRate().toString());
            io.print("Product Type: " + currentOrder.getProductType());
            io.print("Area: " + currentOrder.getArea());
            io.print("Cost Per Square Foot: " + currentOrder.getCostPerSquareFoot());
            io.print("Labor Per Square Foot: " + currentOrder.getLaborCostPerSquareFoot());
            io.print("Material Cost: " + currentOrder.getMaterialCost());
            io.print("Labor Cost: " + currentOrder.getLaborCost());
            io.print("Tax: " + currentOrder.getTax());
            io.print("Total: " + currentOrder.getTotal());
            io.print(" ");

        }
    }

    public String getDate() {
        return io.readString("Please enter a date in MM/dd/yyyy format.");
    }

    public void displayAddOrderBanner() {
        io.print("--Add Order--");
    }

    public Order addOrder(int orderNumber) {
        String customerName = io.readString("Please Enter Client Name");
        String state = io.readString("Please Enter State Abbreviation."
        );
        String productType = io.readString("Please Enter Product Type");
        BigDecimal area = io.readBigDecimal("Please Enter Area in Square Feet");
        Order currentOrder = new Order(orderNumber);
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setArea(area);
        currentOrder.setProductType(productType);
        return currentOrder;

    }

    public void displayAddOrderSuccessBanner() {
        io.print("--New Order Added--");
    }

    public void exitMessage() {
        io.print("Thanks, Bye!");
    }

    public void displayEditOrderBanner() {
        io.print("--Order Edit--");
    }

    public int getOrderNumber() {
        return io.readInt("Please Enter Order Number");
    }

    public Order getEditedOrder(Order editOrder) {
        String customerName = io.readString("Please enter Customer Name("
                + editOrder.getCustomerName() + ")");
        if (customerName.length() > 0) {
            editOrder.setCustomerName(customerName);
        }
        String state = io.readString("Please Enter State Abbreviation ("
                + editOrder.getState() + ")");
        if (state.length() > 0) {
            editOrder.setState(state);
        }
        String area = (io.readString("Please Enter Area("
                + editOrder.getArea() + ")"));
        if (area.toString().length() > 0) {
            editOrder.setArea(new BigDecimal(area));
        }
        return editOrder;
    }

    public void displayEditSuccessBanner() {
        io.print("--Order Edited--");
    }

    public void displayRemoveOrderBanner() {
        io.print("--Remove Order--");

    }

    public void displayRemoveOrderSuccessBanner() {
        io.print("--Order Removed--");
    }

    public void displayUnknownCommand() {
        io.print("Unknown Command.");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
