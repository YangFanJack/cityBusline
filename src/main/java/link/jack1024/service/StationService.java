package link.jack1024.service;

import link.jack1024.pojo.Station;

import java.util.ArrayList;

public interface StationService {
    public ArrayList<Station> findSelect(int lineId);

    boolean add1(String stationName, int x, int y, int lineId);

    boolean add2(int stationId, int lineId);

    ArrayList<Station> findAll();

    boolean update(int stationId, String stationName);

    Station findById(int stationId);

    boolean delete(int id);

    boolean deleteInLine(int stationId, int lineId);
}
