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

        <main class="container">
            <nav id="content_actions">
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
                <div class="button_actions">
                    <button class="btn">
                        <i class="fa-solid fa-briefcase"></i>
                    </button>
                    <span>Serviços</span>
                </div>
                <div class="button_actions">
                    <button class="btn">
                        <i class="fa-solid fa-piggy-bank"></i>
                    </button>
                    <span>Metas</span>
                </div>
                <div class="button_actions">
                    <button class="btn">
                        <i class="fa-solid fa-square-poll-vertical"></i>
                    </button>
                    <span>Aplicações</span>
                </div>
            </nav>
            <section id="section-investimentos" class="mt-4">
                <h2 class="mb-4">Meus Investimentos</h2>

                <div class="d-flex justify-content-end mb-3">
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalInvestimento">
                        <i class="fa-solid fa-plus"></i> Novo Investimento
                    </button>
                </div>

                <div class="row" id="investimentosContainer">
                    <!-- Cada investimento será inserido aqui dinamicamente com JS -->
                    <!-- Exemplo de um card de investimento -->
                    <div class="col-md-6 col-lg-4 mb-3">
                        <div class="card shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Tesouro Selic</h5>
                                <p class="card-text mb-1"><strong>Valor Investido:</strong> R$ 1.000,00</p>
                                <p class="card-text mb-1"><strong>Categoria:</strong> Renda Fixa</p>
                                <p class="card-text mb-1"><strong>Data de Início:</strong> 01/05/2024</p>
                                <p class="card-text"><strong>Descrição:</strong> Investimento para reserva de
                                    emergência.</p>
                                <button class="btn btn-outline-danger btn-sm mt-2">Excluir</button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>

        <%@include file="components/menuMobile.jsp"%>
    </div>
</div>

<%@include file="components/modais/addSaldo.jsp"%>
<%@include file="components/modais/removeSaldo.jsp"%>


<div class="modal fade" id="modalInvestimento" tabindex="-1" aria-labelledby="modalInvestimentoLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-actions">
        <div class="modal-content modal-content-actions">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalInvestimentoLabel">Adicionar Investimento</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body">
                <form id="formInvestimento">
                    <div class="mb-3">
                        <label for="nomeInvestimento" class="form-label">Nome</label>
                        <input type="text" class="form-control" id="nomeInvestimento"
                               placeholder="Ex: Tesouro Selic" required>
                    </div>

                    <div class="mb-3">
                        <label for="valorInvestimento" class="form-label">Valor Investido</label>
                        <input type="number" class="form-control" id="valorInvestimento" step="0.01"
                               placeholder="Ex: 1000.00" required>
                    </div>

                    <div class="mb-3">
                        <label for="categoriaInvestimento" class="form-label">Categoria</label>
                        <select class="form-select" id="categoriaInvestimento" required>
                            <option value="">Selecione</option>
                            <option value="Renda Fixa">Renda Fixa</option>
                            <option value="Renda Variável">Renda Variável</option>
                            <option value="Criptoativos">Criptoativos</option>
                            <option value="Outros">Outros</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="dataInicioInvestimento" class="form-label">Data de Início</label>
                        <input type="date" class="form-control" id="dataInicioInvestimento" required>
                    </div>

                    <div class="mb-3">
                        <label for="descricaoInvestimento" class="form-label">Descrição</label>
                        <textarea class="form-control" id="descricaoInvestimento" rows="3"
                                  placeholder="Ex: Investimento para reserva de emergência."></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">Adicionar Investimento</button>
                </form>
            </div>
        </div>
    </div>
</div>




<%@include file="components/bottom.jsp"%>
