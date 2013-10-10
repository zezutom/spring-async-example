(function() {
	var self;
	function QuoteManager() {self = this;}
		
	QuoteManager.prototype = {
		pollId: null,		
		start: function() {
			pollId = setInterval(this.poll, 3000);
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
		        	self.updateQuotes(eval(xmlhttp.responseText));		        	
		        }
		    }

		    xmlhttp.open("GET", "quotes/get", true);
		    xmlhttp.setRequestHeader('Accept', 'application/json');
		    xmlhttp.send();			
		},
		updateQuotes: function(quotes) {
			var table = document.getElementsByTagName('table')[0];
			
			var updateCell = function(cell, value) {
				cell.innerHTML = value;
			};
			
			var updateRow = function(quote) {
				var props = ['Symbol', 'BidRealtime', 'AskRealtime', 'ChangeRealtime', 'LastUpdateTime'];
				var cols = row.getElementsByTagName('td');
				
				for (var c in cols) 
					updateCell(cols[c], quote[props[c]]);
			}; 

			var insertRow = function(quote) {					
				row = document.createElement('tr');
				row.setAttribute('id', quote.Symbol);
				
				for (var i=0; i<4; i++) 
					row.appendChild(document.createElement('td'));
				updateRow(quote);		
				table.appendChild(row);
			};
						
			quotes.forEach(function(quote) {
				var row = document.getElementById(quote.Symbol);
				
				if (row) 
					updateRow(quote);
				else 
					insertRow(quote);				
			});
		}
	};	
	window.quoteManager = new QuoteManager();
})();

window.onload = function() {
	window.quoteManager.start();
}