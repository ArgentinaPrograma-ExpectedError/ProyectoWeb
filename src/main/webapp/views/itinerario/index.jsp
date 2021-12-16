<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../assets/stylesheets/panel.css" />


<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>


	<header class="container">

		<h1>Itinerario</h1>

	</header>

	<!-- <-- atracciones  -->
	<main class="container">


		<table class="container table table-dark table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Atracción</th>
					<th>Descripción</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${attractions}" var="atraction">
					
						<tr>
							<td><c:out value="${atraction.getId()}"></c:out></td>
							<td><c:out value="${atraction.getName()}"></c:out></td>
							<td><c:out value="${atraction.description}"></c:out></td>
						</tr>
					
				</c:forEach>
			</tbody>
		</table>

	</main>
	<br>
	<br>
	<br>
	<footer class="container">By @ExpectedError </footer>
</body>
</html>