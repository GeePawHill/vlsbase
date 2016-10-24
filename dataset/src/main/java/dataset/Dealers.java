package dataset;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class Dealers {
	static public class DealerSorter implements Comparator<Dealer> {

		@Override
		public int compare(Dealer o1, Dealer o2) {
			return o1.getId().compareTo(o2.getId());
		}
	}

	private final ObservableList<Dealer> base = FXCollections.observableList(new ArrayList<Dealer>());
	private final SortedList<Dealer> sorted = new SortedList<>(base);

	public Dealer makeNewDealer(int index) {
		String id = IdGenerator.noun("VD_");
		String name = id + "(Dealer)";
		Dealer dealer = new Dealer(id, name);
		base.add(dealer);
		return dealer;
	}

	public Dealer findById(String dealerId) {
		for (Dealer candidate : base) {
			if (candidate.getId().equals(dealerId)) {
				return candidate;
			}
		}
		return null;
	}

	public ObservableList<Dealer> sorted() {
		return sorted;
	}

	public void reset() {
		base.clear();
		for(int i=0;i<3;i++)
		{
			makeNewDealer(i);
		}
	}
}
