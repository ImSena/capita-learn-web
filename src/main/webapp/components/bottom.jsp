<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 19/05/2025
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
<script>
    document.getElementById("btnRecorrentes").addEventListener("click", function () {
        window.location.href = "<%= request.getContextPath() %>/recurrents";
    });

    document.getElementById("btnMetas").addEventListener("click", function () {
        window.location.href = "<%= request.getContextPath() %>/goals";
    });

    document.getElementById("btnAplicacoes").addEventListener("click", function () {
        window.location.href = "<%= request.getContextPath() %>/investments";
    });
</script>
</body>

</html>
