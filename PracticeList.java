import java.util.*;


public class PracticeList {
	private int assignedDay;
	private int dueDay;
	private List<PracticeItem> items;
	
	public PracticeList(PracticeItem[] items, int assigned, int due) {
		this.items = Arrays.asList(items);
		assignedDay = assigned;
		dueDay = due;
	}

	public List<PracticeItem> getPracticeItems() {
		 return items;
	}
	
	public int getLength() {
		return items.size();
	}
	
	public int getItemsQuantityLeft() {
		int numLeft = 0;
		for(PracticeItem p : items) {
			numLeft += 5 - p.getPracticeCount();
		}
		
		return numLeft;
	}

	public void removePracticed() {
		Iterator<PracticeItem> li = items.iterator();
		
		while(li.hasNext()) {
			PracticeItem p = li.next();
			if(p.getPracticeCount() == 5) {
				li.remove();
			}
		}
	}
	
}
