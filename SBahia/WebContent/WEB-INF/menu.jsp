<c:choose>
	<c:when test="${! empty logged }">
		<c:choose>
			<c:when test="${ role.getNomRole()=='admin' }">
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<div class="container-fluid">
						<a class="navbar-brand" href="home">SBahia</a>
						<button class="navbar-toggler" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav me-auto mb-2 mb-lg-0">
								<li class="nav-item"><a class="nav-link" href="home">Home</a>
								</li>
								<li class="nav-item"><a class="nav-link" href="ajouter">Ajouter</a>
								</li>
							</ul>
							<form class="d-flex" style="width: 100%;display: inline-block !important;" action="deconnexion" method="get">
								<button class="btn btn-outline-success" style="float: right;" type="submit">Déconnexion</button>
							</form>
						</div>
					</div>
				</nav>
			</c:when>
			<c:otherwise>
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<div class="container-fluid">
						<a class="navbar-brand" href="index">SBahia</a>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<form class="d-flex" style="width: 100%;display: inline-block !important;" action="deconnexion" method="get">
								<button class="btn btn-outline-success" style="float: right;" type="submit">Déconnexion</button>
							</form>
						</div>
					</div>
				</nav>
			</c:otherwise>
		</c:choose>
	</c:when>
</c:choose>
