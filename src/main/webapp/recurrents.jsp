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
    <%@include file="components/menuSide.jsp" %>

    <div id="application">
        <%@include file="components/header.jsp" %>

        <main class="container">
            <%@include file="components/actions.jsp" %>
            <section id="section-recorrentes" class="mt-4">
                <h2 class="mb-4">Meus pagamentos recorrentes</h2>

                <div class="d-flex justify-content-end mb-3">
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalRecurrents">
                        <i class="fa-solid fa-plus"></i> Novo Pagamento
                    </button>
                </div>

                <div class="row" id="recurrentsContainer">
                    <c:forEach var="recurrent" items="${recurrents}">
                        <div class="col-md-4 mb-3">
                            <div class="card cards-contents h-100">
                                <div class="card-body">
                                    <h5 class="card-title">${recurrent.title}</h5>
                                    <p class="card-text"><strong>Valor:</strong> R$ ${recurrent.amount}</p>
                                    <p class="card-text">
                                        <strong>Descrição:</strong> ${recurrent.description}
                                    </p>
                                    <p class="card-text">
                                        <strong>Ciclo:</strong>
                                        <c:choose>
                                            <c:when test="${recurrent.cycle == 'DAY'}">Diário</c:when>
                                            <c:when test="${recurrent.cycle == 'WEEK'}">Semanal</c:when>
                                            <c:when test="${recurrent.cycle == 'MONTH'}">Mensal</c:when>
                                            <c:when test="${recurrent.cycle == 'YEARLY'}">Anual</c:when>
                                            <c:otherwise>Não especificado</c:otherwise>
                                        </c:choose>
                                    </p>
                                    <p class="card-text">
                                        <strong>Próximo pagamento:</strong> ${recurrent.nextPaymentDate}
                                    </p>

                                    <div class="card-footer d-flex justify-content-between">
                                        <button
                                                type="button"
                                                class="btn btn-outline-secondary btn-sm"
                                                data-bs-toggle="modal"
                                                data-bs-target="#modalEditarRecurrents"
                                                data-id="${recurrent.recurringPaymentId}"
                                                data-title="${recurrent.title}"
                                                data-description="${recurrent.description}"
                                                data-amount="${recurrent.amount}"
                                                data-cycle="${recurrent.cycle}"
                                                data-date="${recurrent.nextPaymentDate}">
                                            Editar
                                        </button>
                                        <form method="post" action="delete-recurrents"
                                              onsubmit="return confirm('Tem certeza que deseja excluir este pagamento recorrente?');">
                                            <input type="hidden" name="id" value="${recurrent.recurringPaymentId}"/>
                                            <button class="btn btn-outline-danger btn-sm" type="submit">Excluir</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </main>

        <%@include file="components/menuMobile.jsp" %>
    </div>
</div>

<%@include file="components/modais/addInvestment.jsp" %>
<%@include file="components/modais/removeInvestment.jsp" %>

<div class="modal fade" id="modalEditarRecurrents" tabindex="-1" aria-labelledby="modalEditarRecurrentsLabel" aria-hidden="true">
    <div class="modal-dialog modal-actions">
        <div class="modal-content modal-content-actions">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalEditarRecurrentsLabel">Editar Recorrência</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body">
                <form id="formEditarRecurrents" action="edit-recurrents" method="POST">
                    <input type="hidden" name="recurrentsId" id="recurrentsId">

                    <div class="mb-3">
                        <label for="editTitle" class="form-label">Título</label>
                        <input type="text" class="form-control" name="title" id="editTitle" placeholder="Título">
                    </div>

                    <div class="mb-3">
                        <label for="editDescription" class="form-label">Descrição</label>
                        <input type="text" class="form-control" name="description" id="editDescription" placeholder="Descrição">
                    </div>

                    <div class="mb-3">
                        <label for="editAmount" class="form-label">Valor</label>
                        <input type="number" class="form-control" id="editAmount" name="amount" step="0.01" required>
                    </div>

                    <div class="mb-3">
                        <label for="editCycle" class="form-label">Ciclo</label>
                        <select id="editCycle" name="cycle" class="form-control" required>
                            <option value="" hidden selected>Selecione</option>
                            <option value="DAY">Diário</option>
                            <option value="WEEK">Semanal</option>
                            <option value="MONTH">Mensal</option>
                            <option value="YEARLY">Anual</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="editDate" class="form-label">Data do próximo pagamento</label>
                        <input type="date" class="form-control" name="next_payment_date" id="editDate" required>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-success">Salvar Alterações</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="modalRecurrents" tabindex="-1" aria-labelledby="modalRecurrentsLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-actions">
        <div class="modal-content modal-content-actions">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalRecurrentsLabel">Adicionar Pagamento Recorrente</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body">
                <form id="formRecurrents" method="POST" action="recurrents">
                    <div class="mb-3">
                        <label for="title" class="form-label">Título</label>
                        <input type="text" class="form-control" name="title" id="title" placeholder="Título">
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label">Descrição</label>
                        <input type="text" class="form-control" name="description" id="description" placeholder="Descrição">
                    </div>

                    <div class="mb-3">
                        <label for="amount" class="form-label">Valor</label>
                        <input type="number" class="form-control" id="amount" name="amount" step="0.01" required>
                    </div>

                    <div class="mb-3">
                        <label for="cycle" class="form-label">Ciclo</label>
                        <select id="cycle" name="cycle" class="form-control" required>
                            <option value="" hidden selected>Selecione</option>
                            <option value="DAY">Diário</option>
                            <option value="WEEK">Semanal</option>
                            <option value="MONTH">Mensal</option>
                            <option value="YEARLY">Anual</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="date" class="form-label">Data do próximo pagamento</label>
                        <input type="date" class="form-control" name="next_payment_date" id="date" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Adicionar Pagamento Recorrente</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    const modalEditarRecurrents = document.getElementById('modalEditarRecurrents');
    modalEditarRecurrents.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;

        const id = button.getAttribute('data-id');
        const title = button.getAttribute('data-title');
        const description = button.getAttribute('data-description');
        const amount = button.getAttribute('data-amount');
        const cycle = button.getAttribute('data-cycle');
        const date = button.getAttribute('data-date');

        document.getElementById('recurrentsId').value = id;
        document.getElementById('editTitle').value = title;
        document.getElementById('editDescription').value = description;
        document.getElementById('editAmount').value = amount;
        document.getElementById('editCycle').value = cycle;
        document.getElementById('editDate').value = date;
    });
</script>



<%@include file="components/bottom.jsp" %>
