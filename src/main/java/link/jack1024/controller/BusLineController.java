package link.jack1024.controller;

import link.jack1024.pojo.*;
import link.jack1024.service.BusLineService;
import link.jack1024.utils.Dijkstra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

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
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            System.out.println(b.getLineNum());
            b.setBusLineNum();
        }
        for(BusLine b : busLineList){
            b.setAllPoints(busLineList);
            b.setDistance();
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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

        busMap.recommend(0);

        return busMap;
    }

    @RequestMapping("/searchLine")
    @ResponseBody
    public BusLine searchLine(@RequestParam("lineNum") int lineNum){
        BusLine busLine = busLineService.findByLineNum(lineNum);
        if(busLine!=null){
            BusLine byId = busLineService.findById(busLine.getId());
            return byId;
        }
        return null;
    }

    @RequestMapping("/searchShortest")
    @ResponseBody
    public ArrayList<ShortestPlan> searchShortest(@RequestParam("sourceStation") String sourceStation,@RequestParam("targetStation") String targetStation){
        ArrayList<BusLine> busLineList = busLineService.findAll();
        for(BusLine b : busLineList){
            b.setBusLineNum();
        }
        for(BusLine b : busLineList){
            b.setAllPoints(busLineList);
            b.setDistance();
        }
        BusMap busMap = new BusMap();
        busMap.setBusLines(busLineList);
        busMap.setAllPointSet();
        busMap.setCountPointHash();
        busMap.setMap();

        int[][] mapArrayEx = busMap.getMapArray();
        int[][] mapArray = new int[mapArrayEx.length][mapArrayEx.length];
        for(int i=0;i<mapArrayEx.length;i++){
            for(int j=0;j<mapArrayEx[0].length;j++){
                mapArray[i][j]=mapArrayEx[i][j];
            }
        }

        //打印邻接矩阵
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i=0;i<mapArray.length;i++){
            for(int j=0;j<mapArray[i].length;j++){
                System.out.print(mapArray[i][j] + "  ");
            }
            System.out.println();
        }


        LinkedHashSet<Point> allPointSet = busMap.getAllPointSet();
        int sourceNum = -1;
        int targetNum = -1;
        boolean b = false;
        for(Point p : allPointSet){
            if(p.getPointType()==2){
                Station p1 = (Station) p;
                if(p1.getStationName().equals(sourceStation)){
                    b = true;
                    sourceNum = p1.getPointNum();
                }
                if(p1.getStationName().equals(targetStation)){
                    b = true;
                    targetNum = p1.getPointNum();
                }
            }
        }
        if(!b){
            return null;
        }

        busMap.recommend(sourceNum);
        int[][] path = ShortestPlan.path;
        int[] shortest = ShortestPlan.shortest;
        System.out.println(Arrays.toString(path[targetNum]));
        System.out.println(shortest[targetNum]);
        HashMap<Integer, ArrayList<Point>> countPointHash = busMap.getCountPointHash();
        ArrayList<ArrayList<Point>> arrayLists = new ArrayList<>();
        for(int i=0;i<path[targetNum].length;i++){
            ArrayList<Point> points = countPointHash.get(path[targetNum][i]);
            arrayLists.add(points);
//            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//            System.out.println(path[targetNum][i]);
//            for(Point p : points){
//                if(p.getPointType()==1){
//                    System.out.println("1拐点："+p.getBusLineNum()+"号线，"+"("+p.getX()+","+p.getY()+")");
//                }
//                else if(p.getPointType()==2){
//                    Station p1 =(Station)p;
//                    System.out.println("2车站："+p.getBusLineNum()+"号线，"+p1.getStationName()+"("+p1.getX()+","+p1.getY()+")");
//                }
//                else{
//                    System.out.println("3交点："+p.getBusLineNum()+"号线，"+"("+p.getX()+","+p.getY()+")");
//                }
//            }
        }

        ShortestPlan shortestPlan = new ShortestPlan();
        shortestPlan.setSearchPath(path[targetNum]);
        shortestPlan.setSearchShortest(shortest[targetNum]);
        shortestPlan.setSearchPathStation(arrayLists);



        //打印邻接矩阵
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i=0;i<mapArray.length;i++){
            for(int j=0;j<mapArray[i].length;j++){
                System.out.print(mapArray[i][j] + "  ");
            }
            System.out.println();
        }

        boolean isChange = false;
        for(int i=1;i<path[targetNum].length-1;i++){
            int k = path[targetNum][i];
            for(int j=0;j<mapArray.length;j++){
                if(k!=j){//中线都是0
                    if(mapArray[k][j]!=-1){
                        isChange = true;
                        mapArray[k][j]=10000;
                    }
                    if(mapArray[j][k]!=-1){
                        isChange = true;
                        mapArray[j][k]=10000;
                    }
                }
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i=0;i<mapArray.length;i++){
            for(int j=0;j<mapArray[i].length;j++){
                System.out.print(mapArray[i][j] + "  ");
            }
            System.out.println();
        }

        busMap.setMapArray(mapArray);
        busMap.recommend(sourceNum);
        int[][] path2 = ShortestPlan.path;
        int[] shortest2 = ShortestPlan.shortest;
        ArrayList<ArrayList<Point>> arrayLists2 = new ArrayList<>();
        for(int i=0;i<path2[targetNum].length;i++) {
            ArrayList<Point> points = countPointHash.get(path2[targetNum][i]);
            arrayLists2.add(points);
        }
        ShortestPlan shortestPlan2 = new ShortestPlan();
        shortestPlan2.setSearchPath(path2[targetNum]);
        shortestPlan2.setSearchShortest(shortest2[targetNum]);
        shortestPlan2.setSearchPathStation(arrayLists2);

        ArrayList<ShortestPlan> shortestPlans = new ArrayList<>();
        shortestPlans.add(shortestPlan);
        if(shortestPlan2.getSearchShortest()<10000 && isChange){
            shortestPlans.add(shortestPlan2);
        }
        return shortestPlans;
    }


}
