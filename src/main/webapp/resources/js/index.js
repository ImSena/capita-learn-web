$(document).ready(function(){
    $("#historico").on("click", function () {
        $(".overview-content").removeClass("active");
        $("#historico.overview-content").addClass("active");

        $("#historico").addClass("active");
        $("#relatorio").removeClass("active");
    });

    $("#relatorio").on("click", function () {
        $(".overview-content").removeClass("active");
        $("#relatorio.overview-content").addClass("active");

        $("#relatorio").addClass("active");
        $("#historico").removeClass("active");
    });

    const $valueTotal = $("#value_total");
    const originalText = $valueTotal.text();

    const $toggleArea = $("#amount");
    const $toggleButton = $("#amount .fa-eye-slash, #amount .fa-eye");
    const $toggleText = $("#amount span:first");

    let isHidden = false;

    $toggleArea.on("click", function () {
        if (isHidden) {
            $valueTotal.text(originalText);
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
});
