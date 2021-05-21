package link.jack1024.service;

import link.jack1024.pojo.Message;

import java.util.ArrayList;

public interface MessageService {

    public boolean add(Message message);

    public ArrayList<Message> findAll();

    public Message findById(int id);

    public ArrayList<Message> findByUserId(int userId);

    public boolean delete(int id);

    ArrayList<Message> findByUserIdBySearch(int userId, String searchString);
}
