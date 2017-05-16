$(document).ready(function myFunction() {
  $(".checkedin").hide();
  $(".button").click(function (e) {
    $(".checkedin", this).show();
    // $("#button1").attr("disabled", true);
    $(".checkin", this).hide();
});
});
