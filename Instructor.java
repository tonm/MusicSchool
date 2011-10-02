
public class Instructor extends Persistent implements TimeAware {
	private String name;
	private Classroom pupils;
	
	public Instructor(String name, Classroom clazz) {
		super(name + ".txt");
		this.name = name;
		this.pupils = clazz;
	}
	
	public void newDay() {
		
	}
	
	public void toFile() {
		
	}
	
	public void fromFile() {
		
	}

	public void newDay(int day) {
		
	}
}
