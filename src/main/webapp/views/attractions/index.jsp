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

		<h1>Atracciones</h1>

	</header>

	<!-- <-- atracciones  -->
	<main class="container">

		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/turismo/attraction/create.do" class="btn btn-danger"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Atracción
				</a>
			</div>
		</c:if>

		<table class="container table table-dark table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Atracción</th>
					<th>Descripción</th>
					<th>Costo</th>
					<th>Tiempo</th>
					<th>Cupo Diario</th>
					<th>Tipo de Atracción</th>
					<th>Acciones</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${attractions}" var="atraction">
					<c:if test="${user.isAdmin()}">
						<tr>
							<td><c:out value="${atraction.getId()}"></c:out></td>
							<td><c:out value="${atraction.getName()}"></c:out></td>
							<td><c:out value="${atraction.description}"></c:out></td>
							<td><c:out value="${atraction.getCost()}"></c:out></td>
							<td><c:out value="${atraction.getDuration()}"></c:out></td>
							<td><c:out value="${atraction.getCapacity()}"></c:out></td>
							<td><c:out value="${atraction.getAttractionType()}"></c:out></td>

							<td><a href="/turismo/atractions/edit.do?id=${atraction.id}"
								class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-square"></i></a> <a
								href="/turismo/atractions/delete.do?id=${atraction.id}"
								class="btn btn-danger" role="button"><i class="bi bi-trash"></i></a>
								<c:choose>
									<c:when test="${atraction.enable}">
										<a href="/turismo/atractions/enable.do?id=${atraction.id}"
											class="btn btn-outline-secondary" role="button">Deshabilitar</a>
									</c:when>
									<c:otherwise>
										<a href="/turismo/atractions/enable.do?id=${atraction.id}"
											class="btn btn-secondary" role="button">Habilitar</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:if>
					<c:if
						test="${!user.isAdmin() && atraction.enable && user.canAfford(atraction) && user.canAttend(atraction) && atraction.canHost(1) && user.canBuy(atraction.getId())}">
						<tr>
						<td><c:out value="${atraction.getId()}"></c:out></td>
							<td><c:out value="${atraction.name}"></c:out>
								</td>
							<td><c:out value="${atraction.description}"></c:out></td>
							<td><c:out value="${atraction.getCost()}"></c:out></td>
							<td><c:out value="${atraction.getDuration()}"></c:out></td>
	                        <td><c:out value="${atraction.getCapacity()}"></c:out></td>
							<td><c:out value="${atraction.getAttractionType()}"></c:out></td>
							<td><a href="/turismo/attractions/buy.do?id=${atraction.id}"
								class="btn btn-success rounded" role="button">Comprar</a></td>
						</tr>
					</c:if>
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