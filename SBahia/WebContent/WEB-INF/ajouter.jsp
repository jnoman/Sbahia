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

	<form method="post" action="ajouter" enctype="multipart/form-data">
		<script language="JavaScript">
			function showPreview(ele) {
				$("#imgAvatar").attr("src", ele.value); // for IE
				if (ele.files && ele.files[0]) {

					var reader = new FileReader();

					reader.onload = function(e) {
						$("#imgAvatar").attr("src", e.target.result);
					}

					reader.readAsDataURL(ele.files[0]);
				}
			}
		</script>
		<div class="container mt-1" >
			<div class="container">
				<input type="file" name="image" accept="image/*"
					OnChange="showPreview(this)" required>
				<hr>
				<img id="imgAvatar" class="rounded mx-auto d-block" style="width: 400px; height: 300px; margin-bottom: 30px;">
			</div>
			<div class="form-row mb-1" style="display: flex;">
				<div class="form-group col-md-4">
					<label>Nom Article</label> 
					<input type="text" class="form-control" name="nom" minlength="4" required >
				</div>
				<div class="form-group col-md-4 ms-5">
					<label>Quantité</label> 
					<input class="form-control" name="quantite" type="number" min="0" max="100" required>
				</div>
				<div class="form-group col-md-4 ms-5">
					<label>Prix</label> 
					<input class="form-control" name="prix" type="number" min="100" max="10000" required>
				</div>
			</div>
			<div class="form-group mb-3">
				<label for="description">Description</label>
				<textarea class="form-control" name="description" rows="3" minlength="20"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">ajouter</button>
		</div>
	</form>

</body>
</html>