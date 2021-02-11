<body>
	<%@ include file="menu.jsp"%>

	<c:if test="${ !empty erreur }">
		<p style="color: red;" class="mt-3">
			<c:out value="${ erreur }" />
		</p>
	</c:if>
	
	<c:if test="${ !empty succes }">
		<p style="color: green;" class="mt-3">
			<c:out value="${ succes }" />
		</p>
	</c:if>
	
	<c:choose>
    <c:when test="${ !empty article }">
		<form method="post" action="detail?id=${ article.getId() }">
		<div class="container mt-3" >
			<div class="container">
				<img src="data:image/jpeg;base64,${ image }" class="rounded mx-auto d-block" style="width: 470px; height: 370px; margin-bottom: 30px;">
			</div>
			<div class="form-row mb-3" style="display: flex;">
				<div class="form-group col-md-4">
					<label>Nom Article</label> 
					<input type="text" class="form-control" name="nom" id="nom" minlength="4" required value="${ article.getNom() }">
				</div>
				<div class="form-group col-md-4 ms-5">
					<label>Quantité</label> 
					<input class="form-control" name="quantite" type="number" min="0" max="100" required value="${ article.getQuantite() }">
				</div>
				<div class="form-group col-md-4 ms-5">
					<label>Prix</label> 
					<input class="form-control" name="prix" type="number" min="100" max="10000" required value="${ article.getPrix() }">
				</div>
			</div>
			<div class="form-group mb-3">
				<label for="description">Description</label>
				<textarea class="form-control" name="description" rows="3" minlength="20">${ article.getDescription() }</textarea>
			</div>
			<button type="submit" class="btn btn-info btn-lg" name="modifier">modifier</button>
			<button type="submit" class="btn btn-danger btn-lg" name="supprimer">supprimer</button>
		</div>
	</form>
	</c:when>
    <c:otherwise>
    	<h1 style="text-align: center;" class="mt-5">Aucun article avec cet id</h1>
    </c:otherwise>
</c:choose>
</body>
</html>