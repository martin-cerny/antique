/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Category;
import entity.Grouptable;
import entity.GrouptablePK;
import entity.Message;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CategoryFacade;
import session.GrouptableFacade;
import session.MessageFacade;
import session.ProductFacade;
import session.UserFacade;
import validate.Validator;

/**
 *
 * @author Black1
 */
@WebServlet(name = "Controller",
            loadOnStartup = 1,
            urlPatterns = {"/category",
                           "/product",
                           "/send",
                           "/registrace",
                           "/search"})
public class ControllerServlet extends HttpServlet {

    private String surcharge;
    

    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private ProductFacade productFacade;
    @EJB 
    private MessageFacade messageFacade;
    @EJB 
    private UserFacade userFacade;
    @EJB 
    private GrouptableFacade grouptableFacade;
    private String userPath;
    Validator validator = new Validator();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        // store category list in servlet context
        getServletContext().setAttribute("categories", categoryFacade.findAll());
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        userPath = request.getServletPath();
        HttpSession session = request.getSession();
        Category selectedCategory;
        Collection<Product> products;

        // if category page is requested
        if (userPath.equals("/category")) {
            String categoryId = request.getQueryString();
            if (categoryId != null) {
                selectedCategory = categoryFacade.find(Short.parseShort(categoryId));
                session.setAttribute("selectedCategory", selectedCategory);
                products = selectedCategory.getProductCollection();
                session.setAttribute("categoryProducts", products);
            }
            
        // if buy action is called
        } else if (userPath.equals("/product")) {
            String productId = request.getQueryString();
            if (!productId.isEmpty()) {
                Product product = productFacade.find(Integer.parseInt(productId));
                request.setAttribute("product", product);
            }  
        } else if (userPath.equals("/search")) {
            String name = request.getParameter("search");
            if (!name.isEmpty() || !name.equals("")) {
                products = productFacade.findByPart(name);
                session.setAttribute("products", products);
            }
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        userPath = request.getServletPath();

        // if send action is called 
        if (userPath.equals("/send")) {
            userPath = createMessage(request, response);
        // if signin action is called
        } else if (userPath.equals("/registrace")) {
            try {
                User user = new User();
                // MD5 hash function
                byte[] bytesOfPass = request.getParameter("pass").getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] passwordDigest = md.digest(bytesOfPass);
                
                BigInteger number = new BigInteger(1, passwordDigest);
                String password = number.toString(16);

                String username = request.getParameter("username");
                String address = request.getParameter("address");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");

                boolean validationErrorFlag = false;
                // make validation of sigin form
                validationErrorFlag = validator.validateSigninForm(username, email, phone, address, password, request);
                if (validationErrorFlag == true) {
                    request.setAttribute("validationErrorFlag", validationErrorFlag);
                    userPath = "/registrace";
                } else {
                    user.setUsername(username);
                    user.setAddress(address);
                    user.setEmail(email);
                    user.setPass(password);
                    user.setPhone(phone);

                    GrouptablePK grouptablePK = new GrouptablePK();
                    grouptablePK.setUsername(username);
                    grouptablePK.setGroupname("admin");
                    Grouptable groupUser = new Grouptable(grouptablePK);
                    // create user
                    userFacade.create(user);
                    grouptableFacade.create(groupUser);
                    userPath = "/index";
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

        // use RequestDispatcher to forward request internally
        String url = userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Create messages when buyer has interest
     * 
     * @param request
     * @param response 
     * @return  
     */
    public String createMessage(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        if (!productId.isEmpty()) {
            Product product = productFacade.find(Integer.parseInt(productId));
            Message message = new Message();
            message.setUserId(product.getUserId());
            message.setSender(request.getParameter("sender"));
            message.setPhone(Integer.parseInt(request.getParameter("phone")));
            message.setText(request.getParameter("text"));  
            messageFacade.create(message);   
        }
        return "/WEB-INF/view/confirmation";
    }


} 