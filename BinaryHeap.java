import java.util.ArrayList;
import java.util.Comparator;
public class BinaryHeap<K> extends Heap<K> {
    private Comparator<K> comp;
    public BinaryHeap(){
        this(new DefaultComparator<K>());
    }
    public BinaryHeap(Comparator<K> comparator){
        array = new ArrayList<K>();
        comp = comparator;
    }

    private void swap(int i, int j){
        K temp = array.get(i);
        // System.out.print("SWAP: ");
        // System.out.print(i);
        // System.out.print(", ");
        // System.out.println(j);
        // System.out.println(array.toString());
        array.set(i, array.get(j));
        array.set(j, temp);
        // System.out.println(array.toString());
    }
    public K root() {
        return array.get(0);
    }
    public K removeRoot() {
        K toReturn = root();
        swap(0, array.size() - 1);
        array.remove(array.size() - 1);
        sink(0);
        return toReturn;
    }
    private int left(int i) {
        return 2*i + 1;
    }
    private int right(int i) {
        return 2*i + 2;
    }
    private boolean hasLeft(int i) {
        return left(i) < array.size();
    }
    private boolean hasRight(int i) {
        return right(i) < array.size();
    }
    private int parent(int i) {
        return (i-1) / 2;
    }
    private void sink(int i) {
        while (hasLeft(i)) {
            int leftIndex = left(i);
            int smallChildIndex = leftIndex;
            if (hasRight(i)) {
                int rightIndex = right(i);
                if (comp.compare(array.get(leftIndex), array.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;
            }
            if (comp.compare(array.get(smallChildIndex), array.get(i)) >= 0)
                break;
            swap(i, smallChildIndex);
            i = smallChildIndex;
        }
    }
    private void swim(int i) {
        while (i > 0) {
            int p = parent(i);
            if (comp.compare(array.get(i), array.get(p)) < 0) {
                swap(i, p);
                i = p;
            } else {
                i = 0;
            }
        }
    }
    public void insert(K k) {
        array.add(k);
        // System.out.print("Inserting: ");
        // System.out.println(k);
        swim(array.size() - 1);
    }
    public K removeMin() {
        return removeRoot();
    }
    public K min() {
        return root();
    }

    public String toString() {
        return array.toString();
    }
    public int size() {
        return array.size();
    }

    public static void main(String[] args) {
        BinaryHeap<String> a = new BinaryHeap<String>();
        for (String cur : args) {
            a.insert(cur);
        }
        System.out.println(a.toString());

        int size = a.size();
        for (int i = 0; i < size; i++) {
            System.out.println(a.removeMin());
        }
    }
}
