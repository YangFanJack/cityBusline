package link.jack1024.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import link.jack1024.pojo.Notice;
import link.jack1024.pojo.Post;
import link.jack1024.pojo.User;
import link.jack1024.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping("addNotice")
    public String add(String title, int type, String content, Model model, HttpSession session){
        //获取当前系统时间并转化为字符串
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String time = sdf.format(date);
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setTime(time);
        notice.setType(type);
        boolean b = noticeService.add(notice);
        if(b){
            model.addAttribute("msg","通知发布成功！");
        }
        else{
            model.addAttribute("msg","系统错误，通知发布失败！");
        }
        return "redirect:findAllNotice2";
    }

    @RequestMapping("findAllNotice")
    public String findAll(String searchString, Integer page, Integer pages, Model model, HttpSession session){
        model.addAttribute("searchString",searchString);
        ArrayList<Notice> noticeListBefore = noticeService.findALl();
        //使用pageHelper插件
        int pageSize = 6;
        if(page==null || page==0){
            page = 1;
        }
        if(pages!=null && page>=pages){
            page=pages;
        }
        PageHelper.startPage(page,pageSize);
        ArrayList<Notice> noticeListAfter = null;
        if(searchString != null && !searchString.equals("")){
            searchString = "%"+searchString+"%";
            noticeListAfter = noticeService.findAllBySearch(searchString);
        }
        else{
            noticeListAfter = noticeService.findALl();
        }
        PageInfo<Notice> postPageInfo = new PageInfo<>(noticeListAfter);
        List<Notice> notices = null;
        if(noticeListBefore.size()>4){
            notices = noticeListBefore.subList(0, 4);
            model.addAttribute("noticeList",notices);
        }
        else{
            model.addAttribute("noticeList",noticeListBefore);
        }
        model.addAttribute("noticePageInfo",postPageInfo);
        return "frontPages/notice";
    }

    @RequestMapping("findNoticeById")
    public String findById(int id,Model model,HttpSession session){
        Notice notice = noticeService.findById(id);
        model.addAttribute("notice",notice);
        ArrayList<Notice> noticeList = noticeService.findALl();
        List<Notice> notices = null;
        if(noticeList.size()>4){
            notices = noticeList.subList(0, 4);
            model.addAttribute("noticeList",notices);
        }
        else{
            model.addAttribute("noticeList",noticeList);
        }
        return "frontPages/detailNotice";
    }

    @RequestMapping("deleteNotice")
    public String delete(int id, Model model){
        boolean b = noticeService.delete(id);
        if(b){
            model.addAttribute("msg","贴子删除成功!");
        }
        else{
            model.addAttribute("msg","系统错误,删除失败!");
        }
        return "redirect:findAllNotice2";
    }

    @RequestMapping("findAllNotice2")
    public String findAll2(String msg, Model model, HttpSession session){
        ArrayList<Notice> postList = noticeService.findALl();
        model.addAttribute("noticeList",postList);
        model.addAttribute("msg",msg);
        return "backPages/noticeManage";
    }
}
