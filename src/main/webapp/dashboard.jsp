<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 17/05/2025
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
                        <div class="transacao">
                            <div class="type">
                                <div class="content_img">
                                    <img src="https://placehold.co/40" alt="">
                                </div>
                                <div class="description">
                                    <span>Transação Ok</span>
                                    <span>09 de Setembro</span>
                                </div>
                            </div>
                            <div class="valor">
                                <span class="color_error">-R$30,07</span>
                            </div>
                        </div>
                        <div class="transacao">
                            <div class="type">
                                <div class="content_img">
                                    <img src="https://placehold.co/40" alt="">
                                </div>
                                <div class="description">
                                    <span>Transação Ok</span>
                                    <span>09 de Setembro</span>
                                </div>
                            </div>
                            <div class="valor">
                                <span class="color_success">+R$3000,00</span>
                            </div>
                        </div>
                        <div class="transacao">
                            <div class="type">
                                <div class="content_img">
                                    <img src="https://placehold.co/40" alt="">
                                </div>
                                <div class="description">
                                    <span>Transação Ok</span>
                                    <span>09 de Setembro</span>
                                </div>
                            </div>
                            <div class="valor">
                                <span class=" color_success">+R$200,00</span>
                            </div>
                        </div>
                        <div class="transacao">
                            <div class="type">
                                <div class="content_img">
                                    <img src="https://placehold.co/40" alt="">
                                </div>
                                <div class="description">
                                    <span>Transação Ok</span>
                                    <span>09 de Setembro</span>
                                </div>
                            </div>
                            <div class="valor">
                                <span class="color_error">-R$312,22</span>
                            </div>
                        </div>
                        <div class="transacao">
                            <div class="type">
                                <div class="content_img">
                                    <img src="https://placehold.co/40" alt="">
                                </div>
                                <div class="description">
                                    <span>Transação Ok</span>
                                    <span>09 de Setembro</span>
                                </div>
                            </div>
                            <div class="valor">
                                <span class="color_error">-R$312,22</span>
                            </div>
                        </div>
                        <div class="transacao">
                            <div class="type">
                                <div class="content_img">
                                    <img src="https://placehold.co/40" alt="">
                                </div>
                                <div class="description">
                                    <span>Transação Ok</span>
                                    <span>09 de Setembro</span>
                                </div>
                            </div>
                            <div class="valor">
                                <span class="color_error">-R$30,07</span>
                            </div>
                        </div>
                    </div>

                    <div id="relatorio" class="overview-content">
                        <h3>Relatório Mensal</h3>

                        <!-- Filtro de mês e ano -->
                        <form id="filtro-relatorio" style="margin-bottom: 1rem;">
                            <label for="mes">Mês:</label>
                            <select id="mes" name="mes">
                                <option value="01">Janeiro</option>
                                <option value="02">Fevereiro</option>
                                <option value="03">Março</option>
                                <option value="04">Abril</option>
                                <option value="05">Maio</option>
                                <option value="06">Junho</option>
                                <option value="07">Julho</option>
                                <option value="08">Agosto</option>
                                <option value="09">Setembro</option>
                                <option value="10">Outubro</option>
                                <option value="11">Novembro</option>
                                <option value="12">Dezembro</option>
                            </select>

                            <label for="ano" style="margin-left: 1rem;">Ano:</label>
                            <select id="ano" name="ano">
                                <!-- Você pode preencher dinamicamente pelo backend -->
                                <option value="2024">2024</option>
                                <option value="2025" selected>2025</option>
                                <option value="2026">2026</option>
                            </select>

                            <button type="submit" style="margin-left: 1rem;">Gerar</button>
                        </form>

                        <!-- Título dinâmico do mês/ano selecionado -->
                        <div id="titulo-relatorio" style="margin-bottom: 1rem; font-weight: 600;">
                            Resumo para <span id="mes-ano-selecionado">Maio / 2025</span>
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
                                <td class="valor-positivo">R$ 0,00</td>
                            </tr>
                            <tr>
                                <td>Total Saídas</td>
                                <td class="valor-negativo">R$ 0,00</td>
                            </tr>
                            <tr>
                                <td><strong>Saldo Final</strong></td>
                                <td class="saldo">R$ 0,00</td>
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