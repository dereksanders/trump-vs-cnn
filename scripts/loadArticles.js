httpGet('data/trumpArticles.json', (err, data)=>{
    let articlesDisplay = JSON.parse(data);
    let parent = document.getElementById('articles');
    
    if(!articlesDisplay)
        throw "Could not retrieve articles.";
    
    articlesDisplay.articles.map((article)=>{

        // Parent div for all articles.
        let articleDiv = document.createElement("div");
        articleDiv.className = "article";
        
        // Fill in article data.
        let headline = document.createElement("p");
        headline.innerHTML = article.headline;
        let date = document.createElement("pre");
        date.innerHTML = article.date;
        let link = document.createElement("pre");
        link.innerHTML = article.link;
        
        // Create a div for each article.
        articleDiv.appendChild(headline);
        articleDiv.appendChild(date);
        articleDiv.appendChild(link);
        
        parent.appendChild(articleDiv);
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