<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="components/head.jsp"%>
<main class="d-flex content-init">
  <div class="image-form">
  </div>

  <div class="d-flex flex-column container p-5 justify-content-center align-items-center">
    <h3 class="contrast">Bem-Vindo de Volta!</h3>
    <p>Sua jornada para sua organização financeira começa aqui</p>
    <form action="${pageContext.request.contextPath}/login" class="w-100" style="max-width: 360px;">
      <div class="mb-3 input-group bg-dark-subtle rounded px-2 py-1">
                    <span class="input-group-text bg-transparent border-0 text-secondary">
                        <i class="fa fa-user"></i>
                    </span>
        <input type="text" class="form-control bg-transparent border-0 text-light" name="login"
               placeholder="Usuário" />
      </div>

      <div class="mb-1 input-group bg-dark-subtle rounded px-2 py-1">
                    <span class="input-group-text bg-transparent border-0 text-secondary">
                        <i class="fa fa-lock"></i>
                    </span>
        <input type="password" class="form-control bg-transparent border-0 text-light" name="password"
               placeholder="Senha" />
        <span class="input-group-text bg-transparent border-0 text-secondary" id="togglePassword"
              style="cursor: pointer;">
                        <i class="fa fa-eye-slash"></i>
                    </span>
      </div>

      <div class="d-flex justify-content-end mb-3">
        <a href="/esqueceu-senha" class="small contrast">Esqueceu a senha?</a>
      </div>

      <button class="btn btn-actions w-100">Entrar <i class="fa-regular fa-circle-up"></i></button>
    </form>

    <p>Não tem uma conta? <a class="contrast" href="${pageContext.request.contextPath}/cadastro">Cadastre-se</a></p>
  </div>
</main>

<script>
  const togglePassword = document.getElementById('togglePassword');
  const passwordInput = document.querySelector('input[name="password"]');
  const icon = togglePassword.querySelector('i');

  togglePassword.addEventListener('click', () => {
    const isPassword = passwordInput.type === 'password';
    passwordInput.type = isPassword ? 'text' : 'password';
    icon.classList.toggle('fa-eye');
    icon.classList.toggle('fa-eye-slash');
  });
</script>

<%@include file="components/bottom.jsp"%>
