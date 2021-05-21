package link.jack1024.service;

import link.jack1024.pojo.Post;

import java.util.ArrayList;

public interface PostService {

    public boolean add(Post post,int userId);

    public ArrayList<Post> findAll();

    public Post findById(int id);

    public ArrayList<Post> findByUserId(int userId);

    public boolean delete1(int id);

    public boolean delete2(int id);

    ArrayList<Post> findAllBySearch(String searchString);

    ArrayList<Post> findByUserIdBySearch(int userId, String searchString);
}
