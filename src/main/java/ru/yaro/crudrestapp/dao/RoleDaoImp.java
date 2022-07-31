package ru.yaro.crudrestapp.dao;

import org.springframework.stereotype.Repository;
import ru.yaro.crudrestapp.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Role getByName(String name) {
        return (Role) entityManager.createQuery("select role from Role role where role.name=?1").setParameter(1, name).getSingleResult();
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        entityManager.merge(role);
    }

    @Transactional
    @Override
    public Set<Role> getAll() {
        TypedQuery<Role> query = entityManager.createQuery("select role from Role role", Role.class);
        return new HashSet<>(query.getResultList());
    }

    @Transactional
    @Override
    public void addAllRoles(Set<Role> roles) {
        for (Role role : roles) {
            addRole(role);
        }
    }
}

