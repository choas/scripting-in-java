package kata.range.java;

public interface Range {

	public abstract boolean contains(String rangeString1, String rangeString2);

	public abstract boolean contains(String rangeString, int point);

	public abstract int[] allPoints(String rangeString);

	public abstract boolean overlaps(String rangeString1, String rangeString2);

}