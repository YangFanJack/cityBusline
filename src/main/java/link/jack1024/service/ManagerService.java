package link.jack1024.service;

import link.jack1024.pojo.Manager;
import link.jack1024.pojo.User;

import java.util.ArrayList;

public interface ManagerService {

    public boolean login(Manager manager);

    public boolean add(Manager manager);

    public ArrayList<Manager> findAll();

    public Manager findByAccount(String account);

    public Manager findById(int id);

    public boolean delete(int id);

    public boolean modifyPasswd(Manager manager);
}
