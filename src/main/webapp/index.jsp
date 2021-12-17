<%@page import="model.Suggestion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
<link rel="stylesheet" href="assets/stylesheets/index.css">
</head>
<body class="container">

<c:if test="${!user.isAdmin()}">
	<jsp:include page="partials/nav.jsp"></jsp:include>

	<header class="container">
		<h1 id=brand>PARQUE TIERRA MEDIA</h1>
	</header>


	<div class="p-4 rounded container">
		<br>
		<p style="font-size: 20px;">
		
		Los personajes medievales, tanto clérigos como seglares, se valieron de la literatura como medio para alcanzar un fin más trascendente: mostrar al hombre cómo había de orientar su vida de acuerdo con la edad media para obtener la supervivencia.
		<br>
		El Parque Tierra Media tiene mil Aventuras para ofrecerte, cientos de Paisajes para recorrer y muchos
			sabores para descubrir en Degustaciones</p>
		<br>
		<h2 style="text-align: center;"> Elije cómo revivir la Edad Media <i class="bi bi-bug-fill"></i></h2>
	</div>
	<br>
	<br>
	<br>

	<main class="container" style="flex: center">

		<ul class="lista-articulos">

			<c:forEach items="${suggestions}" var="suggestion">

				<li>
					<article>
						<img src="${suggestion.getUrl()}">
						<h3>
							<c:out value="${suggestion.getName()}"></c:out>
						</h3>
						<p>
							<c:out value="${suggestion.description}"></c:out>
						</p>


					</article>
				</li>
			</c:forEach>
		</ul>
	</main>

	<footer>
		<div class="container-all ">

			<div class="container-body">
				<!-- ----------- datos----------------  -->
				<div class="colum1">
					<h5>
						Parque Temático <br> Tierra Media
					</h5>
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
						fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16">
                  <path
							d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A31.493 31.493 0 0 1 8 14.58a31.481 31.481 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z" />
                  <path
							d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
                </svg>
					<br> <br>
					<p>Dirección: Avenida Siempre Viva 1234</p>

				</div>
			</div>
			<!-- ----------- iconos----------------  -->

			<div>
				<svg xmlns="http://www.w3.org/2000/svg" width="36" height="36"
					fill="currentColor" class="bi bi-facebook" viewBox="0 0 16 16">
             <path
						d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z" />
             </svg>

				<svg xmlns="http://www.w3.org/2000/svg" width="36" height="36"
					fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
              <path
						d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z" />
              </svg>

				<svg xmlns="http://www.w3.org/2000/svg" width="36" height="36"
					fill="currentColor" class="bi bi-twitter" viewBox="0 0 16 16">
              <path
						d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z" />
              </svg>


				<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"
					fill="currentColor" class="bi bi-twitch" viewBox="0 0 16 16">
              <path
						d="M3.857 0 1 2.857v10.286h3.429V16l2.857-2.857H9.57L14.714 8V0H3.857zm9.714 7.429-2.285 2.285H9l-2 2v-2H4.429V1.143h9.142v6.286z" />
              <path
						d="M11.857 3.143h-1.143V6.57h1.143V3.143zm-3.143 0H7.571V6.57h1.143V3.143z" />
            </svg>

				<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"
					fill="currentColor" class="bi bi-pinterest" viewBox="0 0 16 16">
               <path
						d="M8 0a8 8 0 0 0-2.915 15.452c-.07-.633-.134-1.606.027-2.297.146-.625.938-3.977.938-3.977s-.239-.479-.239-1.187c0-1.113.645-1.943 1.448-1.943.682 0 1.012.512 1.012 1.127 0 .686-.437 1.712-.663 2.663-.188.796.4 1.446 1.185 1.446 1.422 0 2.515-1.5 2.515-3.664 0-1.915-1.377-3.254-3.342-3.254-2.276 0-3.612 1.707-3.612 3.471 0 .688.265 1.425.595 1.826a.24.24 0 0 1 .056.23c-.061.252-.196.796-.222.907-.035.146-.116.177-.268.107-1-.465-1.624-1.926-1.624-3.1 0-2.523 1.834-4.84 5.286-4.84 2.775 0 4.932 1.977 4.932 4.62 0 2.757-1.739 4.976-4.151 4.976-.811 0-1.573-.421-1.834-.919l-.498 1.902c-.181.695-.669 1.566-.995 2.097A8 8 0 1 0 8 0z" />
               </svg>

				<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"
					fill="currentColor" class="bi bi-discord" viewBox="0 0 16 16">
               <path
						d="M13.545 2.907a13.227 13.227 0 0 0-3.257-1.011.05.05 0 0 0-.052.025c-.141.25-.297.577-.406.833a12.19 12.19 0 0 0-3.658 0 8.258 8.258 0 0 0-.412-.833.051.051 0 0 0-.052-.025c-1.125.194-2.22.534-3.257 1.011a.041.041 0 0 0-.021.018C.356 6.024-.213 9.047.066 12.032c.001.014.01.028.021.037a13.276 13.276 0 0 0 3.995 2.02.05.05 0 0 0 .056-.019c.308-.42.582-.863.818-1.329a.05.05 0 0 0-.01-.059.051.051 0 0 0-.018-.011 8.875 8.875 0 0 1-1.248-.595.05.05 0 0 1-.02-.066.051.051 0 0 1 .015-.019c.084-.063.168-.129.248-.195a.05.05 0 0 1 .051-.007c2.619 1.196 5.454 1.196 8.041 0a.052.052 0 0 1 .053.007c.08.066.164.132.248.195a.051.051 0 0 1-.004.085 8.254 8.254 0 0 1-1.249.594.05.05 0 0 0-.03.03.052.052 0 0 0 .003.041c.24.465.515.909.817 1.329a.05.05 0 0 0 .056.019 13.235 13.235 0 0 0 4.001-2.02.049.049 0 0 0 .021-.037c.334-3.451-.559-6.449-2.366-9.106a.034.034 0 0 0-.02-.019Zm-8.198 7.307c-.789 0-1.438-.724-1.438-1.612 0-.889.637-1.613 1.438-1.613.807 0 1.45.73 1.438 1.613 0 .888-.637 1.612-1.438 1.612Zm5.316 0c-.788 0-1.438-.724-1.438-1.612 0-.889.637-1.613 1.438-1.613.807 0 1.451.73 1.438 1.613 0 .888-.631 1.612-1.438 1.612Z" />
               </svg>

			</div>

			<div>
				<h5>Información Contacto:</h5>
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
					fill="currentColor" class="bi bi-phone" viewBox="0 0 16 16">
                <path
						d="M11 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h6zM5 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H5z" />
                <path d="M8 14a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
              </svg>
				<a href="+54 333332222" style="color: aliceblue;"> teléfono: +54
					333332222 </a> <br />

				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
					fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
                  <path
						d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z" />
                </svg>
				<a href="Contacto@tierraMedia.com " style="color: aliceblue;">Contacto@tierraMedia.com
				</a>


			</div>

		</div>
		<!-- ----------- integrantes del grupo----------------  -->
		<div class="container">

			<div class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown"
					aria-expanded="false" style="color: white;"> By @ExpectedErrorr
				</a>
				<ul class="dropdown-menu container" style="text-align: center"
					aria-labelledby="navbarDropdownMenuLink">

					<li><a class="dropdown-item" style="color: blueviolet;"
						href="https://github.com/Myriampp">Myriam</a></li>
					<li><a class="dropdown-item" style="color: blue"
						href="https://github.com/Kmilion">Gonzalo</a></li>
					<li><a class="dropdown-item" style="color: chartreuse;"
						href="https://github.com/MatiasBinsak">Matias</a></li>
					<li><a class="dropdown-item" style="color: red;"
						href="https://github.com/DenisAndersen">Denis</a></li>

				</ul>
			</div>
		</div>


	</footer>
	
	</c:if>
	<c:if test="${user.isAdmin()}">

	<header class="container">
		<h1 id=brand style="margin-top: 40px; margin-bottom: 80px;">PARQUE TIERRA MEDIA</h1>
	</header>
	<jsp:include page="partials/nav.jsp"></jsp:include>


	</c:if>
	
</body>
</html>







