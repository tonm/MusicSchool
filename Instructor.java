import java.util.Iterator;
import java.util.List;


public class Instructor extends Persistent implements TimeAware {
	private String name;
	private Classroom pupils;
	private ProgressReporter prog;
	private SongDB db;
	private boolean is_assignment_out;
	
	public Instructor(String name, Classroom clazz, SongDB db) {
		super(name + ".txt");
		this.name = name;
		this.pupils = clazz;
		this.db = db;
		is_assignment_out = false;
		prog = new ProgressReporter(this);
		
		
		Clock.getInstance().subscribe(this);
	}
	
	public void toFile() {
		
	}
	
	public void fromFile() {
		
	}

	public void newDay(int day) {
		if (day % 7 == 0) {
			if (is_assignment_out) {
				for (Iterator<Student> itr = pupils.getStudentsIterator(); itr.hasNext();) {
					Student s = itr.next();
					PracticeList incomplete = s.getCurrentPractice();
					
					if (incomplete.isEmpty())
						prog.report(s.getName() + "passed assignment given on " + Integer.toString(incomplete.getDueDay()));
					else
						prog.report(s.getName() + "failed assignment given on " + Integer.toString(incomplete.getDueDay()) +
								", did not do: " + incomplete.toString());
				}
				is_assignment_out = true;
				
				for (Iterator<Student> itr = pupils.getStudentsIterator(); itr.hasNext();) {
					Student s = itr.next();
					PracticeItem[] assignment = new PracticeItem[s.getStudentLevel()];
					
					for (int level = 0; level < s.getStudentLevel(); ++level) {
						List<Song> selection = db.getSongsByLevel(level);
						assignment[level] = new PracticeItem(selection.get((int)Math.round((selection.size() - 1) * Math.random())) );
					}
					
					s.assignPractice(new PracticeList(assignment, day, day+7));
				}
			}
		}
	}

	public String getName() {
		return name;
	}
}
