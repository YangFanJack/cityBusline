package link.jack1024.dao;

import link.jack1024.pojo.BusLine;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface BusLineDao {

    public ArrayList<BusLine> selectAllLinePoint();

    public ArrayList<BusLine> selectAllLineStation();

    @Select("select * from bus_line,line_station,station " +
            "where line_station.line_id = bus_line.id and line_station.station_id = station.id" +
            "and station_num = #{stationNum}")
    public ArrayList<BusLine> selectByStation(int stationNum);

    @Select("select * from bus_line,line_point,point " +
            "where line_point.line_id = bus_line.id and line_point.point_id = point.id" +
            "and point.id = #{pointId}")
    public ArrayList<BusLine> selectByPoint(int pointId);

    @Insert("insert into bus_line(line_num,line_price,begin_time,end_time) values(#{lineNum},#{linePrice},#{beginTime},#{endTime})")
    public void insert(BusLine busLine);

    @Select("select * from bus_line where line_num = #{lineNum}")
    public BusLine selectByNum(int lineNum);

    BusLine selectById(int lineId);

    @Update("update bus_line set begin_time=#{beginTime},end_time=#{endTime},line_price=#{linePrice} " +
            "where id=#{id}")
    void update(BusLine busLine);

    @Delete("delete from bus_line where id=#{id}")
    void delete(int id);
}
