package dataset;

public class Dataset {
	
	public final static Dataset data = new Dataset();
	
	private Batches batches = new Batches();
	private Orders orders = new Orders();
	private Dealers dealers = new Dealers();
	
	public Dataset()
	{
		reset();
	}
	
	public void reset()
	{
		IdGenerator.reset();
		batches.reset();
		dealers.reset();
		orders.reset(dealers.sorted());
		batches.reset();
	}
	
	public Batches batches()
	{
		return batches;
	}

	public Orders orders() {
		return orders;
	}
	
	public Dealers dealers() {
		return dealers;
	}
}
