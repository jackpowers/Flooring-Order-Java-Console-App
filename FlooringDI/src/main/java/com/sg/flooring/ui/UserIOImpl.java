/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class UserIOImpl implements UserIO{
    @Override
    public void print(String message) {

        System.out.println(message);

    }

    @Override
    public int readInt(String prompt) throws NumberFormatException{

        print(prompt);
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        int intVal = Integer.parseInt(input);

        return intVal;

    }

    @Override
    public int readInt(String prompt, int min, int max) throws NumberFormatException{

        boolean isValid = false;
        int result;
        do {
            result = readInt(prompt);
            isValid = result >= min && result <= max;
        } while (isValid == false);
        return result;

    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) throws NumberFormatException{
        print(prompt);
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
      
        BigDecimal bigDecimalVal = new BigDecimal(input);
        
        return bigDecimalVal;
    }
}
