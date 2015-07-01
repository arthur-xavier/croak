// js/home.js

$(document).ready(function() {
  // FAB click
  $("#fab").click(function() {
    $("#textarea").val('');
    $("#textarea").blur();
    $("#croak-modal").openModal();
    var color = randomColor();
    $("#croak-modal").css('background-color', color);
  });

  // tabs
  $('#nav-mobile a').click(function() {
    window.location.href = $(this).attr('href');
  });
});
