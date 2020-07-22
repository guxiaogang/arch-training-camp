package camp.week7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class LoadTestTool {

    public static void main(String[] args) {
        long sampleNumber = 100;
        int concurrentNumber = 10;
        String url = "http://www.baidu.com";
        long[] sampleNumbers = LongStream.rangeClosed(1, sampleNumber).toArray();
        List<ExecutionResult> results = new ForkJoinPool(concurrentNumber)
                .invoke(new LoadTestTask(sampleNumbers, concurrentNumber, url));

        AggregationReport report = AggregationAnalyzer.analyze(results);
        System.out.println(report);
    }
}
