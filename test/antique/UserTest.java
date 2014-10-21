package antique;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.User;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import session.UserFacade;

/**
 *
 * @author Black1
 */
public class UserTest {
    
    private static final String TEST_USERNAME = "Martin";
    private static final String TEST_PASSWORD = "123456";
    private static final String TEST_EMAIL = "jakjakjak@seznam.cz";
    
    private User user1;
    private User user2;
    @EJB
    private UserFacade userFacade;
    
    @Mock
    @PersistenceContext(unitName="AntiqPU")
    private EntityManager em;
    
    public UserTest() {
    }
    
    @Before
    public void setUp() {
        prepareUsers();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetUserName() {
        user1.setUsername(TEST_USERNAME);
        Assert.assertEquals(TEST_USERNAME, user1.getUsername());
    }
    
    @Test
    public void testSetPassword(){
        user1.setPass(TEST_PASSWORD);
        Assert.assertEquals(TEST_PASSWORD, user1.getPass());
    }
    
    @Test
    public void testSetEmail(){
        user1.setEmail(TEST_EMAIL);
        Assert.assertEquals(TEST_EMAIL, user1.getEmail());
    }
    
//    @Test
//    public void testCreate() {
//        user1.setPhone("720526578");
//        user1.setId(1000);
//        userFacade.create(user1);
//    }

    private void prepareUsers() {
        user1 = new User();
        user2 = new User();
    }
    
}
