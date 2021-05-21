package link.jack1024.service.impl;

import com.sun.corba.se.spi.activation.ServerAlreadyRegisteredHelper;
import link.jack1024.dao.CommentDao;
import link.jack1024.dao.MessageDao;
import link.jack1024.dao.PostDao;
import link.jack1024.dao.UserDao;
import link.jack1024.pojo.Comment;
import link.jack1024.pojo.Message;
import link.jack1024.pojo.Post;
import link.jack1024.pojo.User;
import link.jack1024.service.PostService;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private MessageDao messageDao;

    @Override
    public boolean add(Post post,int userId) {
        postDao.insert(post);
        userDao.updatePostNum(userId);
        return true;
    }

    @Override
    public ArrayList<Post> findAll() {
        ArrayList<Post> posts = postDao.selectAll();
        return posts;
    }

    @Override
    public Post findById(int id) {
        Post post = postDao.selectById(id);
        ArrayList<Comment> comments = commentDao.selectByPostId(id);
        post.setCommentList(comments);
        System.out.println();
        return post;
    }

    @Override
    public ArrayList<Post> findByUserId(int userId) {
        ArrayList<Post> postList = postDao.selectByUserId(userId);
        return postList;
    }

    @Override
    public boolean delete1(int id) {
        postDao.delete(id);
        return true;
    }

    @Override
    @Transactional
    public boolean delete2(int id) {
        Message message = new Message();
        Post post = postDao.selectById(id);
        System.out.println(post);
        //删平评论消息内容
        message.setContent("由于您发表的贴子《"+post.getTitle()+"》内容违规，现已删除该贴子，望了解！");
        //获取当前系统时间并转化为字符串
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String time = sdf.format(date);
        message.setTime(time);
        User user = new User();
        user.setId(post.getUser().getId());
        message.setUser(user);
        messageDao.insert(message);
        postDao.delete(id);
        return true;
    }

    @Override
    public ArrayList<Post> findAllBySearch(String searchString) {
        ArrayList<Post> posts = postDao.selectBySearch(searchString);
        return posts;
    }

    @Override
    public ArrayList<Post> findByUserIdBySearch(int userId, String searchString) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("searchString", searchString);
        parameters.put("userId",userId);
        ArrayList<Post> posts = postDao.selectByUserIdBySearch(parameters);
        return posts;
    }
}
