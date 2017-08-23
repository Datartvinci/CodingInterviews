import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Datartvinci on 2017/1/25.
 */
public class ConsistentHash<T> {
    interface HashFunction {
        int hash(Object src);
    }
    private  HashFunction hashFunction;
    private int numberOfReplicas;//虚拟节点的个数
    private SortedMap<Integer,T> circle=new TreeMap<Integer, T>();

    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T>nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;
        nodes.forEach(this::add);
    }
    public void add(T node){
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunction.hash(node.toString()+i),node);
        }
    }
    public void remove(T node){
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunction.hash(node.toString()+i));
        }
    }
    public T get(Object key){
        if(circle.isEmpty()){
            return null;
        }
        int hash=hashFunction.hash(key);
        if(!circle.containsKey(hash)){
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            hash=tailMap.isEmpty()?circle.firstKey():tailMap.firstKey();
        }
        return circle.get(hash);
    }
}
