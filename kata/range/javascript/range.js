// uses underscore.js
// http://underscorejs.org/
//

String.prototype.points = function(points) {
    return function() {
		vs = this.substring(1, this.length - 1).split(',')
		start = parseInt(vs[0]) + (this.substring(0,1) === '[' ? 0 : 1)
		end = parseInt(vs[1]) + (this.substring(this.length - 1) === ']' ? 1 : 0)
		return _.range(start, end)
    }
  }(String.prototype.points);


function contains(range, point) {
	return _.contains(range.points(), point)
}

function allPoints(range) {
	jsPoints = range.points()
	javaPoints = java.lang.reflect.Array.newInstance(java.lang.Integer.TYPE, jsPoints.length)
	for (i = 0; i < jsPoints.length; i++) { 
		javaPoints[i] = jsPoints[i]
	}
	return javaPoints
}

function containsRange(range1, range2) {
	return _.every(range2.points(), 
		function(point2) {
			return contains(range1, point2)
		})
}

function overlaps(range1, range2) {
	return _.some(range2.points(), 
		function(point2) {
			return contains(range1, point2)
		})
}
