package link.jack1024.dao;

import link.jack1024.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserDao {
    /**
     * 查找所有
     * @return
     */
    @Select("select * from user order by id desc")
    public ArrayList<User> selectAll();

    /**
     * 根据邮箱查找
     * @param email
     * @return
     */
    @Select("select * from user where email = #{email}")
    public User selectByEmail(String email);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    public User selectById(int id);

    /**
     * 根据激活码查询用户
     * @param activeCode
     * @return
     */
    @Select("select * from user where active_code = #{activeCode}")
    public User selectByActiveCode(String activeCode);

    /**
     * 根据昵称查询用户
     * @param nickName
     * @return
     */
    @Select("select * from user where nick_name = #{nickName}")
    public User selectByNickName(String nickName);

    /**
     * 根据邮箱和密码查找
     * @param user
     * @return
     */
    @Select("select * from user where email = #{email} and password = #{password}")
    public User selectByUser(User user);

    /**
     * 新增
     * @param user
     */
    @Insert("insert into user(email,password,post_num,is_active,nick_name,active_code) values(#{email},#{password},#{postNum},#{isActive},#{nickName},#{activeCode})")
    public void insert(User user);

    /**
     * 更新密码
     * @param parameters
     */
    @Update("update user set password = #{password} where email = #{email}")
    public void updatePasswd(HashMap<String,Object> parameters);

    /**
     * 更新激活信息
     */
    @Update("update user set is_active = 1 where active_code = #{activeCode}")
    public void updateActive(String activeCode);

    /**
     * 更新发布贴子数
     */
    @Update("update user set post_num = post_num + 1 where id = #{id}")
    public void updatePostNum(int id);

    @Update("update user set active_code = #{vCode} where email = #{email}")
    public void updateVCode(HashMap<String,Object> parameters);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    public void delete(int id);
}
