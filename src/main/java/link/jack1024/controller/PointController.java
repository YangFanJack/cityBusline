package link.jack1024.controller;

import link.jack1024.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PointController {
    @Autowired
    private PointService pointService;

    @RequestMapping("addPoint")
    public String add(){
        return "";
    }
}
