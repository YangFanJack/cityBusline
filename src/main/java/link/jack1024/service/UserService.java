package link.jack1024.service;

import link.jack1024.pojo.User;

import java.util.ArrayList;

public interface UserService {

    public boolean login(User user);

    public boolean register(User user);

    public ArrayList<User> findAll();

    public User findByEmail(String email);

    public User findById(int id);

    public boolean delete(int id);

    public boolean modifyPasswd(User user);

    public boolean active(String activeCode);

    public boolean updateVCode(String vCode,String email);
}
