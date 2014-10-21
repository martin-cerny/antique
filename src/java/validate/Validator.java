/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package validate;

import entity.Category;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Black
 */
public class Validator {

    /*
    * performs validation on signin form
    * validation is called from servlet and displayed in jsp
    * all validations are made together
    */
    public boolean validateSigninForm(String username, String email, String phone,
                        String address, String pass, HttpServletRequest request) {

        boolean errorFlag = false;
        boolean usernameError;
        boolean emailError;
        boolean phoneError;
        boolean addressError;
        boolean passError;

        if (username == null || username.equals("") || username.length() > 45) {
            errorFlag = true;
            usernameError = true;
            request.setAttribute("usernameError", usernameError);
        }
        if (email == null || email.equals("") || !email.contains("@")) {
            errorFlag = true;
            emailError = true;
            request.setAttribute("emailError", emailError);
        }
        if (phone == null || phone.equals("") || phone.length() < 9) {
            errorFlag = true;
            phoneError = true;
            request.setAttribute("phoneError", phoneError);
        }
        if (address == null || address.equals("") || address.length() > 45) {
            errorFlag = true;
            addressError = true;
            request.setAttribute("addressError", addressError);
        }
        if (pass == null || pass.equals("") || pass.length() < 3) {
            errorFlag = true;
            passError = true;
            request.setAttribute("passError", passError);
        }

        return errorFlag;
    }
    
    // performs validation on sell form
    // all validations are made together
    public boolean validateSellForm(String name,
                                String author,
                                String description,
                                String price,
                                String isbn,
                                Category category,
                                HttpServletRequest request) {

        boolean errorFlag = false;
        boolean nameError;
        boolean authorError;
        boolean descriptionError;
        boolean priceError;
        boolean isbnError;
        boolean categoryError;

        if (name == null || name.equals("") || name.length() > 45) {
            errorFlag = true;
            nameError = true;
            request.setAttribute("nameError", nameError);
        }
        if (author == null || author.equals("") || name.length() > 45) {
            errorFlag = true;
            authorError = true;
            request.setAttribute("authorError", authorError);
        }
        if (description == null || description.equals("")) {
            errorFlag = true;
            descriptionError = true;
            request.setAttribute("descriptionError", descriptionError);
        }
        if (price == null || price.equals("")) {
            errorFlag = true;
            priceError = true;
            request.setAttribute("priceError", priceError);
        }
        if (isbn == null || isbn.equals("") || isbn.length() < 9) {
            errorFlag = true;
            isbnError = true;
            request.setAttribute("isbnError", isbnError);
        }
        if (category == null || category.equals("")) {
            errorFlag = true;
            categoryError = true;
            request.setAttribute("categoryError", categoryError);
        }

        return errorFlag;
    }

}