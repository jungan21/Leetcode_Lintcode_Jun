package company.walmart.design.elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private List<ElevatorButton> buttons;

    private List<Boolean> upStops;
    private List<Boolean> downStops;

    private int currLevel;
    private Status status;

    public Elevator(int n) {
        buttons = new ArrayList<ElevatorButton>();
        upStops = new ArrayList<Boolean>();
        downStops = new ArrayList<Boolean>();
        currLevel = 0;
        status = Status.IDLE;

        for (int i = 0; i < n; i++) {
            upStops.add(false);
            downStops.add(false);
        }
    }
    public void insertButton(ElevatorButton eb) {
        buttons.add(eb);
    }

    public void handleExternalRequest(ExternalRequest r) {
        if(r.getDirection() == Direction.UP) {
            // 之所以-1， 因为list 的index从0开始，而电梯楼层从1开始
            upStops.set(r.getLevel() - 1, true);
            if(noRequests(downStops)) {
                status = Status.UP;
            }
        } else {
            downStops.set(r.getLevel() - 1, true);
            if(noRequests(upStops)) {
                status = Status.DOWN;
            }
        }
    }

    public void handleInternalRequest(InternalRequest r) {
        // check valid
        if(status == Status.UP) {
            if(r.getLevel() >= currLevel + 1) {
                upStops.set(r.getLevel() - 1, true);
            }
        } else if(status == Status.DOWN) {
            if(r.getLevel() <= currLevel + 1) {
                downStops.set(r.getLevel() - 1, true);
            }
        }
    }

    public void openGate() throws Exception {
        if(status == Status.UP) {
            for(int i = 0; i < upStops.size(); i++) {
                int checkLevel = (currLevel + i) % upStops.size();
                if(upStops.get(checkLevel)) {
                    currLevel = checkLevel;
                    upStops.set(checkLevel, false);
                    break;
                }
            }
        } else if(status == Status.DOWN) {
            for(int i = 0; i < downStops.size(); i++) {
                int checkLevel = (currLevel + downStops.size() - i) % downStops.size();
                if(downStops.get(checkLevel)) {
                    currLevel = checkLevel;
                    downStops.set(checkLevel, false);
                    break;
                }
            }
        }
    }

    public void closeGate() {
        if(status == Status.IDLE) {
            if(noRequests(downStops)) {
                status = Status.UP;
                return;
            }
            if(noRequests(upStops)) {
                status = Status.DOWN;
                return;
            }
        } else if(status == Status.UP) {
            if(noRequests(upStops)) {
                if(noRequests(downStops)) {
                    status = Status.IDLE;
                }
                else status = Status.DOWN;
            }
        } else {
            if(noRequests(downStops)) {
                if(noRequests(upStops)) {
                    status = Status.IDLE;
                }
                else status = Status.UP;
            }
        }
    }

    private boolean noRequests(List<Boolean> stops) {
        for(int i = 0; i < stops.size(); i++) {
            if(stops.get(i)) {
                return false;
            }
        }
        return true;
    }
    public String elevatorStatusDescription() {
        String description = "Currently elevator status is : " + status
                + ".\nCurrent level is at: " + (currLevel + 1)
                + ".\nup stop list looks like: " + upStops
                + ".\ndown stop list looks like:  " + downStops
                + ".\n*****************************************\n";
        return description;
    }
}
