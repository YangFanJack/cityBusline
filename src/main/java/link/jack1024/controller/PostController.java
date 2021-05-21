package link.jack1024.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.corba.se.spi.activation.ServerAlreadyRegisteredHelper;
import com.sun.mail.imap.protocol.SearchSequence;
import link.jack1024.pojo.Notice;
import link.jack1024.pojo.Post;
import link.jack1024.pojo.User;
import link.jack1024.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("addPost")
    public String add(String title, String content, Model model, HttpSession session){
        //获取当前系统时间并转化为字符串
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String time = sdf.format(date);
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setTime(time);
        User user = new User();
        user.setId((int) session.getAttribute("userId"));
        post.setUser(user);
        int userId = (int) session.getAttribute("userId");
        boolean b = postService.add(post, userId);
        if(b){
            model.addAttribute("msg","发布贴子成功！");
        }
        else{
            model.addAttribute("msg","系统错误，发布贴子失败！");
        }
        return "redirect:findPostByUserId";
    }

    @RequestMapping("findAllPost")
    public String findAll(String searchString, Integer page, Integer pages, Model model, HttpSession session){
        model.addAttribute("searchString",searchString);
        ArrayList<Post> postListBefore = postService.findAll();
        //使用pageHelper插件
        int pageSize = 6;
        if(page==null || page==0){
            page = 1;
        }
        if(pages!=null && page>=pages){
            page=pages;
        }
        PageHelper.startPage(page,pageSize);
        ArrayList<Post> postListAfter = null;
        if(searchString!=null && !searchString.equals("")){
            searchString = "%"+searchString+"%";
            postListAfter = postService.findAllBySearch(searchString);
        }
        else{
            postListAfter = postService.findAll();
        }
        PageInfo<Post> postPageInfo = new PageInfo<>(postListAfter);
        List<Post> posts = null;
        if(postListBefore.size()>4){
            posts = postListBefore.subList(0, 4);
            model.addAttribute("postList",posts);
        }
        else{
            model.addAttribute("postList",postListBefore);
        }
        model.addAttribute("postPageInfo",postPageInfo);
        return "frontPages/forum";
    }

    @RequestMapping("findPostById")
    public String findById(String msg, int id,Model model,HttpSession session){
        Post post = postService.findById(id);
        model.addAttribute("post",post);
        ArrayList<Post> postList = postService.findAll();
        List<Post> posts = null;
        if(postList.size()>4){
            posts = postList.subList(0, 4);
            model.addAttribute("postList",posts);
        }
        else{
            model.addAttribute("postList",postList);
        }
        model.addAttribute("msg",msg);
        return "frontPages/detailForum";
    }

    @RequestMapping("findPostByUserId")
    public String findByUserId(String msg, String searchString, Integer page, Integer pages, Model model, HttpSession session){
        model.addAttribute("searchString", searchString);
        int userId = (int) session.getAttribute("userId");
        ArrayList<Post> postListBefore = postService.findByUserId(userId);
        //使用pageHelper插件
        int pageSize = 6;
        if(page==null || page==0){
            page = 1;
        }
        if(pages!=null && page>=pages){
            page=pages;
        }
        PageHelper.startPage(page,pageSize);
        ArrayList<Post> postListAfter = null;
        if(searchString != null && !searchString.equals("")){
            searchString = "%"+searchString+"%";
            postListAfter = postService.findByUserIdBySearch(userId,searchString);
        }
        else{
            postListAfter = postService.findByUserId(userId);
        }
        PageInfo<Post> postPageInfo = new PageInfo<>(postListAfter);
        List<Post> posts = null;
        if(postListBefore.size()>4){
            posts = postListBefore.subList(0, 4);
            model.addAttribute("postList",posts);
        }
        else{
            model.addAttribute("postList",postListBefore);
        }
        model.addAttribute("postPageInfo",postPageInfo);
        model.addAttribute("msg",msg);
        return "frontPages/myForum";
    }

    @RequestMapping("deletePost")
    public String delete(int id,Model model){
        boolean b = postService.delete1(id);
        if(b){
            model.addAttribute("msg","贴子删除成功!");
        }
        else{
            model.addAttribute("msg","系统错误,删除失败!");
        }
        return "redirect:findPostByUserId";
    }

    @RequestMapping("deletePost2")
    public String delete2(int id,Model model,HttpSession session){
        boolean b = postService.delete2(id);
        if(b){
            model.addAttribute("msg","贴子删除成功!");
        }
        else{
            model.addAttribute("msg","系统错误,删除失败!");
        }
        return "redirect:findAllPost2";
    }

    @RequestMapping("findAllPost2")
    public String findAll2(String msg, Model model, HttpSession session){
        ArrayList<Post> postList = postService.findAll();
        model.addAttribute("postList",postList);
        model.addAttribute("msg",msg);
        return "backPages/postManage";
    }

}
