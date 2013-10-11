(function() {
	var self;
	function QuoteManager() {self = this;}
		
	QuoteManager.prototype = {
		pollId: null,	
		fields: ['Symbol', 'BidRealtime', 'AskRealtime', 'ChangeRealtime', 'LastTradeTime'],
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
						
			var updateRow = function(row, quote) {
				var cols = row.getElementsByTagName('td');
				
				for (var c in cols)
					cols[c].innerHTML = quote[self.fields[c]];
			}; 

			var appendRow = function(id) {					
				row = document.createElement('tr');
				row.setAttribute('id', id);
				
				for (var i = 0; i < self.fields.length; i++)
					row.appendChild(document.createElement('td'));
				
				table.appendChild(row);
				return row;
			};
						
			quotes.forEach(function(quote) {
				var row = document.getElementById(quote.Symbol);
				
				if (!row) row = appendRow(quote.Symbol); 
				updateRow(row, quote);			
			});
		}
	};	
	window.quoteManager = new QuoteManager();
})();

window.onload = function() {
	window.quoteManager.start();
}