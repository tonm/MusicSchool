// TODO: Automatically imported?
import java.lang.StringBuffer;

public class ProgressReporter extends Persistent {
    private StringBuffer buffer;

    public ProgressReporter(Instructor in) {
    	super(in.getName() + "-progrep.txt");
        buffer = new StringBuffer();
    }


    public void report(String r) {
        buffer.append(r + "\n");
    }

    public StringBuffer getReport() {
        return buffer;
    }

	public void toFile() {
		Store(buffer.toString());
	}

	public void fromFile() {
		buffer = new StringBuffer(getStored());
	}
}
