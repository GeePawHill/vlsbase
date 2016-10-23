package server;

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
			BatchResponse result = new BatchResponse(responseId, "Error","Invalid From Dealer");
			BatchResponse.responses.put(result.id, result);
			return;
		}

		Dealer to = Dealer.findById(toId);
		if (to == null) {
			BatchResponse result = new BatchResponse(responseId, "Error","Invalid To Dealer");
			BatchResponse.responses.put(result.id, result);
			return;
		}
		
		Order order = Order.findById(orderId);
		if (order == null) {
			BatchResponse result = new BatchResponse(responseId, "Error","Invalid Order");
			BatchResponse.responses.put(result.id, result);
			return;
		}
		
		if(!order.owner.equals(from.id))
		{
			BatchResponse result = new BatchResponse(responseId, "Error","Order Doesn't Belong To From");
			BatchResponse.responses.put(result.id, result);
			return;
		}
		
		order.owner = to.id;
		BatchResponse result = new BatchResponse(responseId, "Success","Completed");
		BatchResponse.responses.put(result.id, result);
	}

}
