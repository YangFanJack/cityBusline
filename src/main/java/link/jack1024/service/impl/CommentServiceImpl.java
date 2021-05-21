package link.jack1024.service.impl;

import link.jack1024.dao.CommentDao;
import link.jack1024.dao.MessageDao;
import link.jack1024.pojo.Comment;
import link.jack1024.pojo.Message;
import link.jack1024.pojo.User;
import link.jack1024.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private MessageDao messageDao;

    @Override
    public boolean add(Comment comment) {
        commentDao.insert(comment);
        return false;
    }

    @Override
    public ArrayList<Comment> findAll() {
        ArrayList<Comment> comments = commentDao.selectAll();
        return comments;
    }

    @Override
    public Comment findById(int id) {
        return null;
    }

    @Override
    public ArrayList<Comment> findByPostId(int postId) {
        ArrayList<Comment> comments = commentDao.selectByPostId(postId);
        return comments;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Message message = new Message();
        Comment comment = commentDao.selectById(id);
        System.out.println("**********************************");
        System.out.println(comment);
        //删平评论消息内容
        message.setContent("由于您发表的位于贴子《"+comment.getPost().getTitle()+"》下的评论违规，评论内容为<br>("+comment.getContent()+")<br>现已删除该评论，望了解！");
        //获取当前系统时间并转化为字符串
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String time = sdf.format(date);
        message.setTime(time);
        User user = new User();
        user.setId(comment.getUser().getId());
        message.setUser(user);
        messageDao.insert(message);
        commentDao.delete(id);
        return true;
    }
}
