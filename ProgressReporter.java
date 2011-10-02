// TODO: Automatically imported?
import java.lang.StringBuffer;

public class ProgressReporter {
    private static ProgressReporter instance;
    private StringBuffer buffer;

    private ProgressReporter() {
        buffer = new StringBuffer();
    }

    public static ProgressReporter getInstance() {
        if (instance == null) {
            instance = new ProgressReporter();
        }
        return instance;
    }

    public void report(String r) {
        buffer.append(r);
    }

    public StringBuffer getReport() {
        return buffer;
    }
}
