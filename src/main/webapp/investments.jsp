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
    <%@include file="components/menuSide.jsp" %>

    <div id="application">
        <%@include file="components/header.jsp" %>

        <main class="container">
            <%@include file="components/actions.jsp" %>
            <section id="section-investimentos" class="mt-4">
                <h2 class="mb-4">Meus Investimentos</h2>

                <div class="d-flex justify-content-end mb-3">
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalInvestimento">
                        <i class="fa-solid fa-plus"></i> Novo Investimento
                    </button>
                </div>

                <div class="row" id="investimentosContainer">
                    <c:forEach var="investment" items="${investments}">
                        <div class="col-md-4 mb-3">
                            <div class="card cards-contents h-100">
                                <div class="card-body">
                                    <h5 class="card-title">${investment.investmentType}</h5>
                                    <p class="card-text"><strong>Valor:</strong> R$ ${investment.amount}</p>
                                    <p class="card-text">
                                        <strong>Recorrente:</strong>
                                        <c:choose>
                                            <c:when test="${investment.isRecurring == 'Y'}">Sim</c:when>
                                            <c:otherwise>Não</c:otherwise>
                                        </c:choose>
                                    </p>

                                    <div class="card-footer d-flex justify-content-between">
                                        <input type="hidden" name="id" value="${investment.investmentId}"/>
                                        <button
                                                type="button"
                                                class="btn btn-outline-secondary btn-sm"
                                                data-bs-toggle="modal"
                                                data-bs-target="#modalEditarInvestment"
                                                data-id="${investment.investmentId}"
                                                data-investmentamount="${investment.amount}"
                                                data-isrecurring="${investment.isRecurring}">
                                            Editar
                                        </button>
                                        <form method="post" action="delete-investment"
                                              onsubmit="return confirm('Tem certeza que deseja excluir este investimento?');">
                                            <input type="hidden" name="id" value="${investment.investmentId}"/>
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

<div class="modal fade" id="modalEditarInvestment" tabindex="-1" aria-labelledby="modalEditarInvestmentLabel" aria-hidden="true">
    <div class="modal-dialog modal-actions">
        <div class="modal-content modal-content-actions">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalEditarInvestmentLabel">Editar Investimento</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body">
                <form id="formEditarInvestment" action="edit-investment" method="POST">
                    <input type="hidden" name="investmentId" id="investmentId">

                    <div class="mb-3">
                        <label for="editInvestmentType" class="form-label">Tipo de Investimento</label>
                        <select id="editInvestmentType" name="investmentType" class="form-control" required>
                            <option value="" hidden selected>Selecione</option>
                            <option value="LCA">LCA</option>
                            <option value="CDB">CDB</option>
                            <option value="LCI">LCI</option>
                            <option value="TESOURO">TESOURO</option>
                            <option value="ACTION">AÇÃO</option>
                            <option value="CRIPTO">CRIPTO MOEDAS</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="editAmount" class="form-label">Valor Investido</label>
                        <input type="number" class="form-control" id="editAmount" name="amount" step="0.01" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">É recorrente?</label><br>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="isRecurring" id="editRecurringYes" value="Y">
                            <label class="form-check-label" for="editRecurringYes">Sim</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="isRecurring" id="editRecurringNo" value="N">
                            <label class="form-check-label" for="editRecurringNo">Não</label>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-success">Salvar Alterações</button>
                </form>
            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="modalInvestimento" tabindex="-1" aria-labelledby="modalInvestimentoLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-actions">
        <div class="modal-content modal-content-actions">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalInvestimentoLabel">Adicionar Investimento</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body">
                <form id="formInvestimento" method="POST" action="investments">
                    <div class="mb-3">
                        <label for="nomeInvestimento" class="form-label">Tipo de investimento</label>
                        <select id="nomeInvestimento" name="investmentType" class="form-control">
                            LCA, CDB, LCI, TESOURO, ACTION, CRIPTO
                            <option value="" hidden selected>Selecione</option>
                            <option value="LCA">LCA</option>
                            <option value="CDB">CDB</option>
                            <option value="LCI">LCI</option>
                            <option value="TESOURO">TESOURO</option>
                            <option value="ACTION">AÇÃO</option>
                            <option value="CRIPTO">CRIPTO MOEDAS</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="valorInvestimento" class="form-label">Valor Investido</label>
                        <input type="number" class="form-control" id="valorInvestimento" step="0.01"
                               placeholder="Ex: 1000.00" name="amount" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">É recorrente?</label>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="isRecurring" id="recurringYes" value="Y">
                            <label class="form-check-label" for="recurringYes">Sim</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="isRecurring" id="recurringNo" value="N">
                            <label class="form-check-label" for="recurringNo">Não</label>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">Adicionar Investimento</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    const modalEditarInvestment = document.getElementById('modalEditarInvestment');
    modalEditarInvestment.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;

        const investmentId = button.getAttribute('data-id');
        const amount = button.getAttribute('data-investmentamount');
        const isRecurring = button.getAttribute('data-isrecurring');
        const investmentType = button.closest('.card-body').querySelector('.card-title').textContent.trim();

        document.getElementById('investmentId').value = investmentId;
        document.getElementById('editAmount').value = amount;

        const investmentTypeSelect = document.getElementById('editInvestmentType');
        for (let i = 0; i < investmentTypeSelect.options.length; i++) {
            if (investmentTypeSelect.options[i].value === investmentType) {
                investmentTypeSelect.selectedIndex = i;
                break;
            }
        }

        if (isRecurring === 'Y') {
            document.getElementById('editRecurringYes').checked = true;
        } else {
            document.getElementById('editRecurringNo').checked = true;
        }
    });
</script>


<%@include file="components/bottom.jsp" %>
