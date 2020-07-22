package camp.week7;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecutionResult {
    private Long id;
    private Long startTime;
    private Long finishTime;
    private Long executionTime;
    private String threadName;
    private String response;
}
