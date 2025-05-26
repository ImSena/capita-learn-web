<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="components/protect.jsp"%>
<%@include file="components/head.jsp" %>
<div class="root">
    <%@include file="components/menuSide.jsp"%>

    <div id="application">
        <%@include file="components/header.jsp"%>

        <main class="container" style="max-width: 600px; margin-top: 2rem;">
            <h2>Configurações</h2>

            <form id="form-configuracoes" method="POST" action="/configuracoes/atualizar">

                <div class="form-check form-switch mb-3">
                    <input class="form-check-input" type="checkbox" id="notificacoes" name="notificacoes" checked>
                    <label class="form-check-label" for="notificacoes">Ativar notificações</label>
                </div>

                <div class="mb-3">
                    <label for="tema" class="form-label">Tema</label>
                    <select class="form-select" id="tema" name="tema">
                        <option value="claro" selected>Claro</option>
                        <option value="escuro">Escuro</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="idioma" class="form-label">Idioma</label>
                    <select class="form-select" id="idioma" name="idioma">
                        <option value="pt-br" selected>Português (Brasil)</option>
                        <option value="en-us">English (US)</option>
                        <option value="es">Español</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Salvar Configurações</button>
            </form>
        </main>

        <%@include file="components/menuMobile.jsp"%>
    </div>
</div>

<%@include file="components/bottom.jsp"%>
