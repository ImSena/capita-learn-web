<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav id="content_actions">

    <%
        String currentUrl = request.getRequestURI();
        boolean isDashboard = currentUrl.contains("/dashboard");
    %>

    <% if (isDashboard) { %>
    <div class="button_actions">
        <button class="btn" data-bs-toggle="modal" data-bs-target="#modalAdicionar">
            <i class="fa-solid fa-circle-plus"></i>
        </button>
        <span>Adicionar</span>
    </div>

    <div class="button_actions">
        <button class="btn" data-bs-toggle="modal" data-bs-target="#modalRetirar">
            <i class="fa-solid fa-circle-minus"></i>
        </button>
        <span>Retirar</span>
    </div>
    <% } %>

    <div class="button_actions">
        <button class="btn" id="btnRecorrentes">
            <i class="fa-solid fa-briefcase"></i>
        </button>
        <span>Recorrentes</span>
    </div>
    <div class="button_actions">
        <button class="btn" id="btnMetas">
            <i class="fa-solid fa-piggy-bank"></i>
        </button>
        <span>Metas</span>
    </div>
    <div class="button_actions">
        <button class="btn" id="btnAplicacoes">
            <i class="fa-solid fa-square-poll-vertical"></i>
        </button>
        <span>Aplicações</span>
    </div>
</nav>
