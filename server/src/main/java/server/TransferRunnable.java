package server;

import java.time.LocalDateTime;

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
			Batch result = new Batch(responseId, "Error","Invalid From Dealer");
			Batch.responses.put(result.id, result);
			return;
		}

		Dealer to = Dealer.findById(toId);
		if (to == null) {
			Batch result = new Batch(responseId, "Error","Invalid To Dealer");
			Batch.responses.put(result.id, result);
			return;
		}
		
		Order order = Order.findById(orderId);
		if (order == null) {
			Batch result = new Batch(responseId, "Error","Invalid Order");
			Batch.responses.put(result.id, result);
			return;
		}
		
		if(!order.owner.equals(from.id))
		{
			Batch result = new Batch(responseId, "Error","Order Doesn't Belong To From");
			Batch.responses.put(result.id, result);
			return;
		}
		
		order.owner = to.id;
		order.changed = LocalDateTime.now().toString();

		Batch result = new Batch(responseId, "Success","Completed");
		Batch.responses.put(result.id, result);
	}

}
