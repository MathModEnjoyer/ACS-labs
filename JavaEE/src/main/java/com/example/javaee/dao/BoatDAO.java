package com.example.javaee.dao;

import com.example.javaee.dao.BoatDAO;
import com.example.javaee.data.Boat;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class BoatDAO {

    @PersistenceContext
    private EntityManager em;

 
    public void save(Boat boat) {
        em.persist(boat);
    }


    public void update(Boat boat) {
        em.merge(boat);
    }


    public void deleteById(Long id) {
        em.remove(em.find(Boat.class, id));
    }


    public Boat getById(Long id) {
        return em.find(Boat.class, id);
    }


    public List<Boat> getAll() {
        return em.createQuery("select b from Boat b", Boat.class)
                 .getResultList();
    }

    public List<Boat> getByYachtsmanId(Long yachtsmanId) {
        return em.createQuery(
                "select b from Boat b join b.yachtsmen y " +
                "where y.id = :yId", Boat.class)
                .setParameter("yId", yachtsmanId)
                .getResultList();
    }
}
