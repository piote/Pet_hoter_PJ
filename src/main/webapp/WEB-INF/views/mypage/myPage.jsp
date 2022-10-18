<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
   <link rel="stylesheet" href="${contextPath}/resources/css/mypage.css">
   <meta charset="utf-8"  />
   <title>My Page</title>
</head>
<body>
   <div id="wrap">
      <div id="container">
         <h2>My Page</h2>
         <div class="mypage_info">
            <table>
               <tr>
                  <td>
                     <img class="img_info" src="${contextPath}/resources/img/user.png" alt="회원이미지">
                  </td>
                  <td></td>
                  <td>
                     <img class="img_membership" src="${contextPath}/resources/img/crown.png" alt="My 혜택">
                     <p class="membership">${user.grade}</p>
                     <h4>${user.name }님</h4>
                     <br>
                     <p>전화: ${user.tel}</p>
                     <br>
                     <p>이메일: ${user.email} </p>
                  </td>
                  <td></td>
                  <td>
                     <a href="${contextPath}/pw_change.do">내정보수정</a>
                     <a href="${contextPath}/membership.do">멤버쉽</a>
                  </td>
                  <td>
                     <a href="${contextPath}/mypage/checkReserve.do">예약 내역</a>
                     <a href="${contextPath}/questionsList.do">고객센터</a>
                  </td>
               </tr>
            </table>
         </div>
         <div id="reserve">
            <h3>최근 예약 내역</h3>
            <a href="${contextPath}/mypage/checkReserve.do"><img src="${contextPath}/resources/img/plus.png" alt="더보기">more</a>
         </div>  
         <div class="mypage_reserve">
            <table>
				<tr class="reserve-center">
				    <td width="10%">Date</td>
				    <td width="20%">Name</td>
				    <td width="20%">PetName</td>
				    <td width="40%">Phone Number</td>
				</tr>
	            <c:choose>
	            	<c:when test="${empty myReserveList }">
		               <tr class="reserve-list">
		                  <td colspan=4>
		                  	<strong>예약하신 내역이 없습니다.</strong>
		                  </td>
		               </tr>
					</c:when>
					<c:when test="${not empty myReserveList }">
						<c:forEach var="reserves" items="${myReserveList }" begin="0" end="2" step="1" varStatus="reservation_st">
							<tr class="reserve-list">
								<td>${reserves.res_st }</td>
								<td>${reserves.user_name }</td>
								<td>${reserves.pet_name }</td>
								<td>${reserves.user_tel }</td>
							</tr>
						</c:forEach>
					</c:when>
               </c:choose>
            </table>
         </div>
         <div id="review">  
            <h3>최근 나의 후기</h3>
            <a href="#"><img src="${contextPath}/resources/img/plus.png" alt="더보기">more</a>
         </div>
         <div class="mypage_review">
            <table>
               <tr class="review-center">
                  <td width="8%">NO</td>
                  <td width="10%">Date</td>
                  <td width="32%">Subject</td>
                  <td width="50%">Contents</td>
              </tr>
	            <c:choose>
	            	<c:when test="${empty myReviewList }">
		               <tr class="review-list">
		                  <td colspan=4>
		                  	<strong>등록하신 리뷰가 없습니다.</strong>
		                  </td>
		               </tr>
					</c:when>
					<c:when test="${not empty myReviewList }">
						<c:forEach var="reviews" items="${myReviewList}" begin="0" end="2" step="1" varStatus="q_num">
							<tr class="review-list">
								<td>${reviews.num }</td>
								<td>${reviews.date }</td>
								<td>${reviews.title }</td>
								<td>${reviews.con }</td>
							</tr>
						</c:forEach>
					</c:when>
               </c:choose>
            </table>
         </div>
      </div>
   </div>
</body>
</html>

