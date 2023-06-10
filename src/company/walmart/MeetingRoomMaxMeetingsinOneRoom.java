package company.walmart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Meeting {
    int start;
    int end;
    int pos;

    Meeting(int start, int end, int pos) {
        this.start = start;
        this.end = end;
        this.pos = pos;
    }
}

public class MeetingRoomMaxMeetingsinOneRoom {

    public static void main(String[] args) {
        int s[] = { 1, 3, 0, 5, 8, 5 };   // Starting time
        int f[] = { 2, 4, 6, 7, 9, 9 };    // Finish time

        // Defining an arraylist of meet type
        ArrayList<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < s.length; i++)
            meetings.add(new Meeting(s[i], f[i], i));

        maxMeeting(meetings);
    }
    public static void maxMeeting(ArrayList<Meeting> meetings){
        ArrayList<Integer> result = new ArrayList<>();
        int time_limit = 0;
        // Key Part: Sorting of meeting according to their finish time.
        Collections.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting m1, Meeting m2) {
                if (m1.end < m2.end){
                    return -1;
                }else if(m1.end > m2.end){
                    return 1;
                }
                return 0;
            }
        });
        // Arrays.sort(meetings, (a, b) -> Integer.compare(a[1], b[1]));


        // Initially select first meeting.
        result.add(meetings.get(0).pos);

        // time_limit to check whether new meeting can be conducted or not.
        time_limit = meetings.get(0).end;

        // Check for all meeting whether it can be selected or not.
        for (int i = 1; i < meetings.size(); i++) {
            if (meetings.get(i).start > time_limit) {
                result.add(meetings.get(i).pos);
                time_limit = meetings.get(i).end;   // Update time limit
            }
        }

        // Print final selected meetings.
        for (int i = 0; i < result.size(); i++)
            System.out.print(result.get(i) + 1 + " ");
    }
}
