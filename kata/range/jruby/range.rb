class Range

	attr_accessor :start, :end

	def initialize(range)
		c = range.index ","
		@start = range[1..c].to_i
		if range[0] != "["
			@start = @start + 1
		end
		@end = range[c+1..range.length-2].to_i
		if range[range.length-1] != "]"
			@end = @end - 1
		end
	end

	def contains (point)
		point >= @start && point <= @end
	end
	
	def allPoints ()
		(Array.new(@end - @start+1) { |point| point + @start }).to_java Java::int
	end

	def containsRange (range)
		cc = true
		Range.new(range).allPoints.each { |point| cc = cc && contains(point) }
		cc
	end

	def overlaps (range)
		Range.new(range).start <= @end
	end
end



def contains (range, point)
	Range.new(range).contains point
end

def allPoints (range)
	Range.new(range).allPoints
end

def containsRange (range1, range2)
	Range.new(range1).containsRange range2
end

def overlaps (range1, range2)
	Range.new(range1).overlaps range2
end
