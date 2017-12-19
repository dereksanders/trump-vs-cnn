var tweetsJSON;

$.getJSON('data/trumpTweets.json', function(json){
    tweetsJSON = json;
})

var tweetsDisplay = JSON.stringify(tweetsJSON, null, 2);

document.getElementById('tweets').textContent = tweetsDisplay;