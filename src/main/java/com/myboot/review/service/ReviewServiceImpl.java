package com.myboot.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myboot.mypage.dao.MyPageDAO;
import com.myboot.review.dao.ReviewDAO;
import com.myboot.review.vo.ReviewVO;

@Service("reviewService")
@Transactional(propagation = Propagation.REQUIRED)
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDAO reviewDAO;
	MyPageDAO myPageDAO;
	
	/*
	 * @Override public List<ReviewVO> reviewDetail_1() throws Exception{
	 * List<ReviewVO> reviewList = reviewDAO.selectAllReviewList(); return
	 * reviewList; }
	 */
	
	@Override
	public List listMyReserve(String user_id) throws Exception{
		return myPageDAO.selectMyReservesList(user_id);
	}
	
	
	@Override
	public Map listMyDetailReserve(Map pagingMap) throws Exception {
		Map myReserveMap = new HashMap();
		List<ReviewVO> myReserveList = reviewDAO.selectAllMyReservesList(pagingMap);
		
		int totReview = reviewDAO.selectTotReview();

		myReserveMap.put("myReserveList", myReserveList);
		myReserveMap.put("totReview", totReview);
		// articlesMap.put("totArticles", 170);
		return myReserveMap;
	}
	
	@Override
	public Map reviewDetail_1(Map pagingMap) throws Exception {
		Map reviewMap = new HashMap();
		List<ReviewVO> reviewList = reviewDAO.selectAllReviewList(pagingMap);
		
		int totReview = reviewDAO.selectTotReview();

		reviewMap.put("reviewList", reviewList);
		reviewMap.put("totReview", totReview);
		// articlesMap.put("totArticles", 170);
		return reviewMap;
	}
	
	@Override
	public Map reviewDetail_2(Map pagingMap) throws Exception {
		Map reviewMap = new HashMap();
		List<ReviewVO> reviewList = reviewDAO.selectAllReviewList2(pagingMap);
		
		int totReview = reviewDAO.selectTotReview();

		reviewMap.put("reviewList", reviewList);
		reviewMap.put("totReview", totReview);
		// articlesMap.put("totArticles", 170);
		return reviewMap;
	}
	@Override
	public Map reviewDetail_3(Map pagingMap) throws Exception {
		Map reviewMap = new HashMap();
		List<ReviewVO> reviewList = reviewDAO.selectAllReviewList3(pagingMap);
		
		int totReview = reviewDAO.selectTotReview();

		reviewMap.put("reviewList", reviewList);
		reviewMap.put("totReview", totReview);
		// articlesMap.put("totArticles", 170);
		return reviewMap;
	}

	@Override
	public int addNewReview(Map reviewMap, String fileName) throws Exception {
		int reviewNO = reviewDAO.selectNewReviewNO();
		reviewMap.put("reviewNO", reviewNO);
		
		if (fileName != null) {
			String fileUrl = "resources/review/review_image" + "/" + reviewNO + "/" + fileName;
			reviewMap.put("review_image_url", fileUrl);
			reviewDAO.insertNewReview(reviewMap);
			reviewMap.put("reviewNO", reviewNO);
		} else {
			reviewDAO.insertNewReview(reviewMap);
		}

		return reviewNO;
	}

//다중 이미지 추가하기	


	@Override
	public void removeReview(int reviewNO) throws Exception {
		reviewDAO.deleteReview(reviewNO);
	}
	
	
	//	메인페이지 리뷰조회
	@Override
	public List returnReviewFormain() throws Exception {
		List reviewList = reviewDAO.selectMainReview();
		return reviewList;
	}
	
	
	
	
}
