<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav>
        <div>
            <button class="button_menu btn">
                <i class="fa-regular fa-user"></i>
            </button>
        </div>

        <div>
            <button class="button_menu btn button_canva" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample"
                    aria-controls="offcanvasExample">
                <i class="fa-solid fa-bars"></i>
            </button>
        </div>
    </nav>

    <div class="search_content">
        <label for="search">
            <i class="fa-solid fa-magnifying-glass"></i>
        </label>
        <input type="search" class="field" name="pesquisa" id="search" placeholder="Pesquisar">
    </div>

    <div id="amount">
        <div>
            <i class="fa-solid fa-eye-slash"></i>
            <span>Ocultar Saldo</span>
            <i class="fa-solid fa-chevron-down"></i>
        </div>

        <div>
            <span id="value_total">R$ 5036,13</span>
        </div>
    </div>
</header>