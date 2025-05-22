<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="modalAdicionar" tabindex="-1" aria-labelledby="modalAdicionarLabel" aria-hidden="true">
  <div class="modal-dialog modal-actions">
    <div class="modal-content modal-content-actions">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="modalAdicionarLabel">Adicionar Dinheiro</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
      </div>
      <div class="modal-body">
        <form id="formAdicionar">
          <div class="mb-3">
            <input type="hidden" name="action" value="adicionar" />
            <label for="valorAdicionar" class="form-label">Valor</label>
            <input type="number" class="form-control" id="valorAdicionar" placeholder="Ex: 150.00"
                   required>
          </div>
          <div class="mb-3">
            <label for="title" class="form-label">Descrição</label>
            <input type="text" class="form-control" id="title" placeholder="Ex: Salário">
          </div>
          <div class="mb-3">
            <label for="descricaoAdicionar" class="form-label">Descrição</label>
            <input type="text" class="form-control" id="descricaoAdicionar" placeholder="Ex: Empresa X">
          </div>
          <input type="hidden" name="transactionType" value="ADD"/>
          <input type="hidden" name="category" value="NORMAL"/>

          <button type="submit" class="btn btn-success">Adicionar</button>
        </form>
      </div>
    </div>
  </div>
</div>