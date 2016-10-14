package design.Elevator;

public class Door {
	enum Status {
		OPEN, CLOSE;
	}

	Status status;

	public void open() {

	}

	public void close() {

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
