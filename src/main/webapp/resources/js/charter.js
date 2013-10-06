(function () {
	function Charter() {}
	
	Charter.prototype = {
		init: function() {
			var container = document.getElementById("container");
			container.setAttribute("id", "container");
			container.appendChild(this._svg());
		},
		_svg: function() {
			var svg = document.createElement("svg");
			svg.className = "bullet";
			svg.setAttribute("width", "100%");
			svg.setAttribute("height", "50");			
			svg.appendChild(this._lineCPUsage());			
			return svg;
		},
		_lineCPUsage: function() {
			var g = document.createElement("g");
			g.setAttribute("transform", "translate(120,5)");
			g.appendChild(this._rect("range s0", 510, 25));
			g.appendChild(this._rect("range s1", 300, 25));
			g.appendChild(this._rect("range s2", 400, 25));
			g.appendChild(this._rect("measure s0", 500, 8.5, 8.5));
			g.appendChild(this._rect("measure s1", 300, 8.5, 8.5));
			g.appendChild(this._line("marker", 666.6666666666667, 666.6666666666667, 4.166666666666667, 20.833333333333332));
			
			for (var i=0; i<=100; i+=10) {
				g.appendChild(this._tick(i * 5, i));
			}
			g.appendChild(this._legend("CPU Load", "percentage"));
			return g;
		},
		_rect: function(cssClass, width, height, y) {
			var rect = document.createElement("rect");
			rect.className = cssClass;
			rect.setAttribute("width", width);
			rect.setAttribute("height", height);
			rect.setAttribute("x", 0);
			if (y) rect.setAttribute("y", y);			
			return rect;
		},
		_line: function(cssClass, x1, x2, y1, y2) {
			var line = document.createElement("line");
			if (cssClass) line.className = cssClass;
			if (x1) line.setAttribute("x1", x1);
			if (x2) line.setAttribute("x2", x2);
			if (y1) line.setAttribute("y1", y1);
			if (y2) line.setAttribute("y2", y2);			
			return line;
		},
		_tick: function(x, caption) {
			var tick = document.createElement("g");
			tick.className = "tick";
			tick.setAttribute("transform", "translate(" + x + ",0)");
			tick.setAttribute("style", "opacity: 1;");
			tick.appendChild(this._line(null, null, null, 25, 29.166666666666668));
			
			var text = document.createElement("text");
			text.setAttribute("text-anchor", "middle");
			text.setAttribute("dy", "1em");
			text.setAttribute("y", "29.166666666666668")
			text.innerHTML = caption;			
			tick.appendChild(text);
			return tick;
		},
		_legend: function(title, subtitle) {
			var g = document.createElement("g");
			g.setAttribute("transform", "translate(-6,12.5)");
			g.setAttribute("style", "text-anchor: end;");
			
			var titleText = document.createElement("text");
			titleText.className = "title";
			titleText.innerHTML = title;
			
			var subtitleText = document.createElement("text");
			subtitleText.setAttribute("dy", "1em");
			subtitleText.className = "subtitle";
			subtitleText.innerHTML = subtitle;
			
			g.appendChild(titleText);
			g.appendChild(subtitleText)
			return g;
		}
	};
	
	window.charter = new Charter();
})();