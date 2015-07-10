// js/users.js

jQuery(function($) {

var getUser = function(user) {
  return '<a href="/@' + user.username + '" class="collection-item avatar">' +
         '<div class="avatar circle" style="background-image: url(\'' + user.avatar + '\')"></div>' +
         '<span class="title">' + user.firstName + ' ' + user.lastName + '</span>' +
         '<p class="grey-text small">@' + user.username + '</p>' +
         '</a>';
};

$(document).ready(function() {

  var $users = $('#users .collection');

  var createUser = function(user) {
    var $elem = $(getUser(user));
    // append to users
    $users.append($elem);

    return $elem;
  };

  // load users
  var loadUsers = function(query) {
    $.ajax({
      url: '/rest/user/' + query,
      contentType: 'application/json',
      method: 'GET',
      beforeSend: function() {
        $('#user-preloader').removeClass('hide');
      }
    }).done(function(users) {
      $('#user-preloader').addClass('hide');
      if(users.forEach) {
        users.forEach(createUser);
      } else if(users.username) {
        createUser(users);
      } else {
        query = decodeURIComponent(query.split('/').slice(1).join('/'));
        $users.html('<h5 style="text-align: center">User <strong>' + query + '</strong> not found.</h5>')
      }
    }).error(function() {
      query = decodeURIComponent(query.split('/').slice(1).join('/'));
      $users.html('<h5 style="text-align: center">User <strong>' + query + '</strong> not found.</h5>')
    });
  };

  // populate users at page load
  if($('load-users').length == 1) {
    loadUsers($('load-users').data('query'));
  }
});

});
