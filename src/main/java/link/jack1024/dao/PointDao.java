package link.jack1024.dao;

import link.jack1024.pojo.Point;
import link.jack1024.pojo.Station;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.HashMap;

public interface PointDao {
    @Select("select * from point where x=#{x} and y=#{y}")
    Point selectByXY(HashMap<String,Object> parameters);

    @Insert("insert into point(x,y) values(#{x},#{y})")
    void insert(Point p);

    @Insert("insert into line_point(line_id,point_id) values(#{line_id},#{point_id})")
    void insertLinePoint(HashMap<String,Object> parameters);

    @Select("select * from point")
    ArrayList<Point> selectAllPoints();

    @Select("select * from line_point where point_id=#{pointId}")
    ArrayList<Point> selectLinePoint(int pointId);

    @Delete("delete from point where id=#{id}")
    void delete(int id);
}
