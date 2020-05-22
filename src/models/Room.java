package models;

import java.util.*;

public class Room {
	private String roomId;
	private int capacity;
	private int availableSeats;
	private List<Meeting> scheduledMeetings;
	private List<Meeting> currentMeetings;
	
	public Room(String roomId, int capacity){
		this.scheduledMeetings = new ArrayList<Meeting>();
		this.currentMeetings = new LinkedList<Meeting>();
		this.roomId = roomId;
		this.capacity = capacity;
		this.availableSeats = capacity;
	}

	public String getRoomId() {
		return roomId;
	}
	
	public List<Meeting> getMeetings() {
		return this.scheduledMeetings;
	}
	
	public List<Meeting> getCurrentMeetings() {
		return this.currentMeetings;
	}
	
	public void addMeeting(Meeting m) {
		this.scheduledMeetings.add(m);
		this.currentMeetings.add(m);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	
	public int getavailableSeats() {
		return availableSeats;
	}
}
