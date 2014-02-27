package model;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {

	private String title;
	private String keyword;
	private String highlights;

	public News() {
		title = "";
		keyword = "";
		highlights = "";
	}

	public News(String _title, String _keyword, String _highlights) {
		title = _title;
		keyword = _keyword;
		highlights = _highlights;
	}

	public News(Parcel in) {
		title = in.readString();
		keyword = in.readString();
		highlights = in.readString();
	}

	public String getTitle() {
		return title;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getHighlights() {
		return highlights;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setHighlights(String highlights) {
		this.highlights = highlights;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(keyword);
		dest.writeString(highlights);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {

		public News createFromParcel(Parcel in) {
			return new News(in);
		}

		public News[] newArray(int size) {
			return new News[size];
		}
	};

}
