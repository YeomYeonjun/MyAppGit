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
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<style>
    .error{
        font-size: 0.8em;
        font-weight:bold;
        color: red;
    }
</style>
<script>
    $(function(){
        /*
        ※$(폼 선택자).validate({rules:{},messages:{}});
        rules규칙:
            -필수 입력 혹은 필수 선택(체크박스/라디오/선택상자)
            하위요소명 : "required"
            혹은
            2개 이상  규칙 적용시
            required:true
            
            -최소 몇자 이상 혹은 몇개 이상 선택
            minlength:최소수		
        */
        $('#frm').validate({
            rules:{//유효성 규칙 설정
                name:"required",
                id:{required:true,minlength:3,maxlength:5},
                password:{required:true,minlength:5},
                password2:{required:true,minlength:5,equalTo:'#password'},
                email:{required:true,email:true},
                inter:{required:true,minlength:2},
                gender:"required",
                grade:"required",
                file:"required",
                self:"required"
            },
            messages:{//유효성 규칙에 벗어날때 표시할 에러 메시지 설정
                name:"<br/>이름을 입력하세요",
                id:{required:'<br/>아이디를 입력하세요',minlength:'<br/>아이디는 3자이상..',maxlength:'<br/>아이디는 5자이하..'},
                password:{required:'<br/>비번을 입력하세요',minlength:"<br/>비번은 5자이상..."},
                password2:{required:'<br/>비번 확인을 입력하세요',minlength:"<br/>비번확인은 5자이상...",equalTo:'<br/>비밀번호가 일치해야 한다'},
                email:{required:"<br/>이메일을 입력하세요",email:"<br/>이메일 형식이 아닙니다."},
                inter:{required:"<br/>관심사항을 선택하세요",minlength:"<br/>2개이상 선택하세요"},
                gender:"<br/>성별을 선택하세요",
                grade:"",
                file:"",
                self:"<br/>자기소개를 입력하세요"
            }

        });

        $(':button').click(function(){
            if(S('#frm').valid()){
                $('#frm').submit();
            }
        });
    });
</script>
<header class="masthead" style="background-image: url('../assets/img/home-bg.jpg')">
            <div class="container position-relative px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <div class="site-heading">
                            <fieldset>
				            	<legend class="text-center"> 회원가입 </legend>
				            		<form action="" method="post" id="frm">
				            			<div class="form-group text-left">
											<label>아이디</label> 
											<input type="text" id="id" 	class="form-control" name="id" placeholder="아이디를 입력하세요">
										</div>	
										<div class="form-group text-left">
											<label>비밀번호</label> 
											<input type="password" id="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요">
										</div>
										<div class="form-group text-left">
											<label>비밀번호 확인</label> 
											<input type="password" id="password2" class="form-control" name="password2" placeholder="비밀번호를 확인하세요">
										</div>
										<div class="form-group text-left">
											<label>이름</label> 
											<input type="text" id="name" 	class="form-control" name="name" placeholder="이름를 입력하세요">
										</div>	
										
										<div class="form-group text-left">
											<label>성별</label><label for="gender" class="error"></label>
											<div class="d-flex">
												<div class="custom-control custom-radio mr-2">
													<input type="radio" class="custom-control-input" name="gender" value="남자" id="male1"> <label for="male1" class="custom-control-label">남자</label>
												</div>
												<div class="custom-control custom-radio">
													<input type="radio" class="custom-control-input" name="gender" value="여자" id="female1"> <label for="female1" class="custom-control-label">여자</label>
												</div>
												
											</div>
										</div>
										<div class="form-group text-left">
											<label>관심사항</label><label for="inter" class="error"></label>
											<div class="d-flex">
												<div class="custom-control custom-checkbox">
													<input type="checkbox" class="custom-control-input" name="inter" value="정치" id="POL"> <label class="custom-control-label" for="POL">정치</label>
												</div>
												<div class="custom-control custom-checkbox mx-2">
													<input type="checkbox" class="custom-control-input" name="inter" value="경제" id="ECO"> <label class="custom-control-label" for="ECO">경제</label>
												</div>
												<div class="custom-control custom-checkbox">
													<input type="checkbox" class="custom-control-input" name="inter" value="연예" id="ENT"> <label class="custom-control-label" for="ENT">연예</label>
												</div>
												<div class="custom-control custom-checkbox ml-2">
													<input type="checkbox" class="custom-control-input" name="inter" value="스포츠" id="SPO"> <label class="custom-control-label" for="SPO">스포츠</label>
												</div>
											</div>
										</div>
								
										<div class="form-group text-left">
											<label>학력사항</label> 
											<select name="grade" class="custom-select mt-3 custom-select-lg">
												<option value="" >학력을 선택하세요</option>
												<option value="초등학교" >초등학교</option>
												<option value="중학교">중학교</option>
												<option value="고등학교">고등학교</option>
												<option value="대학교">대학교</option>
											</select>
										</div>
										<div class="form-group text-left">
											<label>자기소개</label> 
											<textarea class="form-control" rows="5" name="self"></textarea>
										</div>
										<div class="row justify-content-center m-1">
				 							<div class="d-flex justify-content-center mb-4"><input type="submit" class="btn btn-dark mx-2" value="회원가입" /></div>
										</div>
									</form>
				            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </header>


<jsp:include page="/Template/Footer.jsp"></jsp:include>
