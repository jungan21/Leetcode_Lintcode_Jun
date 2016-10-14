package design.CallCenter;

abstract class Employee {
	private Call currentCall = null;
	protected Rank rank;
	private CallHandler callHandler;

	public Employee(CallHandler handler) {
		callHandler = handler;
	}

	/* Start the conversation */
	public void receiveCall(Call call) {
		currentCall = call;
	}

	/* the issue is resolved, finish the call */
	public void callCompleted() {
		if (currentCall != null) {
			/* Disconnect the call. */
			currentCall.disconnect();

			/* Free the employee */
			currentCall = null;
		}

		/* Check if there is a call waiting in queue */
		assignNewCall();
	}

	/*
	 * The issue has not been resolved. Escalate the call, and assign a new call
	 * to the employee.
	 */
	public void escalateAndReassign() {
		if (currentCall != null) {
			/* escalate call */
			currentCall.incrementRank();
			callHandler.dispatchCall(currentCall);

			/* free the employee */
			currentCall = null;
		}

		/* assign a new call */
		assignNewCall();
	}

	/* Assign a new call to an employee, if the employee is free. */
	public boolean assignNewCall() {
		if (!isFree()) {
			return false;
		}
		return callHandler.assignCall(this);
	}

	/* Returns whether or not the employee is free. */
	public boolean isFree() {
		return currentCall == null;
	}

	public Rank getRank() {
		return rank;
	}
}
