package com.example.diary;

import java.util.ArrayList;

import model.Course;
import model.News;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class NewsActivity extends Activity {

	private ArrayList<News> news = new ArrayList<News>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		news = getIntent().getParcelableArrayListExtra("News");

		Toast.makeText(this, news.get(0).getTitle(), Toast.LENGTH_LONG).show();
	}

}
