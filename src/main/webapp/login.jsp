<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=MedievalSharp&display=swap"
	rel="stylesheet" />
<link href="assets/stylesheets/login.css" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Amatic+SC&display=swap" rel="stylesheet">
</head>
<body>
<main>
<video src="assets/video/fondo.mp4" loop="true" autoplay="autoplay" controls muted ></video>
		
		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p class="alerta">
					<c:out value="${flash}" />
				</p>
			</div>
		</c:if>


  <div class="body"></div>
  <div class="grad"></div>
  <div class="header">
    <div class="titulo">Tierra <span>Media</span></div>
  </div>
  <br>
<div class="login" >
	<form action="login"  method="post">

  
  
    <input type="text" placeholder="username" name="userAdmin" ><br>
    <input type="password" placeholder="password" name="passwordAdmin"><br>
    <button type="submit" class="btn btn-lg btn-primary">Ingresar</button>
				
  		</form>
		<br> <br>
		<p id=marca>WebApp by @ExpectedError</p>
	
	</div>
	</main>
</body>
</html>