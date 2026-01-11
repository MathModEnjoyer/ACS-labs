package com.example.javaee.dao;

import com.example.javaee.data.Yachtsman;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


import java.util.List;


@Stateless
public class YachtsmanDAO {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public void save(Yachtsman yachtsman) {
        em.persist(yachtsman);
    }

    public void update(Yachtsman yachtsman) {
        em.merge(yachtsman);
    }

    public void deleteById(Long id) {
        Yachtsman managed = em.find(Yachtsman.class, id);
        if (managed != null) {
            em.remove(managed);
        }
    }

    public Yachtsman getById(Long id) {
        return em.find(Yachtsman.class, id);
    }

    public List<Yachtsman> getAll() {
        return em.createQuery("select y from Yachtsman y", Yachtsman.class)
                 .getResultList();
    }

    public List<Yachtsman> getByBoatId(Long boatId) {
    return em.createQuery(
            "select y from Yachtsman y join y.boats b " +
            "where b.id = :bId", Yachtsman.class)
             .setParameter("bId", boatId)
             .getResultList();
    }

}
