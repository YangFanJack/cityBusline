package link.jack1024.dao;

import link.jack1024.pojo.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface CommentDao {
    /**
     * 查询所有
     * @return
     */
    public ArrayList<Comment> selectAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Comment selectById(int id);

    /**
     * 根据贴子id查询
     * @param postId
     * @return
     */
    public ArrayList<Comment> selectByPostId(int postId);

    /**
     * 发表评论
     * @param comment
     */
    @Insert("insert into comment(content,time,user_id,post_id) values(#{content},#{time},#{user.id},#{post.id})")
    public void insert(Comment comment);

    /**
     * 删除评论
     * @param id
     */
    @Delete("delete from comment where id = #{id}")
    public void delete(int id);
}
