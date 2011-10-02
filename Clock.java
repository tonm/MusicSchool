import java.util.List;
import java.util.ArrayList;

public class Clock {
	private static Clock instance;
	private List<TimeAware> subscribers;

	private Clock() {
		subscribers = new ArrayList<TimeAware>(); 
	}

	public void subscribe(TimeAware subscriber) {
		subscribers.add(subscriber);
	}

	public void start(int weeks) {
        int days = weeks * 7;
		for (int day = 0; day < days; day++) {
            for (TimeAware sub : subscribers) {
                sub.newDay(day);
            }
        }
	}

	public static Clock getInstance() {
		if (instance == null) {
			instance = new Clock();
		}
		return instance;
	}
}
