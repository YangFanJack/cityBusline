package link.jack1024.dao;

import link.jack1024.pojo.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface NoticeDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from notice order by id desc")
    public ArrayList<Notice> selectAll();

    /**
     * 根据id查询通知
     * @param id
     * @return
     */
    @Select("select * from notice where id = #{id}")
    public Notice selectById(int id);

    /**
     * 发布通知
     * @param notice
     */
    @Insert("insert into notice(title,content,time,type) values(#{title},#{content},#{time},#{type})")
    public void insert(Notice notice);

    /**
     * 删除通知
     * @param id
     */
    @Delete("delete from notice where id = #{id}")
    public void delete(int id);

    @Select("select * from notice where title like #{searchString} order by id desc")
    public ArrayList<Notice> selectAllBySearch(String searchString);
}
