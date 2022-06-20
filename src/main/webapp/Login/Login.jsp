<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="/Template/Top.jsp"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<header class="masthead" style="background-image: url('../assets/img/home-bg.jpg')">
	<div class="container position-relative px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<div class="site-heading">
					<fieldset>
						<legend class="text-center"> <h1>Login</h1> </legend>
						<form class="form-group m-2 text-left " action="<c:url value="/kosmo/Login.do"/>" method="post">
							<label class="m-2">아이디</label> 
							<input type="text" name="id" class="form-control mx-2" /> 
							<label class="m-2">비밀번호</label> 
							<input type="password" name="password" class="form-control mx-2" /> 
							<div class="d-flex justify-content-center m-4 border-radius: 100px"><input type="submit" class="btn btn-dark mx-2" value="Login" /></div>
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
</header>


<jsp:include page="/Template/Footer.jsp"></jsp:include>