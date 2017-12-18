function loadTweets(url) {
    
    var request = new XMLHttpRequest();
    
    request.onload = function () {
    
        document.getElementById('tweets').textContent = this.responseText;
    };
    
    request.open('GET', url);
    request.send();
}
loadTweets('https://trump-vs-cnn.000webhostapp.com/data/trumpTweets.txt');