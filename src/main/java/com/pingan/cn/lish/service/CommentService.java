package com.pingan.cn.lish.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.entity.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentService {

    @Autowired
    private RedisTemplate redisTemplate;

    public ResponseUtil save(String movieName,Comments comments){
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            final Long result = listOperations.rightPushAll(movieName, comments);
//            Movie movie = findByMovieName(movieName);
//            movie.setCommentNum(movie.getCommentNum()+1);
//            listOperations.set("movie",idx, movie);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findAllByMovieName(String movieName){
        try {
            ListOperations listOperations = redisTemplate.opsForList();

            List comments = listOperations.range(movieName, 0,-1);
            return ResponseUtil.success(comments);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

//    private Map<Integer,Movie> findByMovieName(String movieName){
//        ListOperations listOperations = redisTemplate.opsForList();
//        List<Movie> movies = listOperations.range("movie", 0, -1);
//        Map<Integer,Movie> maps = new HashMap<Integer,Movie>();
//        movies.forEach(m ->{
//            if(m.getName().equals(movieName)){
//                maps.put(movies.indexOf(m),m);
//            }
//        });
////        List<Movie> movies1 = movies.stream().filter(m -> m.getName().equals(movieName)).collect(Collectors.<Movie>toList());
//        return maps;
//    }
}
