package models;

public class Meeting {
	private String meetingId;
	private int startTime;
	private int endTime;
	private int noOfAttendees;
	
	public Meeting(String meetingId, int startTime, int endTIme, int noOfAttendees) {
		super();
		this.meetingId = meetingId;
		this.startTime = startTime;
		this.endTime = endTIme;
		this.noOfAttendees = noOfAttendees;
	}
	
	public String getMeetingId() {
		return meetingId;
	}

	public int getStartTime() {
		return startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}

	public int getNoOfAttendees() {
		return noOfAttendees;
	}
	
}
