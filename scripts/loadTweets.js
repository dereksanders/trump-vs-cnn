httpGet('data/trumpTweets.json', (err, data)=>{
    let tweetsDisplay = JSON.parse(data);
    let parent = document.getElementById('tweets');
    
    if(!tweetsDisplay)
        throw "Could not retrieve tweets.";
    
    tweetsDisplay.tweets.map((tweet)=>{

        // Parent div for all tweets.
        let tweetDiv = document.createElement("div");
        tweetDiv.className = "tweet";
        
        // Fill in tweet data.
        let user = document.createElement("pre");
        user.innerHTML = tweet.user;
        let time = document.createElement("pre");
        time.innerHTML = tweet.time;
        let text = document.createElement("p");
        text.innerHTML = tweet.text;
        
        // Create a div for each tweet.
        tweetDiv.appendChild(user);
        tweetDiv.appendChild(time);
        tweetDiv.appendChild(text);
        
        parent.appendChild(tweetDiv);
    });
});


function httpGet(theUrl, cb)
{
    var xhr = new XMLHttpRequest();
    xhr.open("GET", theUrl, true);
    xhr.onload = function (e) {
        if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            cb(null, xhr.responseText);
        } else {
            cb(xhr.statusText, null);
        }
      }
    };
    
    xhr.onerror = function (e) {
        cb(xhr.statusText, null);
    };
    
    xhr.send(null);
}