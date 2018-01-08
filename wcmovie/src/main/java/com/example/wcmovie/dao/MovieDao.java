package com.example.wcmovie.dao;

import com.example.wcmovie.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class MovieDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    * 获取电影评论
    * */
    public List<Comment> getMovieComments(String username, String movieId) {
        String sql = "select * from movie_comment where username=? and movieid=?";
        return jdbcTemplate.query(sql, new Object[]{username, movieId}, new BeanPropertyRowMapper<Comment>(Comment.class));
//        return jdbcTemplate.queryForObject(sql, new RowMapper<Comment>() {
//            @Override
//            public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
//                Comment comment = new Comment();
//                comment.setCreated(resultSet.getDate("created"));
//                comment.setId(resultSet.getInt("id"));
//                comment.setMoviecontent(resultSet.getString("moviecontent"));
//                comment.setUsername(resultSet.getString("username"));
//                comment.setMovieid(resultSet.getString("movieid"));
//                return comment;
//            }
//        }, );
    }

    /*
    * 插入电影评论
    * */
    public int insert(Map<String, Object> params) {
        Date created = new Date();
        String movieid = (String) params.get("movieid");
        String moviecontent = (String) params.get("moviecontent");
        String username = (String) params.get("username");
        String moviename = (String) params.get("moviename");
        String sql = "insert into movie_comment(movieid,moviecontent,username,created,moviename) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[]{movieid, moviecontent, username, created, moviename});
    }

    /*
    * 获取某个用户的所有评论
    * */
    public List<Comment> getAllComments(String username) {
        String sql = "select * from movie_comment where username=? order by created DESC";
        return jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<Comment>(Comment.class));
    }

    /*
    * 删除指定的评论
    * */
    public int delete(Integer id) {
        String sql = "delete from movie_comment where id=?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }
}
