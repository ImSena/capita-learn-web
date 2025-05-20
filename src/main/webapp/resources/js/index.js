$(document).ready(function(){
    $("#historico").on("click", function () {
        $("#historico.overview-content").addClass("active");
        $("#relatorio.overview-content").removeClass("active");
        $("#historico").addClass("active");
        $("#relatorio").removeClass("active");
    });

    $("#relatorio").on("click", function () {
        $("#relatorio.overview-content").addClass("active");
        $("#historico.overview-content").removeClass("active");

        $("#relatorio").addClass("active");
        $("#historico").removeClass("active");
    });

    const $toggleArea = $("#amount")
    const $toggleButton = $("#amount .fa-eye-slash");
    const $valueTotal = $("#value_total");
    const $toggleText = $("#amount span:first");

    let isHidden = false;

    $toggleArea.on("click", function () {
        if (isHidden) {
            $valueTotal.text("R$ 5036,13");
            $toggleButton.removeClass("fa-eye").addClass("fa-eye-slash");
            $toggleText.text("Ocultar Saldo");
            isHidden = false;
        } else {
            $valueTotal.text("********");
            $toggleButton.removeClass("fa-eye-slash").addClass("fa-eye");
            $toggleText.text("Mostrar Saldo");
            isHidden = true;
        }
    });

})