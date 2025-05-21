<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="components/head.jsp"%>
<main class="d-flex content-init">
    <div class="image-form">
    </div>

    <div class="d-flex flex-column container p-5 justify-content-center align-items-center">
        <h2>Descubra um novo mundo de possibilidades financeiras. <span class="contrast">Inove seu futuro</span></h2>
        <p style="font-weight: 300; text-align: left; width: 100%;">Desperte seu potencial financeiro e conquiste seus objetivos com facilidade.</p>

        <button class="btn btn-actions">Começar Agora <i class="fa-regular fa-circle-up"></i></button>

        <p>Já tem uma conta? <a class="contrast" href="${pageContext.request.contextPath}/login">Login</a></p>
    </div>
</main>

<%@include file="components/bottom.jsp"%>
