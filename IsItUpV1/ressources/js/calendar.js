
var day = d3.time.format("%w");
var week = d3.time.format("%U");
var format = d3.time.format("%Y-%m-%d");
var format2 = d3.time.format("%Y-%m-%d");
var cellSize = $("#calendrier").width()/60;       

var color = d3.scale.quantize()
.domain([0, 1])
.range(d3.range(100).map(function(d) {console.log(d); colorInverse = 100-d; return "q" + colorInverse + "-11"; }));


width = $("#calendrier").width(),
height = width/7;

var d = new Date();
var svg = d3.select("#calendrier").selectAll("svgChart")
.data(d3.range(d.getFullYear(),d.getFullYear()+1))
.enter().append("svg")
.attr("width", width)
.attr("height", height)
.attr("class", "RdYlGn")
.append("g")
.attr("transform", "translate(" + ((width - cellSize * 53) / 2) + "," + (height - cellSize * 7 - 1) + ")");


svg.append("text")
.attr("transform", "translate(-6," + cellSize * 3.5 + ")rotate(-90)")
.style("text-anchor", "middle")
.text(function(d) {return d; });
svg.append("text")
.style("text-anchor", "middle");


var rect = svg.selectAll(".day")
.data(function(d) { return d3.time.days(new Date(d, 0, 1), new Date(d + 1, 0, 1)); })
.enter().append("rect")
.attr("class", "day")
.attr("width", cellSize)
.attr("height", cellSize)
.attr("x", function(d) {x=week(d) * cellSize; return x;})
.attr("y", function(d) {y=day(d) * cellSize; return y;})
.datum(format2);

rect.append("title")
.text(function(d) { return d; });

svg.selectAll(".month")
.data(function(d) { return d3.time.months(new Date(d, 0, 1), new Date(d + 1, 0, 1)); })
.enter().append("path")
.attr("class", "month")
.attr("d", monthPath);



var nesteddata = d3.nest()
.key(function(d) { 
	return format2(format.parse(d.date)); 
})
.rollup(function(d) {return d[0].NbComputerOn/d[0].TotalComputers; })
.map(data);

//{"2011-04-16T11:20:39Z":0,"2011-04-16T11:22:17Z":1,"2011-04-16T11:24:31Z":1,"2011-04-16T11:25:54Z":0,"2011-04-16T11:26:56Z":0,"2011-04-16T11:27:54Z":0,"2011-04-16T11:29:05Z":1,"2011-04-16T11:31:17Z":1,"2011-04-16T11:34:03Z":0,"2011-04-16T11:37:33Z":0}
//console.log('nested', JSON.stringify(nesteddata));

rect.filter(function(d) {  return d in nesteddata; })
.attr("class", function(d) { return "day " + color(nesteddata[d]); })
.select("title")
.text(function(d) {  return d + ": " +(nesteddata[d]*100).toFixed(2)+" %";  });


function monthPath(t0) {
	var t1 = new Date(t0.getFullYear(), t0.getMonth() + 1, 0),
	d0 = +day(t0), w0 = +week(t0),
	d1 = +day(t1), w1 = +week(t1);
	return "M" + (w0 + 1) * cellSize + "," + d0 * cellSize
	+ "H" + w0 * cellSize + "V" + 7 * cellSize
	+ "H" + w1 * cellSize + "V" + (d1 + 1) * cellSize
	+ "H" + (w1 + 1) * cellSize + "V" + 0
	+ "H" + (w0 + 1) * cellSize + "Z";
}

d3.select(self.frameElement).style("height", "2910px"); 