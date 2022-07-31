package ru.yaro.crudrestapp.dao;

import ru.yaro.crudrestapp.model.Role;

import java.util.Set;

public interface RoleDao {
    Role getByName(String name);

    void addRole(Role role);

    Set<Role> getAll();

    void addAllRoles(Set<Role> roles);
}
