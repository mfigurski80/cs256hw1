import java.util.ArrayList;
import java.util.Comparator;
public class TernaryHeap<K> extends Heap<K> {
    private Comparator<K> comp;
    public TernaryHeap(){
        this(new DefaultComparator<K>());
    }
    public TernaryHeap(Comparator<K> comp){
        array = new ArrayList<>();
        this.comp = comp;
    }

    private void swap(int i, int j){
        K temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
    private K root(){
        return array.get(0);
    }
    private K removeRoot() {
        K toReturn = root();
        swap(0, array.size() - 1);
        array.remove(array.size() - 1);
        sink(0);
        return toReturn;
    }
    private int left(int i) {
        return 3*i + 1;
    }
    private int middle(int i) {
        return 3*i + 2;
    }
    private int right(int i) {
        return 3*i + 3;
    }
    private boolean hasLeft(int i) {
        return left(i) < array.size();
    }
    private boolean hasMiddle(int i) {
        return middle(i) < array.size();
    }
    private boolean hasRight(int i) {
        return right(i) < array.size();
    }
    private int parent(int i) {
        return (i - 1) / 3;
    }
    private void sink(int i) {
        // System.out.println();
        // System.out.println(array);
        // System.out.print("SINK: ");
        // System.out.println(i);
        while (hasLeft(i)) {
            int leftIndex = left(i);
            int smallChildIndex = leftIndex;
            if (hasMiddle(i)) {
                int middleIndex = middle(i);
                if (comp.compare(array.get(middleIndex), array.get(smallChildIndex)) <= 0)
                    smallChildIndex = middleIndex;
            }
            if (hasRight(i)) {
                int rightIndex = right(i);
                if (comp.compare(array.get(rightIndex), array.get(smallChildIndex)) <= 0)
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
        swim(array.size()-1);
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
        TernaryHeap<Integer> a = new TernaryHeap<Integer>();
        a.insert(65);
        a.insert(70);
        a.insert(5);
        a.insert(25);
        a.insert(44);
        System.out.println(a.toString());

        int size = a.size();
        for (int i = 0; i < size; i++) {
            System.out.println(a.removeMin());
        }
        System.out.println(a.toString());
    }
}
