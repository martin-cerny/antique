/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antique;

import controller.UserServlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import javax.servlet.http.Part;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 *
 * @author Black1
 */
public class UserServletTest {
        
    private UserServlet controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
    public UserServletTest() {
    }
    
    @Before
    public void setUp() {
        controller = new UserServletTest.UserServletImpl();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testLogout() throws IOException {
        request.setServletPath("/user/logout");
        assertEquals("/Antiq", controller.doLogout(request, response));      
    }
    
    public class UserServletImpl extends UserServlet {

    }
    
}
