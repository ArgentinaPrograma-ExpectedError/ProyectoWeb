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


	<header>

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
	</header>

	<h3 style="text-align: left; color: #ffffff; margin-left: 100px;">
		<i class="bi bi-map"></i> Itinerario
	</h3>
	<br>
	<div class="container">
		<table class="table table-danger table table-bordered border-dark">
			<tr>
				<th colspan="4">Nombre: <c:out value="${user.getUsername()}"></c:out></th>

			</tr>

			<tr>
				<th>Tiempo</th>
				<th>Atracción</th>
				<th>Descripción</th>
				<th>Tipo</th>

			</tr>
			<c:forEach items="${attractions}" var="atraction">

				<tr>
					<td><c:out value="${atraction.getDuration()}"></c:out></td>
					<td><c:out value="${atraction.getName()}"></c:out></td>
					<td><c:out value="${atraction.getDescription()}"></c:out></td>
					<td><c:out value="${atraction.getAttractionType()}"></c:out></td>
				</tr>
			</c:forEach>

			<tr>
				<th colspan="1">Tiempo Total: <c:out
						value="${user.getTiempo_gastado()}">
					</c:out> <i class="bi bi-clock-history"></i>
				</th>
				<th colspan="3">Costo Total: <c:out
						value="${user.getDinero_gastado()}">
					</c:out> <i class="bi bi-cash-coin"></i>
				</th>
			</tr>
			<tr>

				<td colspan="4">Se recomienda concurrir 5 minutos antes a cada
					actividad</td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<br>
	<footer class="container">By @ExpectedError </footer>
</body>
</html>