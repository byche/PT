$(document).ready(function(){
	var msg;

$.post("homepage",function(data,status){
				msg = data;
				$('#textcache').html(msg);

			});

 
});

function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getName()); // Do not send to your backend! Use an ID token instead.
	  var id_token = googleUser.getAuthResponse().id_token;
	  $('#userName').html(profile.getName());
	  
	  var xhr = new XMLHttpRequest();
	  xhr.open('POST', '/googleAuth');
	  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	  xhr.onload = function() {
	    console.log('Signed in as: ' + xhr.responseText);
	  };
	  xhr.send('idtoken=' + id_token);

	}

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }