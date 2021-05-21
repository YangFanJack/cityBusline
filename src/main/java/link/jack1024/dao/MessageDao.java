package link.jack1024.dao;

import link.jack1024.pojo.Message;
import link.jack1024.pojo.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.HashMap;

public interface MessageDao {
    /**
     * 查询所有
     * @return
     */
    public ArrayList<Message> selectAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Message selectById(int id);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    @Select("select * from message where user_id = #{userId} order by id desc")
    public ArrayList<Message> selectByUserId(int userId);

    /**
     * 发送消息给某个用户
     * @param message
     */
    @Insert("insert into message(content,time,user_id) values(#{content},#{time},#{user.id})")
    public void insert(Message message);

    /**
     * 删除消息
     * @param id
     */
    @Delete("delete from message where id = #{id}")
    public void delete(int id);

    /**
     * 查找用户贴子（通过关键字搜索）
     * @param parameters
     * @return
     */
    @Select("select * from message where user_id = #{userId} and title like #{searchString} order by id desc")
    public ArrayList<Message> selectByUserIdBySearch(HashMap<String,Object> parameters);
}
