package link.jack1024.controller;

import link.jack1024.pojo.Comment;
import link.jack1024.pojo.Post;
import link.jack1024.pojo.User;
import link.jack1024.service.CommentService;
import link.jack1024.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping("addComment")
    public String add(int postId, String content, Model model, HttpSession session){
        Object o = session.getAttribute("userId");
        if(o == null){
            model.addAttribute("msg","请先登录再评论！");
            return "redirect:findPostById?id="+postId;
        }
        Comment comment = new Comment();
        //获取当前系统时间并转化为字符串
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String time = sdf.format(date);
        comment.setTime(time);
        comment.setContent(content);
        User user = new User();
        user.setId((int)o);
        comment.setUser(user);
        Post post = new Post();
        post.setId(postId);
        comment.setPost(post);
        boolean b = commentService.add(comment);
        if(b){
            model.addAttribute("msg","评论成功！");
        }
        else{
            model.addAttribute("msg","系统错误，评论失败!");
        }
        return "redirect:findPostById?id="+postId;
    }

    @RequestMapping("findAllComment")
    public String findAll(String msg, Model model){
        ArrayList<Comment> commentList = commentService.findAll();
        model.addAttribute("commentList",commentList);
        model.addAttribute("msg",msg);
        return "backPages/commentManage";
    }

    @RequestMapping("findCommentById")
    public String findById(){
        return "";
    }

    @RequestMapping("findCommentByPostId")
    public String findByPostId(int postId, Model model){
        ArrayList<Comment> commentList = commentService.findByPostId(postId);
        model.addAttribute("commentList",commentList);
        return "frontPages/myMessage";
    }

    @RequestMapping("deleteComment")
    public String delete(int id,Model model,HttpSession session){
        boolean b = commentService.delete(id);
        if (b){
            model.addAttribute("msg","删除评论成功！");
        }
        else{
            model.addAttribute("msg","系统错误，删除评论失败！");
        }
        return "redirect:findAllComment";
    }
}
