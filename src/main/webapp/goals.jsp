<%@ page import="br.com.capitalearn.capitalearnweb.model.Goal" %><%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="components/protect.jsp"%>
<%@include file="components/head.jsp" %>
<div class="root">
    <%@include file="components/menuSide.jsp" %>

    <div id="application">
        <%@include file="components/header.jsp" %>

        <main class="container">
            <%@include file="components/actions.jsp" %>

            <section id="metas" class="mt-4">
                <h2 class="mb-4">Minhas Metas</h2>

                <div class="d-flex justify-content-end mb-3">
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalNovaMeta">
                        <i class="fa-solid fa-plus"></i> Nova Meta
                    </button>
                </div>

                <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
                    <!-- Exemplo de meta -->
                    <c:forEach var="goal" items="${goals}">
                        <div class="col">
                            <div class="card cards-contents h-100">
                                <div class="card-body">
                                    <h5 class="card-title">${goal.title}</h5>
                                    <p class="card-text">${goal.description}</p>
                                    <p class="card-text"><strong>Objetivo:</strong> R$ ${goal.goalAmount}</p>
                                    <p class="card-text"><strong>Guardado:</strong> R$ ${goal.goalAmountContain}</p>

                                    <c:set var="amount" value="${goal.goalAmount}"/>
                                    <c:set var="contain" value="${goal.goalAmountContain}"/>
                                    <c:set var="progressRaw" value="${(contain / amount) * 100}" />
                                    <fmt:formatNumber var="progress" value="${progressRaw}" maxFractionDigits="0" />

                                    <div class="progress mt-2">
                                        <div class="progress-bar" role="progressbar"
                                             style="width: ${progress}%"
                                             aria-valuenow="${progress}"
                                             aria-valuemin="0"
                                             aria-valuemax="100">
                                                ${progress}%
                                        </div>
                                    </div>
                                </div>
                                <div class="card-footer d-flex justify-content-between">
                                        <button
                                                type="button"
                                                class="btn btn-outline-secondary btn-sm"
                                                data-bs-toggle="modal"
                                                data-bs-target="#modalEditarMeta"
                                                data-id="${goal.goalId}"
                                                data-title="${goal.title}"
                                                data-description="${goal.description}"
                                                data-goalamount="${goal.goalAmount}"
                                                data-contain="${goal.goalAmountContain}"
                                                data-duedate="${goal.dueDate}"
                                                data-priority="${goal.priority}">
                                            Editar
                                        </button>
                                    <form method="post" action="deletar-goal"
                                          onsubmit="return confirm('Tem certeza que deseja excluir esta meta?');">
                                        <input type="hidden" name="id" value="${goal.goalId}"/>
                                        <button class="btn btn-outline-danger btn-sm" type="submit">Excluir</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                    <!-- Outras metas vão aqui -->
                </div>
            </section>
        </main>

        <%@include file="components/menuMobile.jsp" %>
    </div>
</div>

<%@include file="components/modais/addGoal.jsp" %>
<%@include file="components/modais/removeGoal.jsp" %>


<div class="modal fade" id="modalNovaMeta" tabindex="-1" aria-labelledby="modalNovaMetaLabel" aria-hidden="true">
    <div class="modal-dialog modal-actions">
        <div class="modal-content modal-content-actions">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalNovaMetaLabel">Nova Meta</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body">
                <form id="formNovaMeta" action="goals" method="POST">
                    <div class="mb-3">
                        <label for="tituloMeta" class="form-label">Título da Meta</label>
                        <input type="text" class="form-control" id="tituloMeta" name="title"
                               placeholder="Ex: Novo notebook"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="descricaoMeta" class="form-label">Descrição</label>
                        <input type="text" class="form-control" id="descricaoMeta" name="description"
                               placeholder="Motivo da meta">
                    </div>
                    <div class="mb-3">
                        <label for="dueDate" class="form-label">Data Vencimento</label>
                        <input type="date" class="form-control" id="dueDate" name="dueDate">
                    </div>
                    <div class="mb-3">
                        <label for="valorMeta" class="form-label">Valor necessário</label>
                        <input type="number" class="form-control" id="valorMeta" name="goalAmount"
                               placeholder="Ex: 3000.00" required>
                    </div>
                    <div class="mb-3">
                        <label for="valorGuardado" class="form-label">Já guardado</label>
                        <input type="number" class="form-control" name="goalAmountContain" id="valorGuardado"
                               placeholder="Ex: 500.00">
                    </div>
                    <div class="mb-3">
                        <label for="valorGuardado" class="form-label">Prioridade</label>
                        <select name="priority" class="form-select" id="valorGuardado">
                            <option selected hidden>Escolher</option>
                            <option value="HARD">Alta</option>
                            <option value="NORMAL">Média</option>
                            <option value="EASY">Baixa</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-success">Salvar Meta</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalEditarMeta" tabindex="-1" aria-labelledby="modalEditarMetaLabel" aria-hidden="true">
    <div class="modal-dialog modal-actions">
        <div class="modal-content modal-content-actions">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalEditarMetaLabel">Editar Meta</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body">
                <form id="formEditarMeta" action="edit-goal" method="POST">
                    <input type="hidden" name="goalId" id="editGoalId">

                    <div class="mb-3">
                        <label for="editTituloMeta" class="form-label">Título da Meta</label>
                        <input type="text" class="form-control" id="editTituloMeta" name="title" required>
                    </div>
                    <div class="mb-3">
                        <label for="editDescricaoMeta" class="form-label">Descrição</label>
                        <input type="text" class="form-control" id="editDescricaoMeta" name="description">
                    </div>
                    <div class="mb-3">
                        <label for="editDueDate" class="form-label">Data Vencimento</label>
                        <input type="date" class="form-control" id="editDueDate" name="dueDate">
                    </div>
                    <div class="mb-3">
                        <label for="editValorMeta" class="form-label">Valor necessário</label>
                        <input type="number" class="form-control" id="editValorMeta" name="goalAmount" required>
                    </div>
                    <div class="mb-3">
                        <label for="editValorGuardado" class="form-label">Já guardado</label>
                        <input type="number" class="form-control" id="editValorGuardado" name="goalAmountContain">
                    </div>
                    <div class="mb-3">
                        <label for="editPrioridade" class="form-label">Prioridade</label>
                        <select name="priority" class="form-select" id="editPrioridade">
                            <option value="HARD">Alta</option>
                            <option value="NORMAL">Média</option>
                            <option value="EASY">Baixa</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-success">Salvar Alterações</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    const modalEditar = document.getElementById('modalEditarMeta');
    modalEditar.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;

        document.getElementById('editGoalId').value = button.getAttribute('data-id');
        document.getElementById('editTituloMeta').value = button.getAttribute('data-title');
        document.getElementById('editDescricaoMeta').value = button.getAttribute('data-description');
        document.getElementById('editValorMeta').value = button.getAttribute('data-goalamount');
        document.getElementById('editValorGuardado').value = button.getAttribute('data-contain');
        document.getElementById('editDueDate').value = button.getAttribute('data-duedate');

        const prioridade = button.getAttribute('data-priority');
        const select = document.getElementById('editPrioridade');
        select.value = prioridade;
    });
</script>



<%@include file="components/bottom.jsp" %>
