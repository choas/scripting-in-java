package kata.range.java;

import kata.range.Range;

public class JavaRange implements Range {

	private int start;
	private int end;

	private void range(String rangeString) {
		int p = rangeString.indexOf(',');
		start = Integer.valueOf(rangeString.substring(1, p));
		boolean isStartIncluded = rangeString.substring(0, 1).equals("[");
		if (!isStartIncluded) {
			start++;
		}
		end = Integer.valueOf(rangeString.substring(p + 1, rangeString.length() - 1));
		boolean isEndIncluded = rangeString.substring(rangeString.length() - 1).equals("]");
		if (isEndIncluded) {
			end++;
		}
	}

	@Override
	public boolean contains(String rangeString, int point) {
		range(rangeString);
		return (point >= start && point < end);
	}

	@Override
	public boolean containsRange(String rangeString1, String rangeString2) {
		for (int point : allPoints(rangeString2)) {
			if (!contains(rangeString1, point)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int[] allPoints(String rangeString) {
		range(rangeString);

		int[] points = new int[end - start];
		for (int i = start; i < end; i++) {
			points[i - start] = i;
		}

		return points;
	}

	@Override
	public boolean overlaps(String rangeString1, String rangeString2) {
		for (int point : allPoints(rangeString2)) {
			if (contains(rangeString1, point)) {
				return true;
			}
		}
		return false;
	}
}
