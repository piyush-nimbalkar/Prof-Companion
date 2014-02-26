package com.example.diary;

import model.News;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class EditNewsActivity extends Activity {

	private News news_item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_news);

		news_item = getIntent().getParcelableExtra("NewsItem");

		Toast.makeText(this, news_item.getTitle(), Toast.LENGTH_LONG).show();
	}

}
