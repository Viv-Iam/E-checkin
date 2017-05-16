$(document).ready(function myFunction() {
  $("#button1").click(function (e) {
    $("span", this).text("Checked In");
    $("#button1").attr("disabled", true); 
});
});
