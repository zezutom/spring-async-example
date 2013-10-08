(function() {
	function QuoteManager() {}
		
	QuoteManager.prototype = {
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
		    	return;
		    }
		    
		    var xmlhttp = new XMLHttpRequest();
		    
		    xmlhttp.onreadystatechange = function() {
		        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		        	window.quoteManager.updateQuotes(eval(xmlhttp.responseText));		        	
		        }
		    }

		    xmlhttp.open("GET", "quotes/get", true);
		    xmlhttp.setRequestHeader('Accept', 'application/json');
		    xmlhttp.send();			
		},
		updateQuotes: function(quotes) {
			// TODO update table
			console.log(quotes);
		}
	};	
	window.quoteManager = new QuoteManager();
})();

window.onload = function() {
	window.quoteManager.start();
}