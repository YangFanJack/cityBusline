package link.jack1024.service.impl;

import link.jack1024.dao.ManagerDao;
import link.jack1024.pojo.Manager;
import link.jack1024.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;

    @Override
    public boolean login(Manager manager) {
        Manager manager1 = managerDao.selectByManager(manager);
        if(manager1!=null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean add(Manager manager) {
        managerDao.insert(manager);
        return true;
    }

    @Override
    public ArrayList<Manager> findAll() {
        ArrayList<Manager> managers = managerDao.selectAll();
        return managers;
    }

    @Override
    public Manager findByAccount(String account) {
        return null;
    }

    @Override
    public Manager findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        managerDao.delete(id);
        return true;
    }

    @Override
    public boolean modifyPasswd(Manager manager) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("account",manager.getAccount());
        parameters.put("password",manager.getPassword());
        managerDao.updatePasswd(parameters);
        return true;
    }
}
