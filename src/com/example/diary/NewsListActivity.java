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

	private final int REQUEST_CODE1 = 1;
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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		News news_item = (News) parent.getItemAtPosition(position);
		Intent i = new Intent(context, NewsActivity.class);
		i.putExtra("NewsItem", news_item);
		i.putExtra("Position", position);
		startActivityForResult(i, REQUEST_CODE1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE1 && resultCode == RESULT_OK){
			News news_item = (News) data.getExtras().get("NewsItem");
			int position = data.getIntExtra("Position", 0);
			news.remove(position);
			news.add(position, news_item);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void finish() {
		Intent data = new Intent();
		data.putParcelableArrayListExtra("News", news);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
