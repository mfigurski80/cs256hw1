import java.util.ArrayList;
public abstract class Heap<K> implements PriorityQueue<K>{
    // every heap will use an ArrayList to store the implicit complete binary tree structure
    protected ArrayList<K> array = new ArrayList<K>();
    public boolean isEmpty(){ return size() == 0; }
    public int size(){ return array.size(); }
    public abstract void insert(K k);
    public abstract K removeMin();
    public abstract K min();
}
