/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FlooringStateDaoImpl implements FlooringStateDao {

    public static final String STATE_FILE = "state.txt";
    public static final String DELIMITER = "::";

    private Map<String, State> states = new HashMap<>();

    @Override
    public BigDecimal getTaxRate(String state) {
        BigDecimal taxRate = null;
        try {
            loadStates();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlooringStateDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        

         

                taxRate = states.get(state).getTaxRate();

            

        

        return taxRate;
    }

    private void loadStates() throws FileNotFoundException {
        Scanner scanner;

        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(STATE_FILE)));

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            State currentState = new State(currentTokens[0]);

            currentState.setTaxRate(new BigDecimal(currentTokens[1]));

            states.put(currentState.getState(), currentState);
        }
        // close scanner
        scanner.close();
    }

}
