/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FlooringProductDaoImpl implements FlooringProductDao {

    public static final String PRODUCT_FILE = "product.txt";
    public static final String DELIMITER = "::";

    private Map<String, Product> products = new HashMap<>();

    @Override
    public BigDecimal getCostPerSquareFoot(String productType) {
        BigDecimal costPerSquareFoot = null;
        try {
            loadProducts();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlooringStateDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String key : products.keySet()) {

            if (productType.equalsIgnoreCase(key)) {

                costPerSquareFoot = products.get(key).getCostPerSquareFoot();

            }

        }

        return costPerSquareFoot;
    }

    private void loadProducts() throws FileNotFoundException {
        Scanner scanner;

        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(PRODUCT_FILE)));

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product(currentTokens[0]);

            currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            
            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

            products.put(currentProduct.getProductType(), currentProduct);
        }
        // close scanner
        scanner.close();
    }

    @Override
    public BigDecimal getLaborCostPerSquareFoot(String productType) {
        BigDecimal laborCostPerSquareFoot = null;
        try {
            loadProducts();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlooringStateDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

       

                laborCostPerSquareFoot = products.get(productType).getLaborCostPerSquareFoot();

            

        
        return laborCostPerSquareFoot;

    }
}
