$(document).ready(function myFunction() {
  $(".checkedin").hide();
  $(".button").click(function (e) {
    $(".checkedin", this).show();
    $(".checkin", this).hide();
});
});
