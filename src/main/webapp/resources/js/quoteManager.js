(function() {
	var self;
	function QuoteManager() {self = this;}
		
	QuoteManager.prototype = {
		pollId: null,
		pollMillis: 3000,
		pollStatus: 'off',
		fields: ['Symbol', 'BidRealtime', 'AskRealtime', 'ChangeRealtime', 'LastTradeTime'],
		start: function() {
			// register content listeners
			var cells = document.getElementsByClassName('highlight');
			for (var i = 0; i < cells.length; i++) 
				cells[i].addEventListener('cellChange', this.onCellChange, false);
			
			// ask for immediate results
			this.poll();
			
			// schedule subsequent polls
			pollId = setInterval(this.poll, self.pollMillis);
			
			// change poll status
			this.pollStatus = 'on';
		},
		stop: function() {
			clearInterval(pollId);
			this.pollStatus = 'off';
		},
		onCellChange: function(e) {
			if (/^.* highlight$/g.test(this.className)) {
				var cell = this;
				var animateClass = ' animate';
				cell.className += animateClass;
				setTimeout(function() {
					cell.className = cell.className.replace(animateClass, '');}, 
					self.pollMillis - 100);
			}
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
			var table = document.getElementsByTagName('tbody')[0];
								
			var updateRow = function(row, quote) {
				var cells = row.getElementsByClassName('cell');
								
				for (var i = 0; i < cells.length; i++) {
					var cell = cells[i];
					cell.innerHTML = quote[self.fields[i]];
					cell.dispatchEvent(new CustomEvent('cellChange'));
				} 										
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
	var quoteMgr = window.quoteManager;
	document.getElementById('pollButton').addEventListener('click', function() {
		var caption = 'Start';
		if (quoteMgr.pollStatus === 'off') {
			quoteMgr.start();
			caption = 'Stop';
		} 									
		else 
			quoteMgr.stop();
		
		this.innerHTML = caption;
	}, false);
}