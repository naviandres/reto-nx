/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexos.facade;

import com.nexos.entity.Empleados;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andre
 */
@Stateless
public class EmpleadosFacade extends AbstractFacade<Empleados> implements EmpleadosFacadeLocal {

    @PersistenceContext(unitName = "com.nexos_nexos_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadosFacade() {
        super(Empleados.class);
    }

    @Override
    public List<Empleados> empleados(int primero, int pageSize) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query query = em.createQuery("FROM Empleados");
            query.setFirstResult(primero);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    @Override
    public int totalEmpleados() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query query = em.createQuery("SELECT COUNT(e.id) FROM Empleados e");
            return Integer.parseInt(query.getSingleResult().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
