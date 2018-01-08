package com.example.wcmovie.controller;

import com.example.wcmovie.entity.Comment;
import com.example.wcmovie.entity.ResultEntity;
import com.example.wcmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /*
    * 正在上映
    * */
    @RequestMapping("/showing")
    public Map<String, Object> showing(Integer start, @RequestParam(defaultValue = "20") Integer count) {
        return movieService.showing(start, count);
    }

    /*
    * 即将上映
    * */
    @RequestMapping("/coming")
    public Map<String, Object> comingsoon(Integer start, @RequestParam(defaultValue = "20") Integer count) {
        return movieService.coming(start, count);
    }

    /*
    * top250
    * */
    @RequestMapping("/top250")
    public Map<String, Object> top250(Integer start, @RequestParam(defaultValue = "20") Integer count) {
        return movieService.top(start, count);
    }

    /*
    * 口碑榜
    * */
    @RequestMapping("/weekly")
    public Map<String, Object> weekly(Integer start, @RequestParam(defaultValue = "20") Integer count) {
        return movieService.weekly(start, count);
    }

    /*
    * 北美票房榜
    * */
    @RequestMapping("/usmovie")
    public Map<String, Object> usmovie(Integer start, @RequestParam(defaultValue = "20") Integer count) {
        return movieService.usmovie(start, count);
    }

    /*
    * 新片榜
    * */
    @RequestMapping("/newmovie")
    public Map<String, Object> newmovie(Integer start, @RequestParam(defaultValue = "20") Integer count) {
        return movieService.newmovie(start, count);
    }

    /*
    * 根据条件获取电影
    * */
    @RequestMapping("/search")
    public Map<String, Object> search(String tag) {
        return movieService.search(tag);
    }

    /*
    * 根据id获取某个电影的详情
    * */
    @RequestMapping("/one/{id}")
    public Map<String, Object> getOne(@PathVariable String id, String username) {
        return movieService.getOne(id, username);
    }

    /*
    * 首页数据
    * */
    @RequestMapping("/index")
    public Map<String, Object> getIndex() {
        return movieService.getIndex();
    }

    /*
    * 提交评论
    * */
    @PostMapping("/submit")
    public ResponseEntity<?> submit(@RequestBody Map<String, Object> params) {
        try {
            movieService.submit(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultEntity resultEntity = new ResultEntity("200", "success", null);
        return new ResponseEntity<Object>(resultEntity, HttpStatus.OK);
    }

    /*
    * 获取某个用户的所有评论
    * */
    @GetMapping("/comment")
    public ResponseEntity<?> getAllComments(String username) {
        List<Comment> comments = movieService.getAllComments(username);
        ResultEntity resultEntity = new ResultEntity("200", "success", comments);
        return new ResponseEntity<Object>(resultEntity, HttpStatus.OK);
    }

    /*
    * 删除评论
    * */
    @DeleteMapping("/comment")
    public ResponseEntity<?> delete(Integer id) {
        try {
            movieService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultEntity resultEntity = new ResultEntity("200", "success", null);
        return new ResponseEntity<Object>(resultEntity, HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public List<Comment> getCommentById(@PathVariable String id, String username) {
        return movieService.getCommentById(id, username);
    }

}
