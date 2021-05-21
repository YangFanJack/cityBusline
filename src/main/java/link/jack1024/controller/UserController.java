package link.jack1024.controller;

import link.jack1024.pojo.User;
import link.jack1024.service.MailSenderSrvService;
import link.jack1024.service.UserService;
import link.jack1024.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderSrvService mailSenderSrvService;

    @RequestMapping("/userLogin")
    public String login(String email, String password, Model model, HttpSession session){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        boolean b = userService.login(user);
        if(b){
            User u = userService.findByEmail(email);
            session.setAttribute("userId",u.getId());
            session.setAttribute("userEmail",u.getEmail());
            session.setAttribute("userNickName",u.getNickName());
            model.addAttribute("msg","登录成功！");
            return "frontPages/main";
        }
        else{
            model.addAttribute("msg","邮箱或密码错误，请重新输入！");
            return "frontPages/login";
        }
    }

    @RequestMapping("/userRegister")
    public String register(String email,String password,String rePassword,String nickName,Model model){
        if(!Objects.equals(password,rePassword)){
            model.addAttribute("msg","两次密码输入不一致");
            return "frontPages/register";
        }
        User user = new User();
        user.setPassword(password);
        user.setNickName(nickName);
        user.setEmail(email);
        user.setIsActive(0);
        user.setPostNum(0);
        //设置激活码，唯一
        user.setActiveCode(link.jack1024.utils.UuidUtil.getUuid());
        boolean b = userService.register(user);
        if(b){
            //发送激活账号邮件
            String mail = user.getEmail();
            String subject = "城市公交线路查询网帐号激活通知";
            String text = "<a href = 'http://localhost:8080/cityBusline_war_exploded/activeUser?activeCode="+user.getActiveCode()+"'>点击激活帐号【城市公交线路查询网】</a>";
            mailSenderSrvService.sendEmail(mail,subject,text);
            //注册成功信息
            String msg="注册成功，请前往邮箱激活账号方可登录!";
            model.addAttribute("msg",msg);
            return "frontPages/register";

        }
        else{
            String msg = "注册失败，昵称或者邮箱已被注册，请修改后重试!";
            model.addAttribute("msg",msg);
            return "frontPages/register";
        }
    }

    @RequestMapping("/findAllUser")
    public String findAll(String msg, Model model){
        ArrayList<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        model.addAttribute("msg",msg);
        return "backPages/userManage";
    }

    @RequestMapping("findUserById")
    public String findById(int id){
        return "";
    }

    @RequestMapping("findUserByAccount")
    public String findByAccount(String account){
        return "";
    }

    @RequestMapping("deleteUser")
    public String delete(int id,Model model){
        boolean b = userService.delete(id);
        if(b){
            model.addAttribute("msg","删除用户成功!");
        }
        else{
            model.addAttribute("msg","系统错误,删除失败!");
        }
        return "redirect:findAllUser";
    }

    @RequestMapping("userLogout")
    public String logout(HttpSession session){
        session.removeAttribute("userId");
        session.removeAttribute("userEmail");
        session.removeAttribute("userNickName");
        return "frontPages/main";
    }

    @RequestMapping("userModifyPasswd")
    public String modifyPasswd(String email,String password,String rePassword,String vCode,Model model){
        User u = userService.findByEmail(email);
        if(u==null){
            model.addAttribute("msg","该邮箱尚未注册，请先前往注册！");
        }
        else if (!Objects.equals(password,rePassword)){
            model.addAttribute("msg","两次密码输入不一致");
        }
        else if(Objects.equals(vCode,u.getActiveCode())){
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            boolean b = userService.modifyPasswd(user);
            if(b){
                model.addAttribute("msg","修改密码成功！");
            }
            else{
                model.addAttribute("msg","系统错误，密码修改失败！");
            }
        }
        else{
            model.addAttribute("msg","验证码验证失败！");
        }
        return "frontPages/changePasswd";
    }

    @RequestMapping(value="getVCode",method= RequestMethod.POST)
    @ResponseBody
    public String getVCode(@RequestParam("email") String email){
        String vCode = UuidUtil.getUuid();
        //发送验证码到邮箱
        String subject = "城市公交线路查询网帐号修改密码验证码获取";
        String text = "验证码是"+vCode;
        mailSenderSrvService.sendEmail(email,subject,text);
        boolean b = userService.updateVCode(vCode, email);
        if(b){
            return "success";
        }
        else{
            return "fail";
        }
    }

    @RequestMapping("activeUser")
    public String active(String activeCode,Model model){
        boolean b = userService.active(activeCode);
        if(b){
            String msg="邮箱激活账号成功!";
            model.addAttribute("msg",msg);
            return "frontPages/register";
        }
        else{
            String msg = "邮箱激活账号失败，请重试!";
            model.addAttribute("msg",msg);
            return "frontPages/register";
        }
    }
}
