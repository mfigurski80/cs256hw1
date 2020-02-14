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

    /**
     * Swaps nodes at positions i and j in the ArrayList
     * @param i index of first item
     * @param j index of second item
     */
    private void swap(int i, int j){
        K temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
    /**
     * Returns root node -- by default, the smallest value in the ArrayList
     * @return item of type K
     */
    public K root() {
        return array.get(0);
    }
    /**
     * Deletes root node and returns it -- by default, the smallest value in
     * the ArrayList
     * @return item of type K
     */
    public K removeRoot() {
        K toReturn = root();
        swap(0, array.size() - 1);
        array.remove(array.size() - 1);
        sink(0);
        return toReturn;
    }
    /**
     * Gets the index of the left child in the tree structure, given index of
     * parent
     * @param  i index of parent
     * @return   index of left child
     */
    private int left(int i) {
        return 2*i + 1;
    }
    /**
     * Gets the index of the right child in the tree structure, given index of
     * parent
     * @param  i index of parent
     * @return   index of right child
     */
    private int right(int i) {
        return 2*i + 2;
    }
    /**
     * Checks ArrayList to see if it contains the left child of given parent
     * node in the tree structure.
     * @param  i index of parent
     * @return   whether or not a left child exists
     */
    private boolean hasLeft(int i) {
        return left(i) < array.size();
    }
    /**
     * Checks ArrayList to see if it contains the right child of given parent
     * node in the tree structure.
     * @param  i index of parent
     * @return   whether or not a right child exists
     */
    private boolean hasRight(int i) {
        return right(i) < array.size();
    }
    /**
     * Gets the index of a given node's parent within the tree structure
     * @param  i index of child
     * @return   index of parent
     */
    private int parent(int i) {
        return (i-1) / 2;
    }
    /**
     * Pushes the node at given index down the tree, until it gets to a
     * position where its children have less priority.
     * @param i index of node to push down
     */
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
    /**
     * Pulls the node at the given index up the tree, until it gets to a
     * position where its parent has more priority.
     * @param i index of node to pull up
     */
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
    /**
     * Adds a given node to the tree, and pulls it up to it's proper place
     * @param k node to add
     */
    public void insert(K k) {
        array.add(k);
        swim(array.size() - 1);
    }
    /**
     * Deletes minimum node and returns it (ASSUMING THAT THE COMPARATOR IS THE
     * DEFAULT COMPARATOR, AS OPPOSED TO MAX COMPARATOR)
     * @return node of type K
     */
    public K removeMin() {
        return removeRoot();
    }
    /**
     * Returns minimum node (ASSUMING THAT THE COMPARATOR IS THE DEFAULT
     * COMPARATOR, AS OPPOSED TO MAX COMPARATOR)
     * @return node of type K
     */
    public K min() {
        return root();
    }

    /**
     * Creates a string representation of the Binary Heap's current state.
     * @return String representation
     */
    public String toString() {
        return array.toString();
    }
    /**
     * Checks the current amount of nodes in the heap
     * @return Number of nodes
     */
    public int size() {
        return array.size();
    }

    /**
     * Testing script...
     * @param args Command line arguments. Will be sorted into BinaryHeap
     */
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
