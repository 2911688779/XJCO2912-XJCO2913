package com.pingan.cn.lish.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.entity.Comments;
import com.pingan.cn.lish.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "movie comments")
@RestController
@RequestMapping(value = "/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("save/{movieName}")
    public ResponseUtil save(@PathVariable String movieName,@RequestBody Comments comments){
        return commentService.save(movieName,comments);
    }

    @GetMapping("findAll/{key}")
    public ResponseUtil getAll(@PathVariable String key){
        return commentService.findAllByMovieName(key);
    }

    /*@GetMapping("find/{idx}")
    public ResponseUtil findByIndex(@PathVariable Integer idx){
        return commentService.findByIndex(idx);
    }

    @GetMapping("delete/{idx}")
    public ResponseUtil delete(@PathVariable Integer idx){
        return commentService.delete(idx);
    }

    @PostMapping("update/{idx}")
    public ResponseUtil update(@PathVariable Integer idx,@RequestBody Movie movie){
        return movieService.update(idx,movie);
    }*/

}
