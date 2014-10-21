/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Category;
import entity.Product;
import entity.State;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import session.CategoryFacade;
import session.MessageFacade;
import session.ProductFacade;
import session.StateFacade;
import session.UserFacade;
import validate.Validator;

/**
 *
 * @author Black1
 */
@WebServlet(name = "UserServlet", 
        urlPatterns = {"/user/",
                       "/user/product",
                       "/user/viewProducts",
                       "/user/viewMessages",
                       "/user/logout",
                       "/user/sellBook",
                       "/user/displayBook"})
@ServletSecurity( @HttpConstraint(
        transportGuarantee = TransportGuarantee.CONFIDENTIAL,
        rolesAllowed = {"admin"}) )
@MultipartConfig(location = "D:\\vyvoj\\Antiq\\web\\img\\products")
public class UserServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private MessageFacade messageFacade;
    @EJB
    private StateFacade stateFacade;
    @EJB
    private CategoryFacade categoryFacade;
     
     
    private String userPath;
    private String url;
    private List messageList = new ArrayList();
    private List productList = new ArrayList();
    private List<Category> categoryList = new ArrayList<Category>();
    private String loggedUsername;        
    private Validator validator = new Validator();
        
    /**
     * Handles logout, displays books, messages and form for selling book
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        
        url = "/user/index.jsp";
        
        userPath = request.getServletPath();
        
        loggedUsername = request.getUserPrincipal().getName();
        User user = userFacade.findByUsername(loggedUsername);
        request.setAttribute("user", user);
        
        // if user wants to see selling Books
        if (userPath.equals("/user/viewProducts")) {
            productList = productFacade.findByUser(user);
            request.setAttribute("productList", productList);
        // if user wants to see received Messages
        } else if (userPath.equals("/user/viewMessages")) { 
            messageList = messageFacade.findByUser(user);
            request.setAttribute("messageList", messageList);
        // if user logout is called
        } else if (userPath.equals("/user/logout")) {
            url = doLogout(request, response);
            response.sendRedirect(url);
        // if displayBook is called
        } else if (userPath.equals("/user/displayBook")) {   
            getServletContext().setAttribute("categories", categoryFacade.findAll());
            url = "/user/sell.jsp";
        } else if (userPath.equals("/user/product")) {
            String productId = request.getQueryString();
            if (!productId.isEmpty()) {
                Product product = productFacade.find(Integer.parseInt(productId));
                request.setAttribute("product", product);
            }
        }
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.getStackTrace();
        }
        
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        userPath = request.getServletPath(); 
        
        // if user wants to sell another book
        if (userPath.equals("/user/sellBook")){
            doSell(request, response);
        }
        processRequest(request, response);
    }
    
    /*
    *  Handles selling of books
    *
    *  @param request servlet request
    *  @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
    */
    private void doSell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userFacade.findByUsername(request.getUserPrincipal().getName());
        
        Category category = categoryFacade.find(Short.parseShort(request.getParameter("category")));
        State state = stateFacade.find(Integer.parseInt(request.getParameter("state")));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String isbn = request.getParameter("isbn");
        
        Part part = request.getPart("photo");
        part.write(File.separator + name + ".jpg");

        boolean validationErrorFlag = false;
        validationErrorFlag = validator.validateSellForm(name, author, description, price, isbn, category, request);
        if (validationErrorFlag == true) {
            request.setAttribute("validationErrorFlag", validationErrorFlag);
            userPath = "/registrace";
        } else {
            createProduct(price, name, author, description, price, state, isbn, user, category); 
            url = "/user/index.jsp";
        }
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   
    
    /**
     * Get name of inserted photo
     * 
     * @param part Photo
     * @return name of inserting photo
     */
    public String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    /**
     * Create product while user wants to show it
     * 
     * @param price
     * @param name
     * @param author
     * @param description
     * @param price0
     * @param state
     * @param isbn
     * @param user
     * @param category 
     */
    private void createProduct(String price, String name, String author, String description, String price0, State state, String isbn, User user, Category category) {
        Product product = new Product();
        BigDecimal priceDecimal = new BigDecimal(price);
        product.setName(name);
        product.setAuthor(author);
        product.setDescription(description);
        product.setPrice(priceDecimal);
        product.setStateId(state);
        product.setIsbn(isbn);
        product.setUserId(user);
        product.setCategoryId(category);

        productFacade.create(product);
    }

    /**
     * Make logout of logged user
     * 
     * @param request
     * @param response
     * @throws IOException 
     */
    public String doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session;
        session = request.getSession();
        session.invalidate();
        return "/Antiq";
    }

}
