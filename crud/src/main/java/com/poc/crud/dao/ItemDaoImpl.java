package com.poc.crud.dao;

import com.poc.crud.entity.Item;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ItemDaoImpl implements ItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Item saveItem(Item item) {
        try {
            entityManager.unwrap(Session.class).saveOrUpdate(item);
            System.out.print("Saved success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
