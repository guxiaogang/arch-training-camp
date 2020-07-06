package camp.week5;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsistentHashAlgorithmTest {

    List<String> testData = new ArrayList<>();

//    @Before
//    public void setup() {
//        for (int i = 0; i < 1000000; i++) {
//            testData.add("Key-".concat(String.valueOf(i)));
//        }
//    }

    @Test
    public void testConsistentHashAlgWith32Replicas() {
        int numOfReplicas = 32;
        int numOfNode = 10;
        ConsistentHashAlgorithm algorithm = new ConsistentHashAlgorithm(numOfReplicas);
        for (int i = 0; i < numOfNode; i++) {
            algorithm.addMember("node".concat("_").concat(String.valueOf(i)));
        }
        Map<String,Integer> mapResult = new HashMap<>();
        for (String key : testData) {
            String mappingNode = algorithm.mappingToNode(key);
            if(mapResult.containsKey(mappingNode)){
                mapResult.put()
            }
        }

    }
}
