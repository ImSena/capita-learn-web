<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header>
    <nav>
        <div>
            <button class="button_menu btn">
                <i class="fa-regular fa-user"></i>
            </button>
        </div>

        <div>
            <button class="button_menu btn button_canva" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample"
                    aria-controls="offcanvasExample">
                <i class="fa-solid fa-bars"></i>
            </button>
        </div>
    </nav>

    <div class="search_content">
        <label for="search">
            <i class="fa-solid fa-magnifying-glass"></i>
        </label>
        <input type="search" class="field" name="pesquisa" id="search" placeholder="Pesquisar">
    </div>

    <div id="amount">
        <div>
            <i class="fa-solid fa-eye-slash"></i>
            <span>Ocultar Saldo</span>
            <i class="fa-solid fa-chevron-down"></i>
        </div>

        <div>
            <span id="value_total" data-value="${balance}">
                <fmt:setLocale value="pt_BR"/>
                <fmt:formatNumber value="${balance}" type="currency" currencySymbol="R$" groupingUsed="true" maxFractionDigits="2"/>
            </span>
        </div>
    </div>
</header>

<c:if test="${not empty success || not empty error}">
    <div class="toast-container position-fixed top-0 end-0 p-3" style="z-index: 1055;">
        <div class="toast align-items-center text-white ${not empty success ? 'bg-success' : 'bg-danger'} border-0 show" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    <c:out value="${not empty success ? success : error}" />
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
</c:if>