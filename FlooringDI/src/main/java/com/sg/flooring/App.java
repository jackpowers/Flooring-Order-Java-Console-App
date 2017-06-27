/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring;

import com.sg.flooring.controller.FlooringController;
import com.sg.flooring.dao.FlooringOrderDao;
import com.sg.flooring.dao.FlooringOrderDaoImpl;
import com.sg.flooring.dao.FlooringProductDao;
import com.sg.flooring.dao.FlooringProductDaoImpl;
import com.sg.flooring.dao.FlooringStateDao;
import com.sg.flooring.dao.FlooringStateDaoImpl;
import com.sg.flooring.service.FlooringService;
import com.sg.flooring.service.FlooringServiceImpl;
import com.sg.flooring.ui.FlooringView;
import com.sg.flooring.ui.UserIO;
import com.sg.flooring.ui.UserIOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author admin
 */
public class App {

    public static void main(String[] args) {
//        UserIO myIo = new UserIOImpl();
//        FlooringView myView = new FlooringView(myIo);
//        FlooringOrderDao orderDao = new FlooringOrderDaoImpl();
//        FlooringProductDao productDao = new FlooringProductDaoImpl();
//        FlooringStateDao stateDao = new FlooringStateDaoImpl();
//
//       
//        FlooringService myService = 
//        new FlooringServiceImpl(orderDao, productDao, stateDao);
//        FlooringController controller = 
//        new FlooringController(myService, myView);
//        controller.run();
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller
                = ctx.getBean("controller", FlooringController.class);
        controller.run();
    }

}
