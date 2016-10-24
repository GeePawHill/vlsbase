package dataset;

public class TransferRunnable implements Runnable {

	private String fromId;
	private String toId;
	private String orderId;
	private String responseId;
	private int delay;

	public TransferRunnable(int delay,String responseId, String fromId, String toId, String orderId) {
		this.delay = delay;
		this.responseId = responseId;
		this.fromId = fromId;
		this.toId = toId;
		this.orderId = orderId;

	}

	@Override
	public void run() {
		try {
			Thread.sleep(delay*1000);
		} catch (InterruptedException expected) {
		}
		Dealer from = Dealer.findById(fromId);
		if (from == null) {
			Batch.replace(responseId, "Error","Invalid From Dealer");
			return;
		}

		Dealer to = Dealer.findById(toId);
		if (to == null) {
			Batch.replace(responseId, "Error","Invalid To Dealer");
			return;
		}
		
		Order order = Order.findById(orderId);
		if (order == null) {
			Batch.replace(responseId, "Error","Invalid Order");
			return;
		}
		
		if(!order.getOwner().equals(from.getId()))
		{
			Batch.replace(responseId, "Error","Order Doesn't Belong To From");
			return;
		}
		Order.setOwner(order,to.getId());
		Batch.replace(responseId, "Success","Completed");
	}

}
