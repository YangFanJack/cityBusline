package link.jack1024.service.impl;

import link.jack1024.dao.BusLineDao;
import link.jack1024.dao.StationDao;
import link.jack1024.pojo.BusLine;
import link.jack1024.pojo.Point;
import link.jack1024.pojo.Station;
import link.jack1024.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.ec.point.ProjectivePoint;

import java.util.ArrayList;
import java.util.HashMap;

@Service("stationService")
public class StationServiceImpl implements StationService {
    @Autowired
    private StationDao stationDao;
    @Autowired
    private BusLineDao busLineDao;

    @Override
    public ArrayList<Station> findSelect(int lineId) {
        ArrayList<Station> stations1 = stationDao.selectSelect(lineId);
        ArrayList<Station> stations2 = stationDao.selectByLineId(lineId);
        ArrayList<Station> stations3 = new ArrayList<>();
        for(Station s1 : stations1){
            boolean isSame = false;
            for(Station s2 : stations2){
                if(s1.equals(s2)){
                    isSame = true;
                }
            }
            if(!isSame){
                stations3.add(s1);
            }
        }
        BusLine busLine = busLineDao.selectById(lineId);
        ArrayList<Point> points = busLine.getPoints();
        ArrayList<Station> selectStations = new ArrayList<>();
        //选择可以选择的线路
        for(Point p : points){
            System.out.println(p);
        }
        for(Station s : stations3){
            boolean b = s.stationInLine(points);
            if(b){
                selectStations.add(s);
            }
        }
        return selectStations;
    }

    @Override
    @Transactional
    public boolean add1(String stationName, int x, int y, int lineId) {
        Station station = new Station();
        station.setStationName(stationName);
        station.setX(x);
        station.setY(y);
        BusLine busLine = busLineDao.selectById(lineId);
        ArrayList<Point> points = busLine.getPoints();
        boolean b = station.stationInLine(points);
        if(!b){
            return false;
        }
        stationDao.insert(station);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("x",x);
        parameters.put("y",y);
        Station s = stationDao.selectByXY(parameters);
        HashMap<String, Object> parameters2 = new HashMap<>();
        parameters2.put("line_id",lineId);
        parameters2.put("station_id",s.getId());
        stationDao.insertLineStation(parameters2);
        return true;
    }

    @Override
    public boolean add2(int stationId, int lineId) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("line_id",lineId);
        parameters.put("station_id",stationId);
        stationDao.insertLineStation(parameters);
        return false;
    }

    @Override
    public ArrayList<Station> findAll() {
        ArrayList<Station> stations = stationDao.selectAll();
        return stations;
    }

    @Override
    public boolean update(int stationId, String stationName) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("stationId",stationId);
        parameters.put("stationName",stationName);
        stationDao.update(parameters);
        return true;
    }

    @Override
    public Station findById(int stationId) {
        Station station = stationDao.selectById(stationId);
        return station;
    }

    @Override
    public boolean delete(int id) {
        stationDao.delete(id);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteInLine(int stationId, int lineId) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("stationId",stationId);
        parameters.put("lineId",lineId);
        stationDao.deleteInLine(parameters);
        ArrayList<Station> stations = stationDao.selectLineStation(stationId);
        if(stations.size() == 0){//或者stations==null
            stationDao.delete(stationId);
        }
        return true;
    }
}
