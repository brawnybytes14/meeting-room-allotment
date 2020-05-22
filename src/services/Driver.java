package services;

import java.util.*;

import models.*;

public class Driver {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		MeetingSchedulerService ms  = new MeetingSchedulerService();
		
		System.out.println("NOTE: Taking interval as integers for simplicity. For example take 10:00 as 1, 10:30 as 2, 11:00 as 3 etc.\n");
		ms.addMeeting(new Meeting("m1", 2, 4, 3));
		ms.addMeeting(new Meeting("m2", 1, 2, 4));
		ms.addMeeting(new Meeting("m3", 3, 7, 8));
		ms.addMeeting(new Meeting("m4", 6, 7, 2));
		ms.addMeeting(new Meeting("m5", 6, 7, 9));
		ms.addMeeting(new Meeting("m5", 1, 7, 9));
		
		ms.addRoom(new Room("r1", 4));
		ms.addRoom(new Room("r2", 10));
		
		// Run scheduling algorithm
		ms.scheduleMeetings();
		
		// Print schedules
		ms.printScheduledMeetings();
		ms.printUnScheduledMeetings();
		
		sc.close();
	}
}
