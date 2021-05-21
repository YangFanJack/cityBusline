package link.jack1024.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import link.jack1024.pojo.Message;
import link.jack1024.pojo.Post;
import link.jack1024.service.MessageService;
import link.jack1024.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private PostService postService;

    @RequestMapping("findAllMessage")
    public String findAll(){
        return "";
    }

    @RequestMapping("findMessageById")
    public String findById(int id){
        return "";
    }

    @RequestMapping("findMessageByUserId")
    public String findByUserId(String msg, String searchString, Integer page,Integer pages, Model model, HttpSession session){
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
        ArrayList<Message> messageListAfter = null;
        if(searchString != null && !searchString.equals("")){
            searchString = "%"+searchString+"%";
            messageListAfter = messageService.findByUserIdBySearch(userId,searchString);
        }
        else{
            messageListAfter = messageService.findByUserId(userId);
            System.out.println("***************************");
            System.out.println(messageListAfter);
        }
        PageInfo<Message> messagePageInfo = new PageInfo<>(messageListAfter);
        List<Post> posts = null;
        if(postListBefore.size()>4){
            posts = postListBefore.subList(0, 4);
            model.addAttribute("postList",posts);
        }
        else{
            model.addAttribute("postList",postListBefore);
        }
        model.addAttribute("messagePageInfo",messagePageInfo);
        model.addAttribute("msg",msg);
        return "frontPages/myMessage";
    }

    @RequestMapping("deleteMessage")
    public String delete(int id, Model model){
        boolean b = messageService.delete(id);
        if(b){
            model.addAttribute("msg","贴子删除成功!");
        }
        else{
            model.addAttribute("msg","系统错误,删除失败!");
        }
        return "redirect:findMessageByUserId";
    }
}
