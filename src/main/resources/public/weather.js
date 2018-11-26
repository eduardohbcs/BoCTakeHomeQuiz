Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    return local.toJSON().slice(0,10);
});
document.getElementById("endRange").value = new Date().toDateInputValue();
document.getElementById("search").addEventListener('click',searchWeatherReadings);

function searchWeatherReadings() {
	var start = new Date(document.getElementById("startRange").value);
	var end = new Date(document.getElementById("endRange").value);
	// validate range
	if (end < start) {
		alert("End date must be greater than start");
		return;
	}
	
	// build request URL
	var url = 'http://localhost:8080/weather?start='+start.getTime()+'&end='+end.getTime();
	var request = new Request(url);
	fetch(request).then(function(response){
		if(response.ok){
			return response.json();
		}
		else{
			return Promise.reject(new Error(response.statusText));
		}
	}).then(function(response){
		showResults(response);
	}).catch(function(err){
		alert(err);  
		document.getElementById("readingsTable").innerHTML = "";
	});
}

function showResults(results) {
	// clear table results
	var tableBody = document.getElementById("readingsTableBody");
    //reset table
    while (tableBody.hasChildNodes()) {
    	tableBody.removeChild(tableBody.firstChild);
    }
	// add new rows for each result (with link for mean temp)
    for (i = 0; i < results.length; i++) {
        var tr = document.createElement('TR');
        var td1 = document.createElement('TD');
        var td2 = document.createElement('TD');
        var td3 = document.createElement('TD');

        td2.setAttribute("align", "center");
        td3.setAttribute("align", "center");
        
        var readingDate = new Date(results[i].date).toDateString();
        var tempReading = results[i].meanTemp;
        if (tempReading == null) {
        	tempReading = 'N/A';
        }
        
        var link = document.createElement("a");
        var url = 'details.html?id='+results[i].id;
        var linkText = document.createTextNode(tempReading);
        link.setAttribute("href",url);
        link.appendChild(linkText);
        
        td1.appendChild(document.createTextNode(results[i].stationName));
        td2.appendChild(document.createTextNode(readingDate));
        td3.appendChild(link);
        
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tableBody.appendChild(tr);
    }
}
