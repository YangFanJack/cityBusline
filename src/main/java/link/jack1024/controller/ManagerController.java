package link.jack1024.controller;

import link.jack1024.pojo.Manager;
import link.jack1024.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/managerLogin")
    public String login(String account, String password, Model model, HttpSession session){
        Manager manager = new Manager();
        manager.setAccount(account);
        manager.setPassword(password);
        boolean b = managerService.login(manager);
        if(b){
            model.addAttribute("msg","登录成功");
            session.setAttribute("managerAccount",account);
            return "redirect:findAllBusLine";
        }
        else{
            model.addAttribute("msg","用户名或密码错误");
            return "backPages/managerLogin";
        }
    }

    @RequestMapping("/addManager")
    public String add(String password,String rePassword,Model model,HttpSession session){

        return "";
    }

    @RequestMapping("/findAllManager")
    public String findAll(String msg, Model model){
        ArrayList<Manager> managerList = managerService.findAll();
        model.addAttribute("managerList",managerList);
        model.addAttribute("msg",msg);
        return "backPages/managerManage";
    }

    @RequestMapping("findManagerById")
    public String findById(int id){
        return "";
    }

    @RequestMapping("findManagerByAccount")
    public String findByAccount(String account){
        return "";
    }

    @RequestMapping("deleteManager")
    public String delete(int id,Model model){
        boolean b = managerService.delete(id);
        if(b){
            model.addAttribute("msg","删除管理员成功！");
        }
        else{
            model.addAttribute("msg","系统错误，删除管理员失败！");
        }
        return "redirect:findAllManager";
    }

    @RequestMapping("managerLogout")
    public String logout(HttpSession session){
        session.removeAttribute("managerAccount");
        return "backPages/managerLogin";
    }

    @RequestMapping(value="managerModifyPasswd",method= RequestMethod.POST)
    @ResponseBody
    public String modifyPasswd(@RequestParam("password") String password, @RequestParam("rePassword") String rePassword, HttpSession session){
        if(!Objects.equals(password,rePassword)){
            return "error1！";
        }
        String managerAccount = (String) session.getAttribute("managerAccount");
        Manager manager = new Manager();
        manager.setAccount(managerAccount);
        manager.setPassword(password);
        boolean b = managerService.modifyPasswd(manager);
        if(b){
            return "success!";
        }
        else{
            return "error2！";
        }
    }
}
