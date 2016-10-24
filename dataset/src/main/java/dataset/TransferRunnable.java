package dataset;

public class TransferRunnable implements Runnable {

	private String fromId;
	private String toId;
	private String orderId;
	private String responseId;
	private int delay;

	public TransferRunnable(int delay, String responseId, String fromId, String toId, String orderId) {
		this.delay = delay;
		this.responseId = responseId;
		this.fromId = fromId;
		this.toId = toId;
		this.orderId = orderId;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(delay * 1000);
		} catch (InterruptedException expected) {
		}
		Dealer from = Dataset.data.dealers().findById(fromId);
		if (from == null) {
			Dataset.data.batches().replace(responseId, "Error", "Invalid From Dealer");
			return;
		}

		Dealer to = Dataset.data.dealers().findById(toId);
		if (to == null) {
			Dataset.data.batches().replace(responseId, "Error", "Invalid To Dealer");
			return;
		}

		Order order = Dataset.data.orders().findById(orderId);
		if (order == null) {
			Dataset.data.batches().replace(responseId, "Error", "Invalid Order");
			return;
		}

		if (!order.getOwner().equals(from.getId())) {
			Dataset.data.batches().replace(responseId, "Error", "Order Doesn't Belong To From");
			return;
		}
		Dataset.data.orders().setOwner(order, to.getId());
		Dataset.data.batches().replace(responseId, "Success", "Completed");
	}

}
