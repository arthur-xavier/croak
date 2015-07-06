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

  // search form  
  $('a[data-toggle]').click(function(e) {
    e.preventDefault();
    e.stopPropagation();
    $('#' + $(this).data('toggle'))
    .toggleClass('hide')
    .find('input#search').focus();
  })

  $('nav input#search').blur(function() {
    $('#search-form').toggleClass('hide');
  });
});
