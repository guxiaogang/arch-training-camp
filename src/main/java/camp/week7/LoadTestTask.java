package camp.week7;


import lombok.extern.slf4j.Slf4j;
import sun.net.www.http.HttpClient;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class LoadTestTask extends RecursiveTask<List<ExecutionResult>> {

    private final long[] sampleNumbers;
    private final int start;
    private final int end;
    private int concurrentNumber = 10;
    private String url;

    private LoadTestTask(long[] sampleNumbers, int start, int end, int concurrentNumber, String url) {
        super();
        this.sampleNumbers = sampleNumbers;
        this.start = start;
        this.end = end;
        this.concurrentNumber = concurrentNumber;
        this.url = url;
    }

    public LoadTestTask(long[] sampleNumbers, int concurrentNumber, String url) {
        super();
        this.sampleNumbers = sampleNumbers;
        this.start = 0;
        this.end = sampleNumbers.length;
        this.concurrentNumber = concurrentNumber;
        this.url = url;
    }

    @Override
    protected List<ExecutionResult> compute() {
        int length = end - start;

        if (length <= sampleNumbers.length / concurrentNumber) {
            try {
                return computeSequentially();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        LoadTestTask leftTask = new LoadTestTask(sampleNumbers, start, start + length / 2, concurrentNumber, url);
        leftTask.fork();
        LoadTestTask rightTask = new LoadTestTask(sampleNumbers, start + length / 2, end, concurrentNumber, url);
        rightTask.fork();
        List<ExecutionResult> rightResult = null;
        try {
            rightResult = rightTask.get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        List<ExecutionResult> leftResult = leftTask.join();
        ArrayList<ExecutionResult> results = new ArrayList<>();
        results.addAll(leftResult);
        results.addAll(rightResult);
        return results;
    }

    private List<ExecutionResult> computeSequentially() throws Exception {
        List<ExecutionResult> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            long startTime = System.currentTimeMillis();
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            String response = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                response = streamToString(inputStream);
            }
            long finishTime = System.currentTimeMillis();
            ExecutionResult executionResult = ExecutionResult.builder()
                    .id(sampleNumbers[i])
                    .threadName(Thread.currentThread().getName())
                    .startTime(startTime)
                    .finishTime(finishTime)
                    .executionTime(finishTime - startTime)
                    .response(response)
                    .build();
            list.add(executionResult);
        }
        return list;
    }

    private String streamToString(InputStream is) {
        String result = "";
        try {
            byte[] bytes = new byte[0];
            bytes = new byte[is.available()];
            is.read(bytes);
            result = new String(bytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }
}
