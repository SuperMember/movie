package com.example.wcmovie.service;

import com.example.wcmovie.config.MovieProperties;
import com.example.wcmovie.dao.MovieDao;
import com.example.wcmovie.entity.Comment;
import com.example.wcmovie.exception.MovieException;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MovieProperties properties;
    @Autowired
    Gson gson;
    @Autowired
    MovieDao movieDao;

    /*
    * 正在上映
    * */
    public Map<String, Object> showing(Integer start, Integer count) {
        String url = properties.getBaseUrl() + properties.getShowing() + "?start=" + (start - 1) * count + "&count=" + count;
        return request(url);
    }

    /*
    * 即将上映
    * */
    public Map<String, Object> coming(Integer start, Integer count) {
        String url = properties.getBaseUrl() + properties.getPreshow() + "?start=" + (start - 1) * count + "&count=" + count;
        return request(url);
    }

    /*
    * top250
    * */
    public Map<String, Object> top(Integer start, Integer count) {
        String url = properties.getBaseUrl() + properties.getTop250() + "?start=" + (start - 1) * count + "&count=" + count;
        return request(url);
    }

    /*
    * 口碑榜
    * */
    public Map<String, Object> weekly(Integer start, Integer count) {
        String url = properties.getBaseUrl() + properties.getWeekly() + "?start=" + (start - 1) * count + "&count=" + count;
        return request(url);
    }

    /*
    * 北美票房榜
    * */
    public Map<String, Object> usmovie(Integer start, Integer count) {
        String url = properties.getBaseUrl() + properties.getUsmovie() + "?start=" + (start - 1) * count + "&count=" + count;
        return request(url);
    }

    /*
    * 新片榜
    * */
    public Map<String, Object> newmovie(Integer start, Integer count) {
        String url = properties.getBaseUrl() + properties.getNewmovie() + "?start=" + (start - 1) * count + "&count=" + count;
        return request(url);
    }

    /*
    * 搜索电影
    * */
    public Map<String, Object> search(String condition) {
        String url = properties.getBaseUrl() + properties.getSearch() + "?tag=" + condition;
        return request(url);
    }

    /*
    * 获取某个电影的详情
    * */
    public Map<String, Object> getOne(String id, String username) {
        String url = properties.getBaseUrl() + properties.getMovie() + id;
        Map<String, Object> result = request(url);
        result.put("comments", movieDao.getMovieComments(username, id));
        return result;
    }

    /*
    * 获取首页数据
    * */
    public Map<String, Object> getIndex() {
        //轮播
        try {
            Map<String, Object> swiper = showing(1, 5);
            //即将上映
            Map<String, Object> coming = coming(1, 6);
            //top250
            Map<String, Object> top = top(1, 6);
            //口碑榜
            // Map<String, Object> weekly = weekly(1, 6);
            Map<String, Object> index = new HashMap<>();
            index.put("code", "200");
            index.put("message", "获取成功");
            index.put("swiper", swiper);
            index.put("coming", coming);
            index.put("top", top);
            //index.put("weekly", weekly);
            return index;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MovieException(0, "出错了");
        }
    }

    /*
    * 评论
    * */
    @Transactional
    public int submit(Map<String, Object> params) throws Exception {
        return movieDao.insert(params);
    }

    public Map<String, Object> request(String url) {
        String result = restTemplate.getForObject(url, String.class, new String[]{});
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> resultMap = gson.fromJson(result, type);
        return resultMap;
    }

    /*
    * 获取某个用户的所有评论
    * */
    public List<Comment> getAllComments(String username) {
        return movieDao.getAllComments(username);
    }

    /*
    * 获取某个用户对于某部电影的所有评论
    * */
    public List<Comment> getCommentById(String id, String username) {
        return movieDao.getMovieComments(username, id);
    }

    /*
    * 删除评论
    * */
    public int delete(Integer id) {
        return movieDao.delete(id);
    }
}
