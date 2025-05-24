<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="modalRetirar" tabindex="-1" aria-labelledby="modalRetirarLabel" aria-hidden="true">
    <div class="modal-dialog modal-actions">
        <div class="modal-content modal-content-actions">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalRetirarLabel">Retirar Dinheiro</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body">
                <form id="formRetirar" action="adicionar" method="POST">
                    <input type="hidden" name="action" value="adicionar" />
                    <div class="mb-3">
                        <label for="valorRetirar" class="form-label">Valor</label>
                        <input type="number" class="form-control" id="valorRetirar" placeholder="Ex: 50.00"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="descricaoRetirar" class="form-label">Descrição</label>
                        <input type="text" class="form-control" id="descricaoRetirar"
                               placeholder="Ex: Compra mercado">
                    </div>
                    <button type="submit" class="btn btn-danger">Retirar</button>
                </form>
            </div>
        </div>
    </div>
</div>