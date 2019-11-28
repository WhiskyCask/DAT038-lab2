import java.util.Arrays;

public class Autocomplete {

    private Term[] terms;
    // Initializes the data structure from the given array of terms.
    // Complexity: O(N log N), where N is the number of terms
    public Autocomplete(Term[] terms) {

        this.terms = Arrays.copyOf(terms, terms.length);
        Arrays.sort(this.terms, Term.byLexicographicOrder());
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    // Complexity: O(log N + M log M), where M is the number of matching terms
    public Term[] allMatches(String prefix) {

        Term prefixTerm = new Term(prefix, 0);

        int firstindex = RangeBinarySearch.firstIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));

        if (firstindex == -1) return new Term[0]; /* If no matches found */

        int lastindex = RangeBinarySearch.lastIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));

        Term[] returnterms = Arrays.copyOfRange(terms, firstindex, lastindex + 1);

        Arrays.sort(returnterms, Term.byReverseWeightOrder());

        return returnterms;
    }

    // Returns the number of terms that start with the given prefix.
    // Complexity: O(log N)
    public int numberOfMatches(String prefix) {

        Term prefixTerm = new Term(prefix, 0);

        int firstindex = RangeBinarySearch.firstIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));

        if (firstindex == -1) return 0; /* If no matches found */

        int lastindex = RangeBinarySearch.lastIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));

        return (lastindex + 1) - firstindex;
    }
}
