/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antique;

import entity.User;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import validate.Validator;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 *
 * @author Black1
 */
public class SigninValidatorTest {
    
    Validator validator;
    String username = "Martin";
    String email= "cerny.mrtn@gmail.com"; 
    String phone= "720526578";
    String address = "Praha 10";
    String pass = "123456";
    User user;
    private MockHttpServletRequest request;
    
    
    public SigninValidatorTest() {
    }
    
    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        validator = new Validator();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSuccessValidator(){
        assertEquals(false, validate());
    }

    @Test
    public void testUsernameEmptyValidator(){
        username = "";
        assertEquals(true, validate());
    }
    
    @Test
    public void testUsernameLengthValidator(){
        username = "usernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusername";
        assertEquals(true, validate());
    }
    
    @Test
    public void testEmailValidator(){
        email = "cerny.mrtn[at]gmail.com";
        assertEquals(true, validate());
    }  
        
    @Test
    public void testEmailEmptyValidator(){
        email = "";
        assertEquals(true, validate());
    }   
    
    @Test
    public void testPhoneShortValidator(){
        phone = "7205";
        assertEquals(true, validate());
    }
    
    @Test
    public void testPhoneNullValidator(){
        phone = null;
        assertEquals(true, validate());
    }
    
    @Test
    public void testAddressEmptyValidator(){
        address = "";
        assertEquals(true, validate());
    }
    
    @Test
    public void testPasswordShortValidator(){
        pass = "12";
        assertEquals(true, validate());
    }
    
    private boolean validate() {
        return validator.validateSigninForm(username, email, phone, address, pass, request);
    }

}
