package a.design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Crack code 7.2
 *
 */
public class CallHandler {
	static final int LEVELS = 3; // we have 3 levels of employees static nal int
	static final int NUM_FRESHERS = 5; // we have 5 freshers
	ArrayList<Employee>[] employeeLevels = new ArrayList[LEVELS];
	// queues for each call’s rank
	Queue<Call>[] callQueues = new LinkedList[LEVELS];

	public CallHandler() {

	}

	Employee getCallHandler(Call call) {
		for (int level = call.rank; level < LEVELS - 1; level++) {
			ArrayList<Employee> employeeLevel = employeeLevels[level];
			for (Employee emp : employeeLevel) {
				if (emp.free) {
					return emp;
				}
			}
		}
		return null;
	}

	// routes the call to an available employee, or adds to a queue
	void dispatchCall(Call call) {
		// try to route the call to an employee with minimal rank
		Employee emp = getCallHandler(call);
		if (emp != null) {
			emp.ReceiveCall(call);
		} else {
			// place the call into queue according to its rank
			callQueues[call.rank].add(call);
		}
	}

	// look for call for e’s rank
	void getNextCall(Employee e) {

	}
}

class Call {
	int rank = 0; // minimal rank of employee who can handle this call

	public void reply(String message) {
	}

	public void disconnect() {

	}
}

class Employee {
	CallHandler callHandler;
	int rank; // 0- fresher, 1 - technical lead, 2 - product manager boolean
				// free;
	boolean free;

	Employee(int rank) {
		this.rank = rank;
	}

	void ReceiveCall(Call call) {

	}

	// call is complete
	void CallHandled(Call call) {

	}

	// escalate call
	void CannotHandle(Call call) {
		call.rank = rank + 1;
		callHandler.dispatchCall(call);
		free = true;
		// look for waiting call
		callHandler.getNextCall(this);
	}
}

class Fresher extends Employee {
	public Fresher() {
		super(0);
	}
}

class TechLead extends Employee {
	public TechLead() {
		super(1);
	}
}

class ProductManager extends Employee {
	public ProductManager() {
		super(2);
	}
}