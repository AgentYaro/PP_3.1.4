package ru.yaro.crudrestapp.service;

import ru.yaro.crudrestapp.model.Role;
import ru.yaro.crudrestapp.model.User;

import java.util.Set;

public interface RoleService {

    void setExistingRoles(User user);

    Set<Role> getAllRoles();

    void addRole(Role role);

    Role getRoleByName(String name);

    void addAllRoles(Set<Role> roles);

    void fillRoles(String... roleNames);

    Set<String> getRoleNames(Set<Role> roles);
}
