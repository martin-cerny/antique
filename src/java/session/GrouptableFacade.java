/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Grouptable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Black1
 */
@Stateless
public class GrouptableFacade extends AbstractFacade<Grouptable> {
    @PersistenceContext(unitName = "AntiqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrouptableFacade() {
        super(Grouptable.class);
    }
    
}
