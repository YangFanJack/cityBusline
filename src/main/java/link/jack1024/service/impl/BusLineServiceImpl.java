package link.jack1024.service.impl;

import link.jack1024.dao.BusLineDao;
import link.jack1024.dao.PointDao;
import link.jack1024.dao.StationDao;
import link.jack1024.pojo.BusLine;
import link.jack1024.pojo.Point;
import link.jack1024.pojo.Station;
import link.jack1024.service.BusLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service("busLineService")
public class BusLineServiceImpl implements BusLineService {
    @Autowired
    private BusLineDao busLineDao;
    @Autowired
    private PointDao pointDao;
    @Autowired
    private StationDao stationDao;

    @Override
    @Transactional
    public boolean add(BusLine busLine) {
        //插入公交线路
        BusLine bl1 = busLineDao.selectByNum(busLine.getLineNum());
        if (bl1 != null){
            return false;
        }
        busLineDao.insert(busLine);
        //获得数据库中的线路id
        BusLine bl2 = busLineDao.selectByNum(busLine.getLineNum());
        //插入组成点
        ArrayList<Point> points = busLine.getPoints();
        for(Point p : points){
            HashMap<String, Object> parameters1 = new HashMap<>();
            parameters1.put("x",p.getX());
            parameters1.put("y",p.getY());
            Point point = pointDao.selectByXY(parameters1);
            if(point == null){
                pointDao.insert(p);
            }
            point = pointDao.selectByXY(parameters1);
            HashMap<String, Object> parameters2 = new HashMap<>();
            parameters2.put("line_id",bl2.getId());
            parameters2.put("point_id",point.getId());
            pointDao.insertLinePoint(parameters2);
        }
        return true;
    }

    @Override
    public ArrayList<BusLine> findAll() {
        ArrayList<BusLine> busLines = busLineDao.selectAllLinePoint();
        System.out.println("***************************************");
        for(BusLine b : busLines){
            //如果有车站添加车站
            ArrayList<Station> stations = stationDao.selectByLineId(b.getId());
            b.setStations(stations);
//            System.out.println("这是"+b.getLineNum()+"路车,路线上的组合点是：");
//            for(Point p : b.getPoints()){
//                System.out.println(p);
//            }
//            System.out.println("这是"+b.getLineNum()+"路车,线路上的车站点是：");
//            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//            for (Station s : b.getStations()){
//                System.out.println(s);
//            }
            b.setType();
            b.setPointStations();
            b.setStationsOrder();
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//            for (Station s : b.getStations()){
//                System.out.println(s);
//            }
        }
        return busLines;
    }

    @Override
    public BusLine findById(int lineId) {
        BusLine busLine = busLineDao.selectById(lineId);
        ArrayList<Station> stations = stationDao.selectByLineId(lineId);
        busLine.setStations(stations);
//        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        for (Station s : busLine.getStations()){
//            System.out.println(s);
//        }
        busLine.setType();
        busLine.setPointStations();
        busLine.setStationsOrder();
//        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//        for (Station s : busLine.getStations()){
//            System.out.println(s);
//        }
        return busLine;
    }

    @Override
    public boolean update(int lineId, String beginTime, String endTime, int linePrice) {
        BusLine busLine = new BusLine();
        busLine.setId(lineId);
        busLine.setBeginTime(beginTime);
        busLine.setEndTime(endTime);
        busLine.setLinePrice(linePrice);
        busLineDao.update(busLine);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        busLineDao.delete(id);
        //删掉残留station
        ArrayList<Station> stations = stationDao.selectAllStations();
        for(Station s : stations){
            ArrayList<Station> stations1 = stationDao.selectLineStation(s.getId());
            if(stations1.size() == 0){
                stationDao.delete(s.getId());
            }
        }
        //删掉残留point
        ArrayList<Point> points = pointDao.selectAllPoints();
        for (Point p : points){
            ArrayList<Point> points1 = pointDao.selectLinePoint(p.getId());
            if(points1.size()==0){
                pointDao.delete(p.getId());
            }
        }
        return true;
    }
}
