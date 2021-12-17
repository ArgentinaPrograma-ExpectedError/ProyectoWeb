<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="type"
			class='col-form-label ${promotion.getErrors().get("promotionType") != null ? "is-invalid" : "" }'>Tipo
			de promocion:</label> <input class="form-control" type="text"
			id="promotionType" name="promotionType" required
			value="${promotion.promotionType}"></input>
		<div class="invalid-feedback">
			<c:out value='${promotion.getErrors().get("promotionType")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="name" required
			value="${promotion.name}">
	</div>
	<div class="mb-3">
		<label for="type"
			class='col-form-label ${promotion.getErrors().get("attractionType") != null ? "is-invalid" : "" }'>Tipo
			de atracciones:</label> <input class="form-control" type="text"
			id="attractionType" name="attractionType" required
			value="${promotion.attractionType}"></input>
		<div class="invalid-feedback">
			<c:out value='${promotion.getErrors().get("attractionType")}'></c:out>
		</div>
	</div>
	<c:if test='${promotion.promotionType.equals("ABSOLUTA")}'><div class="mb-3">
			<label for="price"
				class='col-form-label ${promotion.getErrors().get("cost") != null ? "is-invalid" : "" }'>Precio</label>
			<input class="form-control" type="number" id="price" name="price"
				required value="${promotion.getCost()}"></input>
			<div class="invalid-feedback">
				<c:out value='${promotion.getErrors().get("cost")}'></c:out>
			</div>
		</div>
	</c:if>
	<c:if test='${promotion.promotionType.equals("PORCENTUAL")}'>
		<div class="mb-3">
			<label for="discount"
				class='col-form-label ${promotion.getErrors().get("cost") != null ? "is-invalid" : "" }'>Descuento:</label>
			<input class="form-control" type="number" id="discount"
				name="discount" required value="${promotion.getDiscount()}"></input>
			<div class="invalid-feedback">
				<c:out value='${promotion.getErrors().get("cost")}'></c:out>
			</div>
		</div>
	</c:if>
	<div class="mb-3">
		<label for="attractions"
			class='col-form-label ${promotion.getErrors().get("sameType") != null ? "is-invalid" : "" }'>Atracciones:</label>
		<input class="form-control" type="text" id="attractions"
			name="attractions" required value="${promotion.attractions}"></input>
		<div class="invalid-feedback">
			<c:out value='${promotion.getErrors().get("sameType")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="description" class="col-form-label">Descripción:</label> <input
			type="text" class="form-control" id="description" name="description"
			required value="${promotion.description}">
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>