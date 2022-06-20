<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>염연준 개인프로젝트</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CDN -->
    <head>

        <link rel="icon" type="image/x-icon" href="<c:url value="/assets/favicon.ico"/>" />
        <!-- Font Awesome icons (free version)-->
        <script src="<c:url value="https://use.fontawesome.com/releases/v6.1.0/js/all.js"/>" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="<c:url value="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"/>" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="<c:url value="/css/styles.css"/>" rel="stylesheet" />
        <!-- Bootstrap core JS-->
        <script src="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"/>"></script>
        <!-- Core theme JS-->
        <!--  script src="<c:url value="js/scripts.js"/>"></script-->
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="<c:url value="/Home.jsp"/>">KOSMO</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    메뉴
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto py-4 py-lg-0">
			   		<c:if test="${empty sessionScope.id}" var="isLogin">
					    <li class="nav-item" ><a class="nav-link px-lg-3 py-3 py-lg-4"  href="<c:url value="/kosmo/Login.do"/>">로그인</a></li>
					    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="<c:url value="/kosmo/Join.do"/>">회원가입</a></li>
						<li class="nav-item" ><a class="nav-link px-lg-3 py-3 py-lg-4 "  href="<c:url value="/kosmo/List.do"/>">게시판</a></li>
					</c:if>	
					<c:if test="${not isLogin }">    
						<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" >${sessionScope.id }</a></li>
					    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="<c:url value="/kosmo/Logout.do"/>">로그아웃</a></li>
					    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="<c:url value="/kosmo/MemberDataEdit.do"/>">회원정보 수정</a></li>
					    <li class="nav-item" ><a class="nav-link px-lg-3 py-3 py-lg-4 "  href="<c:url value="/kosmo/List.do"/>">게시판</a></li>
			      	</c:if>
                    </ul>
                </div>
            </div>
        </nav>
        
