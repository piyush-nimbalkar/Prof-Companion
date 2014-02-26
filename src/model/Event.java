package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {

	private String type;
	private String time;
	private String day;
	private String note;

	public Event(String _type, String _time, String _day, String _note) {
		type = _type;
		time = _time;
		day = _day;
		note = _note;
	}

	public Event(Parcel in) {
		type = in.readString();
		time = in.readString();
		day = in.readString();
		note = in.readString();
	}

	public String getType() {
		return type;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getDay() {
		return day;
	}
	
	public String getNote() {
		return note;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(type);
		dest.writeString(time);
		dest.writeString(day);
		dest.writeString(note);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {

		public Event createFromParcel(Parcel in) {
			return new Event(in);
		}

		public Event[] newArray(int size) {
			return new Event[size];
		}
	};

}
