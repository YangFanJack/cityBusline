package link.jack1024.service.impl;

import link.jack1024.dao.UserDao;
import link.jack1024.pojo.User;
import link.jack1024.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(User user) {
        User user1 = userDao.selectByUser(user);
        if(user1 != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean register(User user) {
        User user1 = userDao.selectByEmail(user.getEmail());
        User user2 = userDao.selectByNickName(user.getNickName());
        if(user1 != null || user2 != null){
            return false;
        }
        else{
            userDao.insert(user);
            return true;
        }
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = userDao.selectAll();
        return users;
    }

    @Override
    public User findByEmail(String email) {
        User user = userDao.selectByEmail(email);
        return user;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        userDao.delete(id);
        return true;
    }

    @Override
    public boolean modifyPasswd(User user) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("email",user.getEmail());
        parameters.put("password",user.getPassword());
        userDao.updatePasswd(parameters);
        return true;
    }

    @Override
    public boolean active(String activeCode) {
        User user = userDao.selectByActiveCode(activeCode);
        if(user != null){
            userDao.updateActive(activeCode);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean updateVCode(String vCode, String email) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("vCode",vCode);
        parameters.put("email",email);
        userDao.updateVCode(parameters);
        return true;
    }
}
