<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 17/05/2025
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="components/protect.jsp"%>
<%@ include file="components/head.jsp" %>
<div class="root">
    <%@include file="components/menuSide.jsp" %>
    <div id="application">
        <%@include file="components/header.jsp" %>
        <main class="container">
            <%@include file="components/actions.jsp" %>

            <section id="overview">

                <div id="button_overview">
                    <button id="historico" class="button_action active">
                        <span>Histórico</span>
                    </button>

                    <button id="relatorio" class="button_action">
                        <span>Relatórios</span>
                    </button>
                </div>

                <div class="container">
                    <div id="historico" class="overview-content active">
                        <c:forEach var="transaction" items="${transactions}">
                            <div class="transacao">
                                <div class="type">
<%--                                    <div class="content_img">--%>
<%--                                        <img src="https://placehold.co/40" alt="">--%>
<%--                                    </div>--%>
                                    <div class="description">
                                        <span>${transaction.title}</span>
                                        <span>${transaction.createdAt}</span>
                                    </div>
                                </div>
                                <div class="valor">
                                    <c:choose>
                                        <c:when test="${transaction.transactionType == 'ADD'}">
                                            <span class="color_success">+R$${transaction.amount}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="color_error">-R$${transaction.amount}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div id="relatorio" class="overview-content">
                        <h3>Relatório Mensal</h3>

                        <!-- Filtro de mês e ano -->
                        <form id="filtro-relatorio" method="post" action="dashboard" style="margin-bottom: 1rem;">
                            <label for="mes">Mês:</label>
                            <select id="mes" name="mes">
                                <option value="" selected hidden></option>
                                <option value="01" ${relatorioMes == '01' ? 'selected' : ''}>Janeiro</option>
                                <option value="02" ${relatorioMes == '02' ? 'selected' : ''}>Fevereiro</option>
                                <option value="03" ${relatorioMes == '03' ? 'selected' : ''}>Março</option>
                                <option value="04" ${relatorioMes == '04' ? 'selected' : ''}>Abril</option>
                                <option value="05" ${relatorioMes == '05' ? 'selected' : ''}>Maio</option>
                                <option value="06" ${relatorioMes == '06' ? 'selected' : ''}>Junho</option>
                                <option value="07" ${relatorioMes == '07' ? 'selected' : ''}>Julho</option>
                                <option value="08" ${relatorioMes == '08' ? 'selected' : ''}>Agosto</option>
                                <option value="09" ${relatorioMes == '09' ? 'selected' : ''}>Setembro</option>
                                <option value="10" ${relatorioMes == '10' ? 'selected' : ''}>Outubro</option>
                                <option value="11" ${relatorioMes == '11' ? 'selected' : ''}>Novembro</option>
                                <option value="12" ${relatorioMes == '12' ? 'selected' : ''}>Dezembro</option>
                            </select>

                            <label for="ano" style="margin-left: 1rem;">Ano:</label>
                            <select id="ano" name="ano">
                                <option value="" selected hidden></option>
<%--                                Deixar dinâmico com outro tipo de estrutura--%>
                                <option value="2025" ${relatorioAno == '2025' ? 'selected' : ''}>2025</option>
                                <option value="2026" ${relatorioAno == '2026' ? 'selected' : ''}>2026</option>
                            </select>

                            <button type="submit" style="margin-left: 1rem;">Gerar</button>
                        </form>

                        <div id="titulo-relatorio" style="margin-bottom: 1rem; font-weight: 600;">
                            <c:if test="${not empty relatorioMes && not empty relatorioAno}">
                                Resumo para
                                <c:choose>
                                    <c:when test="${relatorioMes == '01'}">Janeiro</c:when>
                                    <c:when test="${relatorioMes == '02'}">Fevereiro</c:when>
                                    <c:when test="${relatorioMes == '03'}">Março</c:when>
                                    <c:when test="${relatorioMes == '04'}">Abril</c:when>
                                    <c:when test="${relatorioMes == '05'}">Maio</c:when>
                                    <c:when test="${relatorioMes == '06'}">Junho</c:when>
                                    <c:when test="${relatorioMes == '07'}">Julho</c:when>
                                    <c:when test="${relatorioMes == '08'}">Agosto</c:when>
                                    <c:when test="${relatorioMes == '09'}">Setembro</c:when>
                                    <c:when test="${relatorioMes == '10'}">Outubro</c:when>
                                    <c:when test="${relatorioMes == '11'}">Novembro</c:when>
                                    <c:when test="${relatorioMes == '12'}">Dezembro</c:when>
                                </c:choose>
                                / ${relatorioAno}
                            </c:if>
                        </div>

                        <!-- Tabela de resumo -->
                        <table class="relatorio-table">
                            <thead>
                            <tr>
                                <th>Descrição</th>
                                <th>Valor</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Total Entradas</td>
                                <td class="valor-positivo">
                                    <fmt:formatNumber value="${empty totalEntradas ? 0 : totalEntradas}" type="currency" currencySymbol="R$ " />
                                </td>
                            </tr>
                            <tr>
                                <td>Total Saídas</td>
                                <td class="valor-negativo">
                                    <fmt:formatNumber value="${empty totalSaidas ? 0 : totalSaidas}" type="currency" currencySymbol="R$ " />

                                </td>
                            </tr>
                            <tr>
                                <td><strong>Saldo Final</strong></td>
                                <td class="saldo">
                                    <fmt:formatNumber value="${empty saldoFinal ? 0 : saldoFinal}" type="currency" currencySymbol="R$ " />
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
        </main>

        <%@include file="components/menuMobile.jsp" %>
    </div>
</div>

<%@include file="components/modais/addSaldo.jsp" %>
<%@include file="components/modais/removeSaldo.jsp" %>

<%@include file="components/bottom.jsp" %>