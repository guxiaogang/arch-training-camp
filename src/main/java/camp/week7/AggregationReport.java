package camp.week7;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AggregationReport {
    private long maxResTime;
    private long minResTime;
    private double avgResTime;
    private long ninetyFiveResTime;
    private long fiftyResTime;

    public String toString() {
        StringBuilder sb = new StringBuilder("Aggregation Report：");
        sb.append(" min->").append(this.getMinResTime());
        sb.append(" max->").append(this.getMaxResTime());
        sb.append(" avg->").append(this.getAvgResTime());
        sb.append(" 50%->").append(this.getFiftyResTime());
        sb.append(" 95%->").append(this.getNinetyFiveResTime());
        return sb.toString();
    }
}
