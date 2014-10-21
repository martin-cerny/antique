/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antique;

import controller.ControllerServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 *
 * @author Black1
 */
public class ControllerServletTest {
    
    private ControllerServlet controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
    public ControllerServletTest() {
    }
    
    @Before
    public void setUp() {
        controller = new ControllerServletImpl();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test(expected=NullPointerException.class)
    public void testFailGetAction() {
        request.setServletPath("/send");
        request.setParameter("productId", "1000");
        assertEquals("/WEB-INF/view/confirmation", controller.createMessage(request, response));  
    }
    
//    @Test
//    public void testGetAction() {
//        request.setServletPath("/send");
//        request.setParameter("productId", "1");
//        assertEquals("/WEB-INF/view/confirmation", controller.createMessage(request, response));  
//    }
    
    public class ControllerServletImpl extends ControllerServlet {

        public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        }
    }
}
