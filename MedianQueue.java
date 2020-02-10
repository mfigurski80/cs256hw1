public interface MedianQueue<K extends Comparable> {
    public boolean isEmpty();
    public int size();
    public void insert(K k);
    public K removeMedian();
    public K median();
}
