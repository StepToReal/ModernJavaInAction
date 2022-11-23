package modernjava.executePattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAroundPattern {

    public void processTest() throws Exception{
        String line1 = processFile((BufferedReader br) -> br.readLine());
        String line2 = processFile((BufferedReader br) -> br.readLine() + br.readLine());

        BufferedReaderProcessor brp = new BufferedReaderProcessor() {
            @Override
            public String process(BufferedReader br) throws IOException {
                return br.readLine() + br.readLine();
            }
        };

        processFile(brp);
    }

    public String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    public String processFile(BufferedReaderProcessor brProcessor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return brProcessor.process(br);
        }
    }
}
