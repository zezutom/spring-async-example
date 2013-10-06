(function() {
	function DataPoller() {}
	
	DataPoller.prototype = {
		pollId: null,
		start: function() {
			pollId = setInterval(this.poll, 1000);
		},
		stop: function() {
			clearInterval(pollId);	
		},
		poll: function() {

		    if (!window.XMLHttpRequest) {
		    	console.error("Your browser doesn't support Ajax!");
		    }

		    var xmlhttp = new XMLHttpRequest();
		    
		    xmlhttp.onreadystatechange = function() {
		        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		        	// TODO push new data to the graph
		        	//window.chartDrawer.update(xmlhttp.responseText);
		        	console.log(xmlhttp.responseText);
		        }
		    }

		    xmlhttp.open("GET", "stats/get", true);
		    xmlhttp.send();			
		}
	};
	window.dataPoller = new DataPoller();
})();