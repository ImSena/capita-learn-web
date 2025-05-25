<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample"
     aria-labelledby="offcanvasExampleLabel">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title" id="offcanvasExampleLabel">CapitaLearn</h5>
    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"
            style="background-color: #fff !important;"></button>
  </div>
  <div class="offcanvas-body">
    <nav>
      <ul>
        <li><i class="fa-solid fa-house"></i><a href="dashboard" class="link-menu">Página Inicial</a></li>
        <li><i class="fa-solid fa-user"></i><a href="perfil" class="link-menu">Perfil</a></li>
        <li><i class="fa-solid fa-cog"></i><a href="config" class="link-menu">Configurações</a></li>
        <li><i class="fa-solid fa-chart-line"></i><a href="exit" class="link-menu">Sair</a></li>
      </ul>
    </nav>
  </div>

  <div class="offcanvas-footer">
    &copy; Todos os direitos reservados
  </div>
</div>
