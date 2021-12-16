<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="username" name="username" required
			value="${user.username}">
	</div>
	
	
	<div class="mb-3">
		<label for="cost"
			class='col-form-label ${user.errors.get("coins") != null ? "is-invalid" : "" }'>Dinero:</label>
		<input class="form-control" type="number" id="coins" name="coins"
			required value="${user.coins}"></input>
		<div class="invalid-feedback">
			<c:out value='${user.errors.get("coins")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="time"
			class='col-form-label ${user.errors.get("time") != null ? "is-invalid" : "" }'>Tiempo disponible:</label>
		<input class="form-control" type="number" id="time"
			name="time" required value="${user.time}"></input>
		<div class="invalid-feedback">
			<c:out value='${user.errors.get("time")}'></c:out>
		</div>
	</div>

	<div class="mb-3">
		<label for="type"
			class='col-form-label ${user.errors.get("type") != null ? "is-invalid" : "" }'>Tipo:</label>
		<input class="form-control" type="text" id="type" name="type" 
			value="${user.type}"></input>
		<div class="invalid-feedback">
			<c:out value='${user.errors.get("type")}'></c:out>
		</div>
	</div>
<div class="mb-3">
		<label for="isAdmin" class='col-form-label'>Es administrador: </label>
		  <input type="checkbox" id="isAdmin" name="isAdmin"></input>
		

		</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
