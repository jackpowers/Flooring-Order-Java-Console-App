/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import java.math.BigDecimal;

/**
 *
 * @author admin
 */
public interface FlooringStateDao {
    
    public BigDecimal getTaxRate(String state);
    
}
