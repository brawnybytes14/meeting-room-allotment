package services;

import java.util.*;
import models.*;

public class MeetingSchedulerService {
	private List<Room> rooms;
	private List<Meeting> meetings;
	private List<Meeting> unScheduledMeetings;

	public MeetingSchedulerService(){
		this.rooms = new ArrayList<Room>();
		this.meetings = new ArrayList<Meeting>();
		this.unScheduledMeetings = new ArrayList<Meeting>();
	}
	
	public void addMeeting(Meeting meeting) {
		this.meetings.add(meeting);
	}
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	
	private void settleAvailableSeats(Meeting currentMeeting) {
		for(Room room: this.rooms) {
			
			for(Meeting m: room.getCurrentMeetings()) {
				if(m.getEndTime() <= currentMeeting.getStartTime()) {
					
					int availableSeats = room.getavailableSeats() + m.getNoOfAttendees();
		
					room.getCurrentMeetings().remove(m);
		
					room.setAvailableSeats(availableSeats);
				}
			}
			
		}
	}
	
	private Room getAvailableRoom(int attendees){
		Room room = null;
		int min = Integer.MAX_VALUE;
		for (Room r : this.rooms) {
			if(attendees <= r.getavailableSeats()) {
				int diff = r.getavailableSeats() - attendees;
				if(diff < min) {
					min = diff;
					room = r;
				}
			}
		}
		return room;
	}
	
	public void scheduleMeetings() {
		// Sort the meetings
		Collections.sort(this.meetings, new MeetingComparator());
		
		for(Meeting m: this.meetings) {
			// First settle the available seats, then get the available room
			settleAvailableSeats(m);
			
			Room r = getAvailableRoom(m.getNoOfAttendees());
			
			if(r != null) { // Room is available
				r.setAvailableSeats(r.getavailableSeats() - m.getNoOfAttendees());
				r.addMeeting(m);
			} else {
				this.unScheduledMeetings.add(m);
			}
		}
	}
	
	public void printScheduledMeetings() {
		for(Room r: this.rooms) {
			if(r.getMeetings().size() > 0) {
				System.out.println("Room: "+ r.getRoomId() + " has the following meetings.");
				for(Meeting m: r.getMeetings()) {
					System.out.println("Meeting: " + m.getMeetingId() + ", from " + m.getStartTime() + " to " + m.getEndTime() + ", attendees " + m.getNoOfAttendees());
				}
				System.out.println();
			}
		}
	}
	
	public void printUnScheduledMeetings() {
		if(this.unScheduledMeetings.size() > 0) {
			System.out.println("Unscheduled meetings due to conflicts");
			for(Meeting m: this.unScheduledMeetings) {
				System.out.println("Meeting: " + m.getMeetingId() + ", from " + m.getStartTime() + " to " + m.getEndTime() + ", attendees " + m.getNoOfAttendees());
			}
		}
	}
	
}

class MeetingComparator implements Comparator<Meeting> 
{ 
    public int compare(Meeting a, Meeting b) 
    { 
    	if(a.getStartTime() != b.getStartTime())
    		return a.getStartTime() - b.getStartTime(); 
    	
    	// If duration is same give preference to maximum no. of attendees.
    	if(a.getStartTime() == b.getStartTime() && a.getEndTime() == b.getEndTime())
    		return b.getNoOfAttendees() - a.getNoOfAttendees();
    	
    	//If start time conflicting give preference to longest duration
    	return b.getEndTime() - a.getEndTime();
    } 
} 
  
