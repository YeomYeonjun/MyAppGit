<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/Template/Top.jsp"/>

<header class="masthead" style="background-image: url('../assets/img/home-bg.jpg')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="site-heading">
                    <h1>Yeom YJ</h1>
                    <span class="subheading">게시판</span>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="container">
	<table class="table table-hover text-center">
	<c:if test="${empty sessionScope.id}" var="isLogin">
	<h3 class="text-center">로그인후 이용해주세요</h3>
	<div type="button" class="d-flex justify-content-center mb-4"><a class="btn btn-outline-dark" href="<c:url value="/kosmo/Login.do"/>">Login</a></div>
	</c:if>
	<c:if test="${not isLogin }">
	<thead>
		<tr>
			<th class="col-1">번호</th>
			<th>제목</th>
			<th class="col-2">작성자</th>
			<th class="col-1">조회수</th>
			<th class="col-2">작성일</th>
		</tr>
	</thead>
	<tbody class="table-sm down-file-body">
		<c:if test="${empty records }" var="isEmpty">
			<tr>
				<td colspan="6">등록된 자료가 없습니다.</td>
			</tr>	
		</c:if>
		<c:if test="${not isEmpty }">
		<c:forEach var="record" items="${records}" varStatus="loop">
			<tr>
				<td>${pagingMap.totalRecordCount - (((pagingMap.nowPage - 1) * pagingMap.pageSize) + loop.index)}</td>		
				<td><a href="<c:url value="/kosmo/View.do?boardno=${record.boardno }&nowPage=${param.nowPage}"/>">${record.title }</a></td>	
				<td>${record.name }</td>	
				<td>${record.hitCount }</td>	
				<td>${record.postDate }</td>	
			</tr>
		</c:forEach>
		</c:if>
		</tbody>
	</table>
	<div>${pagingString}</div>
    <!-- Pager-->
    <div class="d-flex justify-content-end mb-4"><a class="btn btn-primary text-uppercase" href="<c:url value="/kosmo/Write.do"/>">글쓰기</a></div>
	</c:if>
<jsp:include page="/Template/Footer.jsp" />
