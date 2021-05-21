package link.jack1024.dao;

import link.jack1024.pojo.Point;
import link.jack1024.pojo.Station;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;

public interface StationDao {
    @Select("select station.id,x,y,station_name from bus_line,line_station,station " +
            "where bus_line.id=line_station.line_id and station.id=line_station.station_id " +
            "and bus_line.id=#{lineId}")
    ArrayList<Station> selectByLineId(int lineId);

    @Select("select station.id,x,y,station_name from bus_line,line_station,station " +
            "where bus_line.id=line_station.line_id and station.id=line_station.station_id " +
            "and bus_line.id!=#{lineId}")
    ArrayList<Station> selectSelect(int lineId);

    @Select("select * from station where x=#{x} and y=#{y}")
    Station selectByXY(HashMap<String,Object> parameters);

    @Insert("insert into line_station(line_id,station_id) values(#{line_id},#{station_id})")
    void insertLineStation(HashMap<String,Object> parameters);

    @Insert("insert into station(station_name,x,y) values(#{stationName},#{x},#{y})")
    void insert(Station station);

    ArrayList<Station> selectAll();

    @Select("select * from station")
    ArrayList<Station> selectAllStations();

    @Update("update station set station_name=#{stationName} where id=#{stationId}")
    void update(HashMap<String,Object> parameters);

    @Select("select id,x,y,station_name from station where id=#{stationId}")
    Station selectById(int stationId);

    @Delete("delete from station where id=#{id}")
    void delete(int id);

    @Delete("delete from line_station where line_id=#{lineId} and station_id=#{stationId}")
    void deleteInLine(HashMap<String, Object> parameters);

    @Select("select * from line_station where station_id=#{stationId}")
    ArrayList<Station> selectLineStation(int stationId);
}
