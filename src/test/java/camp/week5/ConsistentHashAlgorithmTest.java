package camp.week5;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ConsistentHashAlgorithmTest {

    List<String> testData = new ArrayList<>();

    @Before
    public void setup() {
        for (int i = 0; i < 1000000; i++) {
            testData.add(UUID.randomUUID().toString());
        }
    }

    @Test
    public void TestGetMinStandardDeviation() {
        int minMode = 0;
        double minStandardDeviation = Double.MAX_VALUE;
        for (int i = 150; i < 200; i++) {
            int numOfReplicas = i;
            int numOfNode = 10;
            ConsistentHashAlgorithm algorithm = new ConsistentHashAlgorithm(numOfReplicas);
            for (int j = 0; j < numOfNode; j++) {
                algorithm.addMember("node".concat("_").concat(String.valueOf(j)));
            }
            Map<String, Integer> mapResult = new HashMap<>();
            for (String key : testData) {
                String mappingNode = algorithm.mappingToNode(key);
                if (!mapResult.containsKey(mappingNode)) {
                    mapResult.put(mappingNode, 1);
                } else {
                    mapResult.put(mappingNode, mapResult.get(mappingNode) + 1);
                }
            }
            System.out.println("=============================================");
            System.out.println("虚拟节点 ".concat(String.valueOf(numOfReplicas)).concat(" 个的对应分布情况："));
            System.out.println(mapResult);
            double standardDeviation = getStandardDeviation(mapResult);
            System.out.println("对应的标准差为：".concat(String.valueOf(standardDeviation)));
            if (standardDeviation < minStandardDeviation) {
                minMode = i;
            }
        }
        System.out.println("最小标准差对应的虚拟节点数为：".concat(String.valueOf(minMode)));
    }

    private double getStandardDeviation(Map<String, Integer> result) {
        long sum = 0;
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            int sub = entry.getValue() - 100000;
            sum += Math.pow(sub, 2);
        }
        return Math.sqrt(sum / 10);
    }

}
