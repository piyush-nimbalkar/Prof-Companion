package com.example.diary;

import java.util.ArrayList;

import model.News;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class NewsListActivity extends Activity implements OnItemClickListener {

	private Context context;
	private ListView listView;
	private ArrayList<News> news = new ArrayList<News>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitvity_news_list);
		context = this;
		listView = (ListView) findViewById(R.id.listViewNews);
		news = getIntent().getParcelableArrayListExtra("News");

		final NewsArrayAdapter adapter = new NewsArrayAdapter(this, news);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

	public void finish() {
		Intent data = new Intent();
		data.putParcelableArrayListExtra("News", news);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
