<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 "
		style="font-size: 20px; padding:0; ">
		<div class="container" style="background-color: #000000;">
	
	<div class="collapse navbar-collapse" id="navbarCollapse">
	
			
				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/turismo/index.jsp"><i
				class="bi bi-house"></i> </a></li>
				</ul>
	
		
				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item"><c:out value="${user.username}"></c:out></li>
				</ul>

				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/turismo/attractions/index.do">Atracciones</a></li>
				</ul>

				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/turismo/promotions/index.do">Promociones</a></li>
				</ul>

				<c:if test="${user.isAdmin()}">
					<ul class="navbar-nav me-auto mb-2 mb-md-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/turismo/users/index.do">Usuarios</a></li>
					</ul>
				</c:if>

				<c:if test="${!user.isAdmin()}">
					<ul class="navbar-nav me-auto mb-2 mb-md-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/turismo/itinerario/index.do"> <i class="bi bi-journal-text"></i>
								Mi Itinerario
						</a></li>
					</ul>

					<ul class="navbar-nav me-auto mb-2 mb-md-0">
						<li class="nav-item"> <i title="monedas"
								style="color: gold;" class="bi bi-coin"> </i> <c:out
									value="${user.coins}"></c:out>
						</li>
					</ul>

					<ul class="navbar-nav me-auto mb-2 mb-md-0">
						<li class="nav-item"><i title="tiempo"
								style="color: white;" class="bi bi-clock-fill"></i> <c:out
									value="${user.time}h"></c:out> </li>
					</ul>

				</c:if>

				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item"><a href="/turismo/logout"
						style="color: white; text-decoration: none">Salir <i
							class="bi bi-arrow-right-circle" style="color: white;"></i></a></li>
				</ul>

			</div>
		</div>
	</nav>
</header>