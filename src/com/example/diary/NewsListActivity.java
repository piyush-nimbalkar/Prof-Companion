package com.example.diary;

import java.util.ArrayList;

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

/* An activity in which you can see a list of all the news and returns
 * back the updated list of news to the caller activity
 */
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

		/* Using an adaptor to show the list of news in a custom layout
		 */
		final NewsArrayAdapter adapter = new NewsArrayAdapter(this, news);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	/* On click of the item in the list, it will open an activity to edit the news
	 * and pass the current news item to it with its position in the list
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		News news_item = (News) parent.getItemAtPosition(position);
		Intent i = new Intent(context, EditNewsActivity.class);
		i.putExtra("NewsItem", news_item);
		i.putExtra("Position", position);
		startActivityForResult(i, REQUEST_EDIT);
	}

	/* Update the list of news depending on the results from the returned activities
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			News news_item = (News) data.getExtras().get("NewsItem");
			switch (requestCode) {
			case REQUEST_EDIT:
				/* In case of an edit request it replaces the original object with the updated
				 * object that was received from the edit activity
				 */
				int position = data.getIntExtra("Position", 0);
				news.remove(position);
				news.add(position, news_item);
				break;
			case REQUEST_ADD:
				/* In case of an add request it will just add the object at the end of the list
				 */
				news.add(news_item);
				break;
			}
		} else if (resultCode == RESULT_DELETED) {
			/* In case of a delete request it remove the object from its position
			 */
			int position = data.getIntExtra("Position", -1);
			if (requestCode == REQUEST_EDIT)
				news.remove(position);
		}
		final NewsArrayAdapter adapter = new NewsArrayAdapter(this, news);
		listView.setAdapter(adapter);
		super.onActivityResult(requestCode, resultCode, data);
	}

	/* Menu options for the current screen on the action bar.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.news_list_menu, menu);
		return true;
	}

	/* Menu options on select callback for this screen. Currently, it only
	 * lets you add a news item
	 */
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

	/* Return the updated or new news item object back to the caller activity
	 */
	public void finish() {
		Intent data = new Intent();
		data.putParcelableArrayListExtra("News", news);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
