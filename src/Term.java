import java.util.Comparator;

public class Term {

    private String query;
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null) throw new NullPointerException();
        if (weight < 0) throw new IllegalArgumentException();

        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in lexicographic order by query.
    public static Comparator<Term> byLexicographicOrder() {
        return new Comparator<>() {
            @Override
            public int compare(Term o1, Term o2) {
                return o1.query.compareTo(o2.query);
            }
        };
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<>() {
            @Override
            public int compare(Term o1, Term o2) {
                return Long.compare(o2.weight, o1.weight);
            }
        };
    }

    // Compares the two terms in lexicographic order,
    // but using only the first k characters of each query.
    public static Comparator<Term> byPrefixOrder(int k) {
        if (k < 0) throw new IllegalArgumentException();
        return new Comparator<>() {
            private int m = k;

            @Override
            public int compare(Term o1, Term o2) {
                int len = Math.min(Math.min(o1.query.length(), o2.query.length()), m);
                for (int i = 0; i < len; i++) {
                    int j = o1.query.charAt(i) - o2.query.charAt(i);
                    if (j != 0) return j;
                }
                if (len < m)
                    return o1.query.length() - o2.query.length();
                return 0;
            }
        };
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by whitespace, followed by the query.
    public String toString() {
        return String.format("%12d    %s", this.weight, this.query);
    }
}
