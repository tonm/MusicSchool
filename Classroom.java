import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class Classroom {
	private List<Student> students;
	
	public Classroom(Student[] students) {
		this.students = Arrays.asList(students);
	}
	
	public Iterator<Student> getStudentsIterator() {
		return students.iterator();
	}
	
	public String toString() {
		String result = new String();
		for (Student s : students) {
			s.toFile();
			result += s.getFile_name() + " ";
		}
		
		return result.trim();
	}
}
