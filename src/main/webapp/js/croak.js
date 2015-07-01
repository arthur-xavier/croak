// js/croak.js
var colors = [
  '#ffffff', '#ffebee', '#fce4ec', '#f3e5f5',
  '#ede7f6', '#e8eaf6', '#e3f2fd', '#e1f5fe',
  '#e0f7fa', '#e0f2f1', '#e8f5e9', '#f1f8e9',
  '#f9fbe7', '#fffde7', '#fff8e1', '#fff3e0',
  '#fbe9e7', '#efebe9', '#fafafa', '#eeeeee',
  '#eceff1'
];

var randomColor = function() { return colors[Math.floor(colors.length * Math.random())]; };

var formatCroak = function(text) {
  return text.replace(/(#\w+)/g, function(_, hashtag) {
    return '<a href="/' + hashtag + '">' + hashtag + '</a>';
  }).replace(/(@\w+)/g, function(_, user) {
    return '<a href="/' + user + '">' + user + '</a>';
  });
};

var getCroak = function(croak) {
  var c = (croak.big === true ? "col s12 m8 big" : "col s6 l4");
  return '<div class="' + c + '"><article class="card-panel z-depth-1-half" style="background-color: ' + (croak.color || randomColor()) + '">' +
         '<p>' + formatCroak($('<div/>').text(croak.text).html()) + '</p>' +
         '<div class="author">' +
         '<div class="left circle avatar" style="background-image: url(' + croak.author.avatar + ')"></div>' +
         '<a href="/@' + croak.author.username + '" class="right tooltipped" data-tooltip="@' + croak.author.username + '">&mdash; ' + croak.author.firstName + ' ' + croak.author.lastName + '</a>' +
         '</div></article>';
};

$(document).ready(function() {

  // initialize packery
  var $croaks = $("#croaks").packery({ itemSelector: '.col', gutter: 0 });

  var createCroak = function(croak) {
    var $elem = $(getCroak(croak));
    // reduce big croak
    $('.big').attr("class", "col s6 l4");
    // prepend to croaks
    $croaks.prepend($elem);
    $croaks.packery('prepended', $elem.get());
    // tooltips
    $('.tooltipped').tooltip({delay: 50});
    return $elem;
  };

  // load croaks
  var loadCroaks = function(croak) {
    $.ajax({
      url: '/croak/rest/croak',
      contentType: 'application/json',
      method: 'GET',
      beforeSend: function() {
        $('#croak-preloader').removeClass('hide');
      }
    }).done(function(croaks) {
      $('#croak-preloader').addClass('hide');
      $croaks.html('');
      croaks.forEach(createCroak);
    });
  };

  // croak form submit
  $("#croak-form").submit(function(e) {
    e.preventDefault();

    // invalid croak
    if($("#textarea").val().length > 150) {
      alert("Your croak can have maximal 150 characters.");
    // valid croak
    } else {

      // create croak object
      var croak = {
        text: $('#textarea').val(),
        color: $('#croak-modal').css('background-color'),
        author: {
          username: "mustermann",
          firstName: "Max", lastName: "Mustermann",
          avatar: "img/mustermann.jpg"
        }
      };

      // send POST request
      $.ajax({
        url: '/croak/rest/croak',
        contentType: 'application/json',
        method: 'POST',
        data: JSON.stringify(croak),
        dataType: 'json',
        beforeSend: function() {
          $('#croak-modal .modal-content, #croak-modal .modal-footer').addClass('hide');
          $('#croak-modal-preloader').removeClass('hide');
        }
      }).done(function() {
        console.log("done!");
        $('#croak-modal-preloader').addClass('hide');
        $("#croak-modal").closeModal();
        window.location.href = "/croak/home";
      });
    }
  });

  // populate croaks at page load
  loadCroaks();
});
