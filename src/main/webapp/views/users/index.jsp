<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../assets/stylesheets/panel.css">

<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<header class="container">

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>

			<h1>Usuarios</h1>
			</header>
	<main class="container">
		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/turismo/users/create.do" class="btn btn-danger"
					role="button"> <i class="bi bi-plus-lg"></i> Nuevo usuario
				</a>
			</div>
		</c:if>
		<table class="class= container table table-dark table-striped">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Dinero</th>
					<th>Tiempo</th>
					<th>Preferido</th>
					<th>Es administrador</th>
					<th>Opciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><strong><c:out value="${user.username}"></c:out></strong>
						<td><c:out value="${user.coins}"></c:out></td>
						<td><c:out value="${user.time}"></c:out></td>
						<td><c:out value="${user.type}"></c:out></td>
						<td><c:out value="${user.isAdmin()}"></c:out></td>
						
								<td>
								<a href="/turismo/users/edit.do?id=${user.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/turismo/users/delete.do?id=${user.id}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>

	</main>
<footer class="container">By @ExpectedError </footer>
</body>
</html>