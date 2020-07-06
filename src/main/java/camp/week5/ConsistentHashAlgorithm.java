package camp.week5;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsistentHashAlgorithm {
    private int numberOfReplicas = 32;
    private final TreeMap<Integer, String> hashCircle = new TreeMap<>();
    private final Lock lock = new ReentrantLock();
    private int nodeSize = 0;
    private HashFunction hashFunction = Hashing.md5();

    public ConsistentHashAlgorithm(int numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
    }

    public String mappingToNode(String key) {
        Integer hash = hashFunction.hashBytes(key.getBytes()).asInt();
        try {
            lock.lock();
            if (!hashCircle.containsKey(hash)) {
                Integer ceilingKey = hashCircle.ceilingKey(hash);
                hash = (ceilingKey == null ? hashCircle.firstKey() : ceilingKey);
            }
        } finally {
            lock.unlock();
        }
        return hashCircle.get(hash);
    }

    public void addMember(String hostIdentifier) {
        try {
            lock.lock();
            if (!hashCircle.containsValue(hostIdentifier)) {
                for (int i = 0; i < numberOfReplicas; i++) {
                    hashCircle.put(hashFunction.hashBytes((hostIdentifier + i).getBytes()).asInt(), hostIdentifier);
                }
            }
            nodeSize++;
        } finally {
            lock.unlock();
        }
    }

    public void removeMember(String hostIdentifier) {
        try {
            lock.lock();
            for (int i = 0; i < numberOfReplicas; i++) {
                hashCircle.remove(hashFunction.hashBytes((hostIdentifier + i).getBytes()).asInt());
            }
            nodeSize--;
        } finally {
            lock.unlock();
        }

    }
}
