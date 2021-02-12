<body>
	<%@include file="menu.jsp" %>
	
	<div class="container mt-5">

		<c:if test="${ !empty erreur }">
			<p style="color: red;">
				<c:out value="${ erreur }" />
			</p>
		</c:if>
		
		<c:if test="${ !empty sessionScope.erreur }">
			<p style="color: red;">
				<c:out value="${ sessionScope.erreur }" />
				<c:remove var="succes" scope="session" />
			</p>
		</c:if>

		<c:if test="${ !empty sessionScope.succes }">
			<p style="color: green;">
				<c:out value="${ sessionScope.succes }" />
				<c:remove var="succes" scope="session" />
			</p>
		</c:if>

		<c:if test="${empty listProduit}">
			<h1 style="text-align: center;" class="mt-5">Aucune article</h1>
		</c:if>

		<div class="row row-cols-1 row-cols-md-3 g-4">
			<c:forEach var="article" items="${ listProduit }" varStatus="status">
				<div class="col-lg-4 d-flex align-items-stretch">
					<div class="card mx-2">
						<img src="data:image/jpeg;base64,${ images[status.index] }"
							class="card-img-top" alt="..." style="height: 200px">
						<div class="card-body">
							<h6 class="card-title">
								<c:out value="${ article.getNom() }" />
							</h6>
							<p class="card-text" style="font-size: 10px;">
								<c:out value="${ article.getDescription() }" />
							</p>
							<span class="span-like" value="${ article.getId() }">
								<c:choose>
									<c:when test="${ dejaLike[status.index] }">
										<i class="fas fa-heart"></i>
									</c:when>
									<c:otherwise>
										<i class="far fa-heart"></i>
									</c:otherwise>
								</c:choose>
								<p>${ nombreLikes[status.index] }</p>
							</span>
							<div class="div-quantite-prix">
								<p>${ article.getQuantite() } unités disponibles</p>
								<p>prix : ${ article.getPrix() } Dh</p>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<script>
	var requete;
	var select;
		$(function () {
			$('.span-like').click(function () {
				select = $(this);
				var articleId = $(this).attr('value');
				
				if (window.XMLHttpRequest) {
				       requete = new XMLHttpRequest();
				   }
				requete.open("POST", "index?articleId="+articleId, true);
				   requete.onreadystatechange = majIHM;
				   requete.send(null);
				
			});
		});
		
		function majIHM() {
			  var message = "";
			  if (requete.readyState == 4) {
			    if (requete.status == 200) {
			      // exploitation des données de la réponse
			      var messageTag = requete.responseXML.getElementsByTagName("message")[0];
			      message = messageTag.childNodes[0].nodeValue;
				  select.children('p').text(message);
			      if(select.children('i').hasClass("far")){
			    	  select.children('i').removeClass("far");
			    	  select.children('i').addClass("fas");
					} else{
						select.children('i').removeClass("fas");
						select.children('i').addClass("far");
					}
					
			    } else {
			      alert('Une erreur est survenue lors de la mise à jour de la page.'+
				        '\n\nCode retour = '+requete.statusText);    
			    }
			  }
			}
	</script>
</body>
</html>