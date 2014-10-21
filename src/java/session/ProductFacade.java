/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Product;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Black1
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {
    @PersistenceContext(unitName = "AntiqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    /**
     * 
     * @param user User that sells products
     * @return product sold by user
     */
    public List<Product> findByUser(Object user) {
        return em.createNamedQuery("Product.findByUser").setParameter("userId", user).getResultList();
    }

    /**
     * 
     * @param name String that will be searched in db
     * @return collection of products
     */
    public Collection<Product> findByPart(String name) {
        return em.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name OR p.description LIKE :name OR p.author LIKE :name OR p.isbn LIKE :name").setParameter("name", "%" + name + "%").getResultList();
    }
    
}
