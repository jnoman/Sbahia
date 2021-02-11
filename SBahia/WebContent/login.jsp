<body>
<style><%@include file="/css/style.css"%></style>
	<div class="login-reg-panel">
		<div class="login-info-box">
			<h2>avez déjà un compte?</h2>
			<label id="label-register" for="log-reg-show">Connexion</label>
			<input type="radio" name="active-log-panel" id="log-reg-show"  checked="checked">
		</div>
							
		<div class="register-info-box">
			<h2>Vous n'avez pas de compte?</h2>
			<label id="label-login" for="log-login-show">S'inscrire</label>
			<input type="radio" name="active-log-panel" id="log-login-show">
		</div>
							
		<div class="white-panel">
			<div class="login-show">
				<h2>Connexion</h2>
				<form action="login" method="post">
					<input type="text" placeholder="Email" name="email" required>
					<input type="password" placeholder="Mot de passe" name="password" minlength="8" required>
					<button type="submit" class="btn btn-primary">Connexion</button>
				</form>
				<c:if test="${ !empty sessionScope.succes }">
					<p style="color: green;" class="mt-3">${sessionScope.succes}</p>
			        <c:remove var="succes" scope="session" />
				</c:if>
				
				<c:if test="${ !empty sessionScope.erreur }">
					<p style="color: red;" class="mt-3">
						<c:out value="${ sessionScope.erreur }" />
						<c:remove var="erreur" scope="session" />
					</p>
				</c:if>
			</div>
			<div class="register-show">
				<h2>S'inscrire</h2>
				<form action="inscription" method="post">
					<input type="text" placeholder="Nom complet" name="nomComplet" minlength="4" required>
					<input type="text" placeholder="Email" name="email" required>
					<input type="password" placeholder="Mot de passe" name="password" minlength="8" required>
					<button type="submit" class="btn btn-primary">S'inscrire</button>
				</form>
			</div>
		</div>
	</div>
	
	
	<script><%@include file="/js/login.js"%></script>
</body>
</html>