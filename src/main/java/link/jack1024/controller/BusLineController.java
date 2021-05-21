package link.jack1024.controller;

import link.jack1024.pojo.*;
import link.jack1024.service.BusLineService;
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
public class BusLineController {
    @Autowired
    private BusLineService busLineService;

    @RequestMapping("addLine")
    public String add(int lineNum,double linePrice, String beginTime, String endTime, String[] points,Model model){
        BusLine busLine = new BusLine();
        busLine.setLineNum(lineNum);
        busLine.setLinePrice(linePrice);
        busLine.setBeginTime(beginTime);
        busLine.setEndTime(endTime);
        ArrayList<Point> p = new ArrayList<>();
        busLine.setPoints(p);
        for(int i=0;i<points.length;i++){
            String[] split = points[i].split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            Point point = new Point();
            point.setX(x);
            point.setY(y);
            busLine.getPoints().add(point);
        }
        boolean b = busLineService.add(busLine);
        if(b){
            model.addAttribute("msg","添加公交线路成功！");
        }
        else{
            model.addAttribute("msg","公交线路编号重复，请重试！");
        }
        return "redirect:findAllBusLine";
    }

    @RequestMapping("/findAllBusLine")
    public String findAll(String msg, Model model){
        ArrayList<BusLine> busLineList = busLineService.findAll();
        model.addAttribute("busLineList",busLineList);
        model.addAttribute("msg",msg);
        return "backPages/lineManage";
    }

    @RequestMapping(value="findBusLineById",method= RequestMethod.POST)
    @ResponseBody
    public BusLine findById(@RequestParam("lineId") int lineId, HttpSession session){
        BusLine busLine = busLineService.findById(lineId);
        return busLine;
    }

    @RequestMapping("updateLine")
    public String update(int lineId,String beginTime,String endTime,int linePrice,Model model){
        boolean b = busLineService.update(lineId,beginTime,endTime,linePrice);
        if(b){
            model.addAttribute("msg","更新线路信息成功！");
        }
        else{
            model.addAttribute("msg","系统错误，更新线路信息失败！");
        }
        return "redirect:findAllBusLine";
    }

    @RequestMapping("/deleteBusLine")
    public String delete(int id, Model model){
        boolean b = busLineService.delete(id);
        if(b){
            model.addAttribute("msg","删除线路成功！");
        }
        else{
            model.addAttribute("msg","系统错误，删除线路失败！");
        }
        return "redirect:findAllBusLine";
    }

    @RequestMapping(value="findMap",method= RequestMethod.POST)
    @ResponseBody
    public BusMap findMap(HttpSession session){
        ArrayList<BusLine> busLineList = busLineService.findAll();
        for(BusLine b : busLineList){
            b.setAllPoints(busLineList);
            b.setDistance();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            System.out.println(b.getPoints());
//            System.out.println(b.getStations());
//            System.out.println(b.getPointStations());
//            System.out.println(b.getAllPoints());
//            System.out.println(b.getDistance());
        }
        BusMap busMap = new BusMap();
        busMap.setBusLines(busLineList);
        busMap.setAllPointSet();
        busMap.setCountPointHash();
        busMap.setMap();

        //打印allPointSet集合
        for(Point p : busMap.getAllPointSet()){
            System.out.println(p);
        }

        //打印点编号：点的hashmap
        for(java.util.Map.Entry e : busMap.getCountPointHash().entrySet()){
            System.out.println(e.getKey()+" : "+e.getValue());
        }


        //打印邻接矩阵
        int[][] m = busMap.getMapArray();
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[i].length;j++){
                System.out.print(m[i][j] + "  ");
            }
            System.out.println();
        }

        busMap.recommend();

        return busMap;
    }


}
