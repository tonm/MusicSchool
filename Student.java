import java.util.Iterator;
import java.util.List;
import java.util.Random;

/*
 * Uniformly randomly choose a level and select songs up to selected level from SongDB.
 * Each day, Student will determine the amount of work for the given day in order to complete all PracticeItems
 * by Saturday.  Towards the beginning of the week, the Student will have a low probability of completing a small amount
 * of work.  Towards the end of the week, the Student will have a higher probability of completing a sizable number of
 * PracticeItems.  This is otherwise known as procrastination. 
 */
public class Student extends Persistent implements TimeAware {
	private String name;
	private PracticeList current;
	private PlayList achieved;
	private int level;
	private static final int MAX_LEVEL = 7;
	
	public Student(String name, int level) {
		super(name + ".txt");
		this.name = name;
		this.current = null;
		//this.level = (new Random().nextInt(MAX_LEVEL));
		this.level = level;
		
		// Select songs up to level
		this.achieved = new PlayList();
		for(int i = 0; i < this.level; i++) {
			this.achieved.addSong(SongDB.getSongsByLevel(i));
		}
		
		List<Song> currLevelSongs = SongDB.getSongsByLevel(this.level);
		int currLevSongCount = currLevelSongs.size();
		
		int numCurrent = (new Random()).nextInt(currLevSongCount + 1);
		
		Iterator<Song> it = currLevelSongs.iterator();
		while(it.hasNext()) {
			achieved.addSong(it.next());
			it.remove();
		}
	}
	
	// If you're calling this constructor, you should populate using getStored(String) function
	public Student(String from_file) {
		super(from_file);
		this.name = null;
		this.current = null;
		this.achieved = new PlayList();
	}
	
	public int getStudentLevel() {
		return level;
	}
	
	public String getName() {
		return name;
	}
	
	public void assignPractice(PracticeList work) {
		current = work;
	}
	
	public PracticeList getCurrentPractice() {
		List<PracticeItem> prl = current.getPracticeItems();
		for(PracticeItem p : prl) {
			if(p.getPracticeCount() == 5) { // We've met practice objective; it's considered mastered
				achieved.addSong(new Song(p.getName(), p.getLevel()));
			}
		}
		
		current.removePracticed(); // If mastered, remove from list
		return current; // Return PracticeList of uncompleted PracticeItems
	}
	
	public void newDay(int day) {
		if(day % 7 == 0) {  // It's Sunday
			achieved.reset();
		}
		
		doDailyPractice(day % 7);
	}
	
	public void doDailyPractice(int weekday) {
		Random rand = new Random();
		double chance = rand.nextInt(weekday);
		
		if(chance > (7 - weekday)) {
			practice(weekday);
			play(weekday);
		}
	}
	
	public void play(int weekday) {
		int toPlay = achieved.toPlay();
		int today = toPlay * (weekday / 6);
		
		List<SongItem> pl = achieved.getPlayList();
		
		for(SongItem s : pl) {
			if(!s.isPlayed()) {
				s.play();
				today--;
			}
			
			if(today == 0) {
				return;
			}
		}
	}
	
	public void practice(int weekday) {
		int itemsLeft = current.getItemsQuantityLeft();
		int itemsToPractice = itemsLeft * (weekday / 6);
		
		List<PracticeItem> items = current.getPracticeItems();
		for(PracticeItem p : items) {
			int practiceCount = p.getPracticeCount();
			
			if(itemsToPractice == 0) {
				return;
			}
			else if(itemsToPractice > (5 - practiceCount)) {
				p.incPracticeCount(5 - practiceCount);
				itemsToPractice -= 5 - practiceCount;
			}
			else {
				p.incPracticeCount(itemsToPractice);
				itemsToPractice -= practiceCount;
			}
			
		}
	}
	
	public String store() {
		String contents = new String();
		contents = this.name + "^" + this.level + "^^" + current.toString() + "";
		
		return contents;
	}
	
	public void getStored(String contents) {
		String [] data = contents.split("^^,\\s");
		String [] nameAndLevel = data[0].split("^\\s");
		this.name = nameAndLevel[0];
		this.level = Integer.parseInt(nameAndLevel[1]);
		
		String [] playList = data[1].split("~\\s");
		
		for(String s : playList) {
			String [] titleAndLevel = s.split("^\\s");
			Song newSong = new Song(titleAndLevel[0], Integer.parseInt(titleAndLevel[1]));
			achieved.addSong(newSong);
		}
	}
	
	public Boolean practicedPlayList() {
		
		return null;
	}

	@Override
	public void fromFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toFile() {
		// TODO Auto-generated method stub
		
	}
}
