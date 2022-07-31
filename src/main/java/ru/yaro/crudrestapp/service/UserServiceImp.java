package ru.yaro.crudrestapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yaro.crudrestapp.dao.UserDao;
import ru.yaro.crudrestapp.model.User;

import java.util.List;

@Service("userDetailsService")

public class UserServiceImp implements UserDetailsService, UserService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImp() {
    }

    @Autowired
    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getByEmail(username);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);

    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }


    @Override
    public void deleteUser(User user) {
        userDao.delete(user);

    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);

    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteById(id);

    }
}
