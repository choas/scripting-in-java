from jarray import zeros, array

class Range: 
	def __init__(self, range_):
		se = range_[1:-1].split(",")
		self.start = int(se[0])
		if (range_[0] == "("):
			self.start = self.start + 1
		self.end = int(se[1])
		if (range_[-1] == ")"):
			self.end = self.end - 1

def contains(range_, point):
	r = Range(range_)
	return point >= r.start and point <= r.end

def allPoints(range_):
	r = Range(range_)
	return array([i + r.start for i in range(r.end - r.start + 1)], 'i')
	
def containsRange(range1_, range2_):
    for point in allPoints(range2_):
        if not contains(range1_, point):
            return False
    return True

def overlaps(range1_, range2_):
	return Range(range2_).start <= Range(range1_).end
