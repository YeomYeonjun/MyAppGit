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
                    <span class="subheading">게시글 쓰기</span>
                </div>
            </div>
        </div>
    </div>
</header>
<!--container-->
<div class="container">
	<form method="post" action="<c:url value="/kosmo/Write.do"/>">      
      <div class="form-group">
        <label>제목</label>
        <input type="text" class="form-control" placeholder="제목을 입력하세요" name="title">
      </div>
      <div class="form-group">
		<label>내용</label>
		<textarea class="form-control" rows="5" name="content"></textarea>
	  </div>
      <button type="submit" class="btn btn-primary m-4">등록</button>
    </form>	
</div>
<jsp:include page="/Template/Footer.jsp" />

