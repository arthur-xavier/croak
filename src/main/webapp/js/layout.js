// js/home.js

jQuery(function($) {

$(document).ready(function() {

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

  $('.link').click(function() {
    window.location.href = $(this).data('href');
  });

  $('nav input#search').blur(function() {
    $('#search-form').toggleClass('hide');
  });
});

});
