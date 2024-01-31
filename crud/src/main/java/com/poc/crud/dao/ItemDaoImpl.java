package com.poc.crud.dao;

import com.poc.crud.entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl implements ItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Item saveItem(Item item) {
        try {
            entityManager.unwrap(Session.class).save(item);
            System.out.print("Saved success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
