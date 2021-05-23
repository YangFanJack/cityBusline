package link.jack1024.service;

import link.jack1024.pojo.BusLine;

import java.util.ArrayList;

public interface BusLineService {
    boolean add(BusLine busLine);

    ArrayList<BusLine> findAll();

    BusLine findById(int lineId);

    boolean update(int lineId, String beginTime, String endTime, int linePrice);

    boolean delete(int id);

    BusLine findByLineNum(int lineNum);
}
