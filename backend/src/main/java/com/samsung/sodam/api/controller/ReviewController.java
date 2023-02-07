package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.service.ReviewService;
import com.samsung.sodam.db.entity.Review;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService reviewService) {
        this.service = reviewService;
    }

    @PostMapping("review/search")
    public List<Review> getReviewAvg(){
        return service.getOrderByAvg();
    }

    @GetMapping("review/search")
    @ApiOperation(value = "고객 id로 리뷰 검색")
    public List<Review> getReviewByClientId(String clientId){
        return service.getMyReview(clientId);
    }
}
