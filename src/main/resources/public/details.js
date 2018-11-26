function fetchDetails() {
  var parsedUrl = new URL(window.location.href);
  var readingId = parsedUrl.searchParams.get("id");
  var url = 'http://localhost:8080/weather/'+readingId;
	var request = new Request(url);
	fetch(request).then(function(response){
		if(response.ok){
			return response.json();
		}
		else{
			return Promise.reject(new Error(response.statusText));
		}
	}).then(function(response){
		var tableBody = document.getElementById("detailsTableBody");
		
		var tr = document.createElement('TR');
	    var td1 = document.createElement('TD');
	    var td2 = document.createElement('TD');
	    var td3 = document.createElement('TD');
	    var td4 = document.createElement('TD');
	    var td5 = document.createElement('TD');
	    var td6 = document.createElement('TD');

	    td2.setAttribute("align", "center");
	    td3.setAttribute("align", "center");
        td4.setAttribute("align", "center");
        td5.setAttribute("align", "center");
        td6.setAttribute("align", "center");
        
        var readingDate = new Date(response.date).toDateString();   
        var meanTemp = response.meanTemp;
        if (meanTemp == null) {
        	meanTemp = 'N/A';
        }
        var highestTemp = response.monthlyHighestTemp;
        if (highestTemp == null) {
        	highestTemp = 'N/A';
        }
        var lowestTemp = response.monthlyLowestTemp;
        if (lowestTemp == null) {
        	lowestTemp = 'N/A';
        }
	    
        td1.appendChild(document.createTextNode(response.stationName));
        td2.appendChild(document.createTextNode(response.province));
        td3.appendChild(document.createTextNode(readingDate));
        td4.appendChild(document.createTextNode(meanTemp));
        td5.appendChild(document.createTextNode(highestTemp));
        td6.appendChild(document.createTextNode(lowestTemp));
        
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tableBody.appendChild(tr);
	}).catch(function(err){
		alert(err);  
		document.getElementById("detailsTableBody").innerHTML = "";
	});
}