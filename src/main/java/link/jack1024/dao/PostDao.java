package link.jack1024.dao;

import link.jack1024.pojo.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.HashMap;

public interface PostDao {
    /**
     * 查找所有贴子
     * @return
     */
    public ArrayList<Post> selectAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Post selectById(int id);

    /**
     * 更具用户id查找
     * @param userId
     * @return
     */
    @Select("select * from post where user_id = #{userId} order by id desc")
    public ArrayList<Post> selectByUserId(int userId);

    /**
     * 发布贴子
     * @param post
     */
    @Insert("insert into post(title,content,time,user_id) values(#{title},#{content},#{time},#{user.id})")
    public void insert(Post post);

    /**
     * 删除贴子
     * @param id
     */
    @Delete("delete from post where id = #{id}")
    public void delete(int id);

    /**
     * 查找所有贴子（通过关键字搜索）
     * @param searchString
     * @return
     */
    @Select("select * from post where title like #{searchString} order by id desc")
    public ArrayList<Post> selectBySearch(String searchString);

    /**
     * 查找用户贴子（通过关键字搜索）
     * @param parameters
     * @return
     */
    @Select("select * from post where user_id = #{userId} and title like #{searchString} order by id desc")
    public ArrayList<Post> selectByUserIdBySearch(HashMap<String,Object> parameters);
}
