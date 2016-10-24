package dataset;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class Batches {
	static public class BatchSorter implements Comparator<Batch> {

		@Override
		public int compare(Batch o1, Batch o2) {
			return o1.getId().compareTo(o2.getId());
		}
	}

	private int nextId = 300;
	private final ObservableList<Batch> base = FXCollections.observableList(new ArrayList<Batch>());
	private final SortedList<Batch> sorted = new SortedList<>(base,new BatchSorter());

	public Batch makeNewBatch() {
		Batch result = new Batch(nextId++,"0");
		base.add(result);
		return result;
	}

	public Batch find(String id) {
		for (Batch candidate : base) {
			if (candidate.getId().equals(id))
				return candidate;
		}
		return new Batch(id, "Error", "Unknown Batch");
	}

	public void replace(String responseId, String status, String message) {
		for (Batch candidate : base) {
			if (candidate.getId().equals(responseId)) {
				base.remove(candidate);
				base.add(new Batch(responseId, status, message, "0"));
				return;
			}
		}
		throw new RuntimeException("Attempt to replace non-existent batch.");
	}
	
	public ObservableList<Batch> base() {
		return base;
	}

	public ObservableList<Batch> sorted() {
		return sorted;
	}

	public void reset() {
		base.clear();
	}
}
