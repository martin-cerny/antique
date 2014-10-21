/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antique;

import entity.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.mock.web.MockHttpServletRequest;
import validate.Validator;

/**
 *
 * @author Black1
 */
public class SellValidatorTest {
    
    
    Validator validator;
    String name = "Angličtina";
    String author = "Murphy";
    String description = "Často použitá";
    String price = "80";
    String isbn = "87521-1256-523";
    Category category = new Category();
    private MockHttpServletRequest request;
    
    public SellValidatorTest() {
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
    public void testSuccessValidator() {
        assertEquals(false, validate());
    }
    
    @Test
    public void testNameEmptyValidator() {
        name = "";
        assertEquals(true, validate());
    } 
    
    @Test
    public void testAuthorNullValidator() {
        author = null;
        assertEquals(true, validate());
    }  
    
    @Test
    public void testAuthorLengthValidator() {
        name = "NameNameNameNameNameNameNameNameNameName"
                + "NameNameNameNameNameNameNameNameNameName"
                + "NameNameNameNameNameNameNameNameNameName";
        assertEquals(true, validate());
    }  
    
    @Test
    public void testDescriptionEmptyValidator() {
        description = "";
        assertEquals(true, validate());
    }
    
    @Test
    public void testPriceNullValidator() {
        description = null;
        assertEquals(true, validate());
    }
    
    @Test
    public void testISBNShortValidator() {
        isbn = "123";
        assertEquals(true, validate());
    }
    
    @Test
    public void testCategoryEmptyValidator() {
        isbn = "";
        assertEquals(true, validate());
    }
    
    private boolean validate() {
        return validator.validateSellForm(name, author, description, price, isbn, category, request);
    }

}
