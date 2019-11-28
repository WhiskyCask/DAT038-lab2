import java.util.Comparator;

public class RangeBinarySearch {
    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    // Complexity: O(log N), where N is the length of the array
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator comparator) {
        if (a == null) throw new NullPointerException();
        if (key == null) throw new NullPointerException();
        if (comparator == null) throw new NullPointerException();

        int first = 0;
        int last = a.length - 1;
        int middle;
        int result;

        while (first < last) {
            middle = (first + last) / 2;
            result = comparator.compare(a[middle], key);
            if (result < 0) first = middle + 1;
            else last = middle;
        }

        if (comparator.compare(a[first], key) == 0) return first;

        return -1;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    // Complexity: O(log N)
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator comparator) {
        if (a == null) throw new NullPointerException();
        if (key == null) throw new NullPointerException();
        if (comparator == null) throw new NullPointerException();

        int first = 0;
        int last = a.length - 1;
        int middle;
        int result;

        while (first < last) {
            middle = (first + last) / 2;
            result = comparator.compare(a[middle], key);
            if (result > 0) last = middle;
            else first = middle + 1;
        }

        if (comparator.compare(a[first - 1], key) == 0) return first - 1;

        return -1;
    }
}