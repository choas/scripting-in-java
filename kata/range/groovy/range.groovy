

def r(range) {
	m = range =~ /(.)(\d+),(\d+)(.)/
	
	r = [	"start": m[0][2] as int, 
			"startIncluded": m[0][1] == "[",
	 		"end": m[0][3] as int, 
	 		"endIncluded": m[0][4] == "]"]
	 
	r+= ["start_": (r.startIncluded ? r.start : r.start+1)]
	r+= ["end_": (r.endIncluded ? r.end : r.end-1)]	 
}


def boolean contains(range, point) {
	r = r(range)
	
	(r.startIncluded && point >= r.start || 
		!r.startIncluded && point > r.start) && 
	(r.endIncluded && point <= r.end || 
		!r.endIncluded && point < r.end)
		
	//point >= r.start_ && point <= r.end_
}

def allPoints(range) {
	r = r(range)
	(int[])[* r.start_ .. r.end_ ]
}

def containsRange(range1, range2) {
	r1 = r(range1)
	r2 = r(range2)
	
	r2.start_ >= r1.start_ && r2.end_ <= r1.end_
}

def overlaps(range1, range2) {
	r1 = r(range1)
	r2 = r(range2)
	
	r2.start_ <= r1.end_
}
