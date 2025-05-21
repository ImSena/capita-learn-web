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

            <section id="servicos" class="mt-4">
                <h2 class="mb-4">Serviços Contratados</h2>

                <div class="d-flex justify-content-end mb-3">
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalAdicionarServico">
                        <i class="fa-solid fa-plus"></i> Novo Serviço
                    </button>
                </div>

                <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
                    <!-- Exemplo de serviço -->
                    <div class="col">
                        <div class="card h-100">
                            <div class="card-body">
                                <h5 class="card-title">Streaming Premium</h5>
                                <p class="card-text">Assinatura mensal de música</p>
                                <p class="card-text"><strong>Valor:</strong> R$ 29,90</p>
                                <p class="card-text"><strong>Recorrência:</strong> Mensal</p>
                                <p class="card-text text-success"><strong>Status:</strong> Ativo</p>
                            </div>
                            <div class="card-footer d-flex justify-content-between">
                                <button class="btn btn-outline-secondary btn-sm">Editar</button>
                                <button class="btn btn-outline-danger btn-sm">Cancelar</button>
                            </div>
                        </div>
                    </div>

                    <!-- Outros cards de serviço podem ser adicionados dinamicamente -->
                </div>
            </section>
        </main>

        <%@include file="components/menuMobile.jsp"%>
    </div>
</div>

<%@include file="components/modais/addSaldo.jsp"%>
<%@include file="components/modais/removeSaldo.jsp"%>

<div class="modal fade" id="modalAdicionarServico" tabindex="-1" aria-labelledby="modalAdicionarServicoLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-actions">
        <div class="modal-content modal-content-actions">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalAdicionarServicoLabel">Novo Serviço</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body">
                <form id="formAdicionarServico">
                    <div class="mb-3">
                        <label for="nomeServico" class="form-label">Nome do Serviço</label>
                        <input type="text" class="form-control" id="nomeServico" placeholder="Ex: Netflix" required>
                    </div>
                    <div class="mb-3">
                        <label for="descricaoServico" class="form-label">Descrição</label>
                        <input type="text" class="form-control" id="descricaoServico"
                               placeholder="Ex: Vídeos e filmes online">
                    </div>
                    <div class="mb-3">
                        <label for="valorServico" class="form-label">Valor</label>
                        <input type="number" class="form-control" id="valorServico" placeholder="Ex: 39.90"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="recorrenciaServico" class="form-label">Recorrência</label>
                        <select class="form-select" id="recorrenciaServico">
                            <option value="mensal">Mensal</option>
                            <option value="anual">Anual</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Adicionar Serviço</button>
                </form>
            </div>
        </div>
    </div>
</div>



<%@include file="components/bottom.jsp"%>
