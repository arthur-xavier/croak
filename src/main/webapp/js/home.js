// js/home.js
var colors = [
  '#ffffff', '#ffebee', '#fce4ec', '#f3e5f5',
  '#ede7f6', '#e8eaf6', '#e3f2fd', '#e1f5fe',
  '#e0f7fa', '#e0f2f1', '#e8f5e9', '#f1f8e9',
  '#f9fbe7', '#fffde7', '#fff8e1', '#fff3e0',
  '#fbe9e7', '#efebe9', '#fafafa', '#eeeeee',
  '#eceff1'
];

var randomColor = function() { return colors[Math.floor(colors.length * Math.random())]; };

var croaks = [
  { text: "on some real young trill shit doe. I miss y'all. #Hooligans #Love #Peace #TacoBell #GameOfThrones #Hashtag #HashBrowns",
    avatar: "img/jackie-chan.jpg",
    color: "#fafafa",
    author: "Mr. Nice Guy",
    user: "EyeOfJackieChan" },
  { text: "Hey you! Out there in the cold, getting lonely getting old, can you feel me?",
    avatar: "img/roger-waters.jpg",
    color: "#fffde7",
    author: "Roger Waters",
    user: "rogerwaters" },
  { text: "Here's what I said yesterday: We are blessed with the most beautiful God-given landscape in the entire world... We have to be good stewards for it.",
    avatar: "img/barack-obama.jpg",
    color: "#f3e5f5",
    author: "Barack Obama",
    user: "BarackObama" },
  { text: "I think I'm addicted to #croaking #cantstop",
    avatar: "img/bill-gates.jpg",
    color: "#e1f5fe",
    author: "Bill Gates",
    user: "BillGates" },
  { text: "Video fast fertig geschnitten & internet hier ist grad nice, kommt also gleich online :)",
    avatar: "img/simon-unge.jpg",
    color: "#f1f8e9",
    author: "Simon Unge",
    user: "unge" },
  { big: true,
    text: "Did you know that croaking is awesome? I really think that you all should try it! #croaking #awesome",
    avatar: "img/jack-dawson.jpg",
    color: "#fff3e0",
    author: "Jack Dawson",
    user: "JackDawson_pa" }
];

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
         '<div class="left circle avatar" style="background-image: url(' + croak.avatar + ')"></div>' +
         '<a href="/@' + croak.user + '" class="right tooltipped" data-tooltip="@' + croak.user + '">&mdash; ' + croak.author + '</a>' +
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

  // populate croaks
  croaks.forEach(createCroak);

  // croak form submit
  $("#croak-form").submit(function(e) {

    e.preventDefault();
    // invalid croak
    if($("#textarea").val().length > 150) {
      alert("Your croak can have maximal 150 characters.");
    } else {
      // spawn croak
      createCroak({
        big: true,
        text: $("#textarea").val(),
        color: $("#croak-modal").css('background-color'),
        avatar: 'img/mustermann.jpg', 
        author: 'Max Mustermann',
        user: 'mustermann'
      });
      $("#croak-modal").closeModal();
    }
  });

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