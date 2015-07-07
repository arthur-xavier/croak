// js/croaks.js
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
    return '<a href="/search?q=' + encodeURIComponent(hashtag) + '">' + hashtag + '</a>';
  }).replace(/(@\w+)/g, function(_, user) {
    return '<a href="/' + user + '">' + user + '</a>';
  });
};

var getCroak = function(croak) {
  var c = (croak.big === true ? "col s12 m8 big" : "col s6 l4");
  if(croak.giga === true) c = "col s12 big";
  return '<div class="croak ' + c + '">' +
         '<article data-id="' + croak.id + '" class="card-panel z-depth-1-half" style="background-color: ' + (croak.color || randomColor()) + '">' +
         '<p>' + formatCroak($('<div/>').text(croak.text).html()) + '</p>' +
         '<div class="author">' +
         '<div class="left circle avatar" style="background-image: url(' + croak.author.avatar + ')"></div>' +
         '<a href="/@' + croak.author.username + '" class="right tooltipped" data-tooltip="@' + croak.author.username + '">&mdash; ' + croak.author.firstName + ' ' + croak.author.lastName + '</a>' +
         '</article>' +
         '</div>';
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

    $elem.find('.card-panel').click(function() {
      window.location.href = "/croak/" + $(this).data('id');
    });

    return $elem;
  };

  // load croaks
  var loadCroaks = function(query) {
    if(query.split('/').length > 1) {
      escapedQuery = encodeURIComponent(query.split('/').slice(1).join('/'));
      query = query.split('/')[0] + '/' + escapedQuery;
    }
    $.ajax({
      url: '/rest/croak/' + query,
      contentType: 'application/json',
      method: 'GET',
      beforeSend: function() {
        $('#croak-preloader').removeClass('hide');
      }
    }).done(function(croaks) {
      $('#croak-preloader').addClass('hide');
      $croaks.html('');
      if(croaks.forEach && croaks.length > 0) {
        croaks[croaks.length - 1].big = true;
        croaks.forEach(createCroak);
      } else if(croaks.text) {
        croaks.giga = true;
        createCroak(croaks);
      } else {
        query = decodeURIComponent(query.split('/').slice(1).join('/'));
        $croaks.html('<h5 style="text-align: center">No croak for <strong>' + query + '</strong> not found.</h5>')
      }
    }).error(function() {
      query = decodeURIComponent(query.split('/').slice(1).join('/'));
      $croaks.html('<h5 style="text-align: center">No croak for <strong>' + query + '</strong> found.</h5>')
    });;
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
          id: 1,
          username: "mustermann",
          firstName: "Max", lastName: "Mustermann",
          avatar: "/img/mustermann.jpg"
        }
      };

      // send POST request
      $.ajax({
        url: '/rest/croak',
        contentType: 'application/json',
        method: 'POST',
        data: JSON.stringify(croak),
        dataType: 'json',
        beforeSend: function() {
          $('#croak-modal .modal-content, #croak-modal .modal-footer').addClass('hide');
          $('#croak-modal-preloader').removeClass('hide');
        }
      }).done(function() {
        $('#croak-modal-preloader').addClass('hide');
        $("#croak-modal").closeModal();
        window.location.href = "/";
      });
    }
  });

  // populate croaks at page load
  if($('load-croaks').length == 1) {
    loadCroaks($('load-croaks').data('query'));
  }
});
