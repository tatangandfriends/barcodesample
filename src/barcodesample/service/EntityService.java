/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barcodesample.service;

import barcodesample.entity.BoomKunana;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Hadouken
 */
public class EntityService {
    private EntityManager em;
    private EntityTransaction et;
    
    
    @PostConstruct
    private void init(){
        this.em = (EntityManager)Persistence.createEntityManagerFactory("BarcodeSamplePU").createEntityManager();
        this.et = em.getTransaction();   
    }
    
    
    public void save(){
        em.flush();
    }
    
    public void save(BoomKunana b){
        et.begin();        
        BoomKunana merged = em.merge(b);
        et.commit();
    }
    
    public BoomKunana find(String name){
        Query query = em.createNamedQuery("BoomKunana.findByName");
        query.setParameter("employeeName", name);
        return (BoomKunana)query.getSingleResult();
    }
    
}
