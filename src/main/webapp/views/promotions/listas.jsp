<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html> 
<head>
<link rel="stylesheet" href="assets/stylesheets/panel.css">
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>


    <header class="container">

        <h1 id=brand>PARQUE TIERRA MEDIA</h1>
         <h2>Panel del Admistrador</h2>
         	
<div class="container"> 

  
         <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
              <a class="navbar-brand" href="#"></a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                  <li class="nav-item">
                    <a href="../index.html" class="nav-link active" aria-current="page" href="#">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Lista de atracciones</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Tipos de promoción</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Lista de promociones</a>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Lista de Usuarios
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <li><a class="dropdown-item" href="#">Lista de usuarios</a></li>
                      <li><a class="dropdown-item" href="#">Crear nuevo usuario</a></li>
                     </ul>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        
        </div>
    </header>

    <!-- <-- lista de promociones  -->
    <main class="container">
      <h3>Lista de Promociones</h3>
      <table  class="container" >
        <tr>
          <th>id</th>
          <th>tipo_promocion</th>
          <th>nombre</th>
          <th>tipo_atracciones</th>
          <th>descuento</th>
          <th>precio</th>
          <th colspan="2">Acciones</th>
        </tr>
        <tr>
          <td>1</td>
          <td>1</td>
          <td>Lomas Del Silencio</td>
          <td>3</td>
          <td></td>
          <td>40</td>
          <td><button>modificar</button></td>
          <td><button>Deshabilitar</button></td>
        </tr>
        <!-- hasta acá-->
        <tr>
          <td>?</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><button>modificar</button></td>
          <td><button>Deshabilitar</button></td>
        </tr>
        <tr>
          <td>?</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><button>modificar</button></td>
          <td><button>Deshabilitar</button></td>
        </tr>
        <tr>
          <td>?</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><button>modificar</button></td>
          <td><button>Deshabilitar</button></td>
        </tr>
        <tr>
          <td>?</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><button>modificar</button></td>
          <td><button>Deshabilitar</button></td>
        </tr>
        <tr>
          <td>?</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><button>modificar</button></td>
          <td><button>Deshabilitar</button></td>
        </tr>
        <tr>
          <td>?</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><button>modificar</button></td>
          <td><button>Deshabilitar</button></td>
        </tr>
        <tr>
          <td>?</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><button>modificar</button></td>
          <td><button>Deshabilitar</button></td>
        </tr>
        <tr>
          <td>?</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><button>modificar</button></td>
          <td><button>Deshabilitar</button></td>
        </tr>
        <tr>
          <td>?</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><button>modificar</button></td>
          <td><button>Deshabilitar</button></td>
        </tr>

        <tr>
          <td colspan="9">
            <button>Crear nueva promoción</button> falta el enlace
          </td>
        </tr>
      </table>
      
    </main>
    <br /><br /><br />
    <footer>By  @ExpectedError </footer>
</body>
</html>