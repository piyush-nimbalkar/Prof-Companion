package com.example.diary;

import java.util.ArrayList;

import model.Event;
import model.News;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class NewsListActivity extends Activity implements OnItemClickListener {

	private final int REQUEST_EDIT= 1;
	private final int REQUEST_ADD = 2;
	private final int RESULT_DELETED = 3;

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
		Intent i = new Intent(context, EditNewsActivity.class);
		i.putExtra("NewsItem", news_item);
		i.putExtra("Position", position);
		startActivityForResult(i, REQUEST_EDIT);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			News news_item = (News) data.getExtras().get("NewsItem");
			switch (requestCode) {
			case REQUEST_EDIT:
				int position = data.getIntExtra("Position", 0);
				news.remove(position);
				news.add(position, news_item);
				break;
			case REQUEST_ADD:
				news.add(news_item);
				break;
			}
		} else if (resultCode == RESULT_DELETED) {
			int position = data.getIntExtra("Position", -1);
			if (requestCode == REQUEST_EDIT)
				news.remove(position);
		}
		final NewsArrayAdapter adapter = new NewsArrayAdapter(this, news);
		listView.setAdapter(adapter);
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.news_list_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_news_item:
			Intent i = new Intent(context, EditNewsActivity.class);
			startActivityForResult(i, REQUEST_ADD);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void finish() {
		Intent data = new Intent();
		data.putParcelableArrayListExtra("News", news);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
