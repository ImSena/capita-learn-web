<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="components/head.jsp" %>
<div class="root">
  <%@include file="components/menuSide.jsp"%>

  <div id="application">
    <%@include file="components/header.jsp"%>

    <main class="container" style="max-width: 600px; margin-top: 2rem;">
      <h2>Perfil do Usuário</h2>
      <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
      </c:if>

      <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
      </c:if>

      <form id="form-perfil" method="POST" action="perfil">
        <div class="mb-3">
          <label for="nome" class="form-label">Nome Completo</label>
          <input type="text" class="form-control" id="nome" value="${user.fullName}" name="nome" placeholder="Seu nome completo"
                 required>
        </div>

        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="email@exemplo.com"
                 required value="${user.email}">
        </div>

        <div class="mb-3">
          <label for="telefone" class="form-label">Telefone</label>
          <input type="tel" class="form-control" id="telefone" name="telefone"
                 placeholder="(99) 99999-9999" value="${user.phoneNumber}">
        </div>

        <div class="mb-3">
          <label for="senha" class="form-label">Senha</label>
          <input type="password" class="form-control" id="senha" name="senha"
                 placeholder="Digite para alterar">
          <small class="form-text text-muted">Deixe em branco para manter a senha atual.</small>
        </div>

        <button type="submit" class="btn btn-primary">Salvar Perfil</button>
      </form>
    </main>

    <%@include file="components/menuMobile.jsp"%>
  </div>
</div>

<%@include file="components/bottom.jsp"%>
