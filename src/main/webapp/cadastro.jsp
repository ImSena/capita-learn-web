<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 21:22
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
        <form action="${pageContext.request.contextPath}/cadastro" class="w-100" style="max-width: 500px;">
            <div class="mb-3 input-group bg-dark-subtle rounded px-2 py-1">
                    <span class="input-group-text bg-transparent border-0 text-secondary">
                        <i class="fa fa-user"></i>
                    </span>
                <input type="text" class="form-control bg-transparent border-0 text-light" name="login"
                       placeholder="Nome completo" />
            </div>

            <div class="mb-3 input-group bg-dark-subtle rounded px-2 py-1">
                    <span class="input-group-text bg-transparent border-0 text-secondary">
                        <i class="fa-solid fa-calendar"></i>
                    </span>
                <input type="date" class="form-control bg-transparent border-0 text-light" name="login"
                       placeholder="Data de nascimento" />
            </div>

            <div class="mb-3 input-group bg-dark-subtle rounded px-2 py-1">
                    <span class="input-group-text bg-transparent border-0 text-secondary">
                        <i class="fa-solid fa-envelope"></i>
                    </span>
                <input type="email" class="form-control bg-transparent border-0 text-light" name="login"
                       placeholder="Email" />
            </div>

            <div class="mb-3 input-group bg-dark-subtle rounded px-2 py-1">
                    <span class="input-group-text bg-transparent border-0 text-secondary">
                        <i class="fa-solid fa-phone"></i>
                    </span>
                <input type="text" class="form-control bg-transparent border-0 text-light" name="login"
                       placeholder="Contato" />
            </div>

            <div class="mb-3 input-group bg-dark-subtle rounded px-2 py-1">
                    <span class="input-group-text bg-transparent border-0 text-secondary">
                        <i class="fa-solid fa-dollar-sign"></i>
                    </span>
                <input type="text" class="form-control bg-transparent border-0 text-light" name="saldo"
                       placeholder="Saldo total" />
            </div>

            <div class="mb-3 input-group bg-dark-subtle rounded px-2 py-1">
                    <span class="input-group-text bg-transparent border-0 text-secondary">
                        <i class="fa fa-lock"></i>
                    </span>
                <input type="password" class="form-control bg-transparent border-0 text-light" name="password"
                       placeholder="Senha" id="senha" />
                <span class="input-group-text bg-transparent border-0 text-secondary" id="toggleSenha"
                      style="cursor: pointer;">
                        <i class="fa fa-eye-slash"></i>
                    </span>
            </div>

            <!-- Confirmar senha -->
            <div class="mb-3 input-group bg-dark-subtle rounded px-2 py-1">
                    <span class="input-group-text bg-transparent border-0 text-secondary">
                        <i class="fa fa-lock"></i>
                    </span>
                <input type="password" class="form-control bg-transparent border-0 text-light"
                       name="confirmPassword" placeholder="Confirme sua senha" id="confirmSenha" />
                <span class="input-group-text bg-transparent border-0 text-secondary" id="toggleConfirmSenha"
                      style="cursor: pointer;">
                        <i class="fa fa-eye-slash"></i>
                    </span>
            </div>
            <button class="btn btn-actions w-100">Cadastrar <i class="fa-regular fa-circle-up"></i></button>
        </form>

        <p>Já tem uma conta? <a class="contrast" href="${pageContext.request.contextPath}/login">Login</a></p>
    </div>
</main>
<script>
    function setupTogglePassword(toggleId, inputId) {
        const toggle = document.getElementById(toggleId);
        const input = document.getElementById(inputId);
        const icon = toggle.querySelector('i');

        toggle.addEventListener('click', () => {
            const isPassword = input.type === 'password';
            input.type = isPassword ? 'text' : 'password';
            icon.classList.toggle('fa-eye');
            icon.classList.toggle('fa-eye-slash');
        });
    }

    setupTogglePassword('toggleSenha', 'senha');
    setupTogglePassword('toggleConfirmSenha', 'confirmSenha');
</script>

<%@include file="components/bottom.jsp"%>