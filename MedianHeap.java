import java.util.ArrayList;
import java.util.Comparator;
public class MedianHeap<K extends Comparable<K>> {

    protected BinaryHeap<K> smallHeap = new BinaryHeap<K>(new MaxComparator<K>());
    protected BinaryHeap<K> largeHeap = new BinaryHeap<K>();

    /**
     * Checks if there is anything in either heap. Note, small heap is populated
     * first, so if small heap is empty we know MedianHeap is empty as well.
     * @return Whether this MedianHeap is empty
     */
    public boolean isEmpty() {
        return (smallHeap.size() == 0);
    }
    /**
     * Finds the size of the current MedianHeap by adding both small and
     * large heaps
     * @return size of this MedianHeap
     */
    public int size() {
        return smallHeap.size() + largeHeap.size();
    }
    /**
     * Inserts an item into the MedianHeap, maintaining its structure
     * @param k Item to insert
     */
    public void insert(K k) {
        smallHeap.insert(k);
        while(smallHeap.size() > largeHeap.size() + 1) {
            // System.out.print("Rebalancing: ");
            // System.out.println(k);
            largeHeap.insert(smallHeap.removeRoot());
        }
        if (largeHeap.size() <= 0) return;
        while (smallHeap.root().compareTo(largeHeap.root()) > 0) {
            smallHeap.insert(largeHeap.removeRoot());
            largeHeap.insert(smallHeap.removeRoot());
        }
    }
    /**
     * Removes the median item from the MedianHeap and returns it
     * @return Item at current median
     */
    public K removeMedian() {
        K to_return = smallHeap.removeRoot();
        while (smallHeap.size() < largeHeap.size()) {
            smallHeap.insert(largeHeap.removeRoot());
        }
        return to_return;
    }
    /**
     * Finds the current median item
     * @return Item at current median
     */
    public K median() {
        return smallHeap.root();
    }

    /**
     * Creates a String representation of this MedianHeap by combining String
     * representations of auxiliary heaps
     * @return String representation of this MedianHeap
     */
    public String toString() {
        return smallHeap.toString() + " -- " + largeHeap.toString();
    }

    /**
     * Testing script...
     * @param args Console arguments. Will be inserted into MedianHeap
     */
    public static void main(String[] args) {
        MedianHeap<String> a = new MedianHeap<String>();
        for (String cur : args)
            a.insert(cur);
        System.out.println(a.toString());

        int size = a.size();
        for (int i = 0; i < size; i++)
            System.out.println(a.removeMedian());
    }
}
