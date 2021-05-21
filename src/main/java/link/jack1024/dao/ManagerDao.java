package link.jack1024.dao;

import link.jack1024.pojo.Manager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.HashMap;

public interface ManagerDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from manager order by id desc")
    public ArrayList<Manager> selectAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Manager selectById(int id);

    /**
     * 根据帐号查询
     * @param account
     * @return
     */
    @Select("select * from manager where account = #{account}")
    public Manager selectByAccount(String account);

    /**
     * 根据帐号和密码查询
     * @param manager
     * @return
     */
    @Select("select * from manager where account=#{account} and password=#{password}")
    public Manager selectByManager(Manager manager);

    /**
     * 新增管理员
     * @param manager
     */
    @Insert("insert into manager(account,password) values(#{account},#{password})")
    public void insert(Manager manager);

    /**
     * 更新管理员密码
     * @param parameters
     */
    @Update("update manager set password = #{password} where account = #{account}")
    public void updatePasswd(HashMap<String,Object> parameters);

    /**
     * 删除管理员
     * @param id
     */
    @Delete("delete from manager where id = #{id}")
    public void delete(int id);
}
