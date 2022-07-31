package ru.yaro.crudrestapp.dao;

import org.springframework.stereotype.Repository;
import ru.yaro.crudrestapp.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public List<User> getAll() {
        TypedQuery<User> query = entityManager.createQuery("select user from User user", User.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        entityManager.remove(getById(id));
    }

    @Transactional
    @Override
    public User getByEmail(String email) {
        return (User) entityManager.createQuery("select user from User user where user.email = ?1").setParameter(1, email).getSingleResult();
    }
}
