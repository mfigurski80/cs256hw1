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
    public K root(){
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
        return 3*i + 1;
    }
    /**
     * Gets the index of the middle child in the tree structure, given index of
     * parent
     * @param  i index of parent
     * @return   index of middle child
     */
    private int middle(int i) {
        return 3*i + 2;
    }
    /**
     * Gets the index of the right child in the tree structure, given index of
     * parent
     * @param  i index of parent
     * @return   index of right child
     */
    private int right(int i) {
        return 3*i + 3;
    }
    /**
     * Checks ArrayList to see if it contains the left child of given parent
     * node in the tree structure
     * @param  i index of parent
     * @return   whether or not a left child exists
     */
    private boolean hasLeft(int i) {
        return left(i) < array.size();
    }
    /**
     * Checks ArrayList to see if it contains the middle child of given parent
     * node in the tree structure
     * @param  i index of parent
     * @return   whether or not middle child exists
     */
    private boolean hasMiddle(int i) {
        return middle(i) < array.size();
    }
    /**
     * Checks ArrayList to see if it contains the right child of given parent
     * node in the tree structure
     * @param  i index of parent
     * @return   whether or not right child exists
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
        return (i - 1) / 3;
    }
    /**
     * Pushes the node at given index down the tree, until it gets to a
     * position where its children have less priority than it
     * @param i index of node to push down
     */
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
    /**
     * Pulls the node t the given index up the tree, until it gets to a
     * position where its parent has more priority than it
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
     * Adds a given node to the tree, and pulls it up to its proper position
     * @param k node to add
     */
    public void insert(K k) {
        array.add(k);
        // System.out.print("Inserting: ");
        // System.out.println(k);
        swim(array.size()-1);
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
     * Returns minimum node (ASSUMING THAT THE COMPARATOR IS THE
     * DEFAULT COMPARATOR, AS OPPOSED TO MAX COMPARATOR)
     * @return node of type K
     */
    public K min() {
        return root();
    }

    /**
     * Creates a string representation of the TernaryHeap's current state
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
     * Testing script... inserts command line arguments as strings and prints
     * them in sorted order
     * @param args Command line arguments to insert
     */
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
