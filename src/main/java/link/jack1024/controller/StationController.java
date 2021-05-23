package link.jack1024.controller;

import link.jack1024.pojo.BusLine;
import link.jack1024.pojo.Station;
import link.jack1024.service.BusLineService;
import link.jack1024.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class StationController {
    @Autowired
    private StationService stationService;
    @Autowired
    private BusLineService busLineService;

    @RequestMapping(value="findSelectStation",method= RequestMethod.POST)
    @ResponseBody
    public ArrayList<Station> findSelect(@RequestParam("lineId") int lineId, HttpSession session){
        ArrayList<Station> stations = stationService.findSelect(lineId);
        return stations;
    }

    @RequestMapping(value="addStation1",method=RequestMethod.POST)
    @ResponseBody
    public String add1(@RequestParam("stationName") String stationName,@RequestParam("x") int x,
                       @RequestParam("y") int y,@RequestParam("lineId") int lineId){
        boolean b = stationService.add1(stationName,x,y,lineId);
        if(b){
            return "success";
        }
        else{
            return "fail";
        }

    }

    @RequestMapping(value="addStation2",method=RequestMethod.POST)
    @ResponseBody
    public String add2(@RequestParam("lineId") int lineId,@RequestParam("stationId") int stationId){
        boolean b = stationService.add2(stationId,lineId);
        if(b){
            return "success";
        }
        else{
            return "fail";
        }
    }

    @RequestMapping("/findAllStation")
    public String findAll(String msg, Model model){
        ArrayList<Station> stationList = stationService.findAll();
        model.addAttribute("stationList",stationList);
        model.addAttribute("msg",msg);
        return "backPages/siteManage";
    }

    @RequestMapping("/updateStation")
    public String update(int stationId, String stationName, Model model){
        boolean b = stationService.update(stationId, stationName);
        if(b){
            model.addAttribute("msg","更新车站成功！");
        }
        else{
            model.addAttribute("msg","系统错误，更新车站失败！");
        }
        return "redirect:findAllStation";
    }

    @RequestMapping("/deleteStation")
    public String delete(int id, Model model){
        boolean b = stationService.delete(id);
        if(b){
            model.addAttribute("msg","删除车站成功！");
        }
        else{
            model.addAttribute("msg","系统错误，删除车站失败！");
        }
        return "redirect:findAllStation";
    }

    @RequestMapping(value="findStationById",method= RequestMethod.POST)
    @ResponseBody
    public Station findById(@RequestParam("stationId") int stationId, HttpSession session){
        Station station = stationService.findById(stationId);
        return station;
    }

    @RequestMapping(value="deleteStationInLine",method= RequestMethod.POST)
    @ResponseBody
    public String deleteInLine(@RequestParam("stationId") int stationId, @RequestParam("lineId") int lineId, HttpSession session){
        boolean b = stationService.deleteInLine(stationId,lineId);
        if(b){
            return "success";
        }
        else{
            return "fail";
        }
    }

    @RequestMapping("/searchStation")
    @ResponseBody
    public Station searchStation(@RequestParam("stationName") String stationName){
        ArrayList<Station> stationList = stationService.findAll();
        for(Station s : stationList){
            if(s.getStationName().equals(stationName)){
                ArrayList<BusLine> busLines = new ArrayList<>();
                for(BusLine b : s.getBusLines()){
                    BusLine byId = busLineService.findById(b.getId());
//                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//                    for (Station ss : stations){
//                        System.out.println(ss);
//                    }
                    busLines.add(byId);
                }
                s.setBusLines(busLines);
                return s;
            }
        }
        return null;
    }
}
