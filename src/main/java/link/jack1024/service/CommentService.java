package link.jack1024.service;

import link.jack1024.pojo.Comment;

import java.util.ArrayList;

public interface CommentService {

    public boolean add(Comment comment);

    public ArrayList<Comment> findAll();

    public Comment findById(int id);

    public ArrayList<Comment> findByPostId(int postId);

    public boolean delete(int id);
}
