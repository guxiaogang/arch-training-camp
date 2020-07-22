package camp.week7;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class AggregationAnalyzer {
    public static AggregationReport analyze(List<ExecutionResult> results) {
        List<ExecutionResult> sortedResult = results.stream()
                .sorted(Comparator.comparing(ExecutionResult::getExecutionTime))
                .collect(Collectors.toList());
        double avg = sortedResult.stream().mapToLong(ExecutionResult::getExecutionTime).average().orElse(0d);
        int ninetyFive = (int) (sortedResult.size() * 0.95);
        int fifty = (int) (sortedResult.size() * 0.50);
        AggregationReport report = AggregationReport.builder()
                .minResTime(sortedResult.get(0).getExecutionTime())
                .maxResTime(sortedResult.get(sortedResult.size() - 1).getExecutionTime())
                .avgResTime(avg)
                .fiftyResTime(sortedResult.get(fifty).getExecutionTime())
                .ninetyFiveResTime(sortedResult.get(ninetyFive).getExecutionTime())
                .build();
        return report;
    }
}
