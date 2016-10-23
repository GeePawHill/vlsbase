package vls;

public interface Handler {

	String dealers();

	String orders();

	String transfer(String fromId, String toId, String order);

	String batches();

	String batch(String id);

}