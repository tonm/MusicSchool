
public class PracticeItem extends Song {
	private int practice_count;
	
	public PracticeItem(String name, int level) {
		super(name, level);
		practice_count = 0;
	}
	
	public int getPracticeCount() {
		return practice_count;
	}
	
	public void incPracticeCount() {
		++practice_count;
	}
	
	public void incPracticeCount(int count) {
		assert(count + practice_count <= 5);
		
		practice_count += count;
	}

}
