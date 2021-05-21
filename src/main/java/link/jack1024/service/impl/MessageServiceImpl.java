package link.jack1024.service.impl;

import link.jack1024.dao.MessageDao;
import link.jack1024.pojo.Message;
import link.jack1024.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public boolean add(Message message) {
        return false;
    }

    @Override
    public ArrayList<Message> findAll() {
        return null;
    }

    @Override
    public Message findById(int id) {
        return null;
    }

    @Override
    public ArrayList<Message> findByUserId(int userId) {
        ArrayList<Message> messages = messageDao.selectByUserId(userId);
        return messages;
    }

    @Override
    public boolean delete(int id) {
        messageDao.delete(id);
        return true;
    }

    @Override
    public ArrayList<Message> findByUserIdBySearch(int userId, String searchString) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("userId",userId);
        parameters.put("searchString",searchString);
        ArrayList<Message> messages = messageDao.selectByUserIdBySearch(parameters);
        return messages;
    }
}
