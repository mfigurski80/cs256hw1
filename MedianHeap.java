import java.util.ArrayList;
import java.util.Comparator;
public class MedianHeap<K extends Comparable<K>> { // TODO: maybe not Comparable<K>

    protected BinaryHeap<K> smallHeap = new BinaryHeap<K>(new MaxComparator<K>());
    protected BinaryHeap<K> largeHeap = new BinaryHeap<K>();

    public boolean isEmpty() {
        return (smallHeap.size() == 0);
    }
    public int size() {
        return smallHeap.size() + largeHeap.size();
    }
    /*
    private void rebalance(){
        //perhaps you'd use a helper method like this...
    }
    */
    public void insert(K k) {
        smallHeap.insert(k);
        while(smallHeap.size() > largeHeap.size() + 1) {
            System.out.print("Rebalancing: ");
            System.out.println(k);
            largeHeap.insert(smallHeap.removeRoot());
        }
        if (largeHeap.size() <= 0) return;
        while (smallHeap.root().compareTo(largeHeap.root()) > 0) {
            smallHeap.insert(largeHeap.removeRoot());
            largeHeap.insert(smallHeap.removeRoot());
        }
    }
    public K removeMedian() {
        K to_return = smallHeap.removeRoot();
        while (smallHeap.size() < largeHeap.size()) {
            smallHeap.insert(largeHeap.removeRoot());
        }
        return to_return;
    }
    public K median() {
        return smallHeap.root();
    }

    public String toString() {
        return smallHeap.toString() + " -- " + largeHeap.toString();
    }

    public static void main(String[] args) {
        MedianHeap<Integer> a = new MedianHeap<Integer>();
        a.insert(1);
        System.out.println(a.toString());
        a.insert(2);
        System.out.println(a.toString());
        a.insert(3);
        System.out.println(a.toString());
        a.insert(4);
        System.out.println(a.toString());
        a.insert(5);
        System.out.println(a.toString());
        a.insert(6);
        System.out.println(a.toString());
        a.insert(7);
        System.out.println(a.toString());

        System.out.print("MEDIAN: ");
        System.out.println(a.median());

        a.removeMedian();
        System.out.println(a.toString());
        a.removeMedian();
        System.out.println(a.toString());
    }
}
