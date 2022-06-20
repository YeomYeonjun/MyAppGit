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
                    <span class="subheading">게시글보기</span>
                </div>
            </div>
        </div>
    </div>
</header>
<!--container-->
<div class="container">
	<table class="table ">
		<thead>
			<tr>
				<th class="col-1">${record.boardno}</th>
				<th class="text-center">${record.title}</th>
				<th class="col-2">${record.name }</th>
				<th class="col-1">${record.hitCount }</th>
				<th class="col-2">${record.postDate}</th>
			</tr>
		</thead>
	</table>
				<div class="text-center m-5">${record.content }</div>
	<div class="text-center">
		<c:if test="${sessionScope.id==record.id}">
			<a href="<c:url value="/kosmo/Edit.do?boardno=${record.boardno}&nowPage=${param.nowPage}"/>" class="btn btn-outline-success btn-md">수정</a> 
			<a href="<c:url value="/kosmo/Delete.do?boardno=${record.boardno}"/>" class="btn btn-outline-success btn-md">삭제</a> 
		</c:if>
		<a href="<c:url value="/kosmo/List.do?nowPage=${param.nowPage}"/>" class="btn btn-outline-success btn-md">목록</a>
	</div>
<jsp:include page="/Template/Footer.jsp" />
