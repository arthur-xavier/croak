// js/home.js

jQuery(function($) {

$(document).ready(function() {

  // tabs
  $('#nav-mobile a').click(function() {
    window.location.href = $(this).attr('href');
  });

  // invitations
  $('span.toast').each(function() {
    console.log($(this).html());
    Materialize.toast($(this).html(), 30000);
  });
  $('div.toast > a.btn-flat').click(function() {
    var $toast = $(this).parent();
    $.ajax({
      url: '/rest/invitation/' + $(this).data('id'),
      method: 'DELETE',
      contentType: 'application/json',
      success: function(data) {
        $toast.remove();
      }
    });
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
