package com.example.diary;

import model.News;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/* An activity which facilitates editing of the news information and
 * returning it back to the caller Activity
 */
public class EditNewsActivity extends Activity {

	private final int RESULT_DELETED = 3;

	private News newsItem;
	private int newsItemPosition;
	private EditText editTextNewsTitle, editTextNewsHighlights, editTextNewsKeyword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_news);

		editTextNewsTitle = (EditText) findViewById(R.id.editTextEditNewsTitle);
		editTextNewsKeyword = (EditText) findViewById(R.id.editTextEditNewsKeyword);
		editTextNewsHighlights = (EditText) findViewById(R.id.editTextEditNewsHighlights);

		newsItemPosition = getIntent().getIntExtra("Position", -1);
		newsItem = (News) ((newsItemPosition == -1) ? new News() : getIntent().getParcelableExtra("NewsItem"));

		/* Fill out the news information on the screen from the received news object
		 */		
		editTextNewsTitle.setText(newsItem.getTitle());
		editTextNewsKeyword.setText(newsItem.getKeyword());
		editTextNewsHighlights.setText(newsItem.getHighlights());
	}

	/* Return the updated news object back to the caller activity
	 */
	public void finish() {
		Intent data = new Intent();
		data.putExtra("NewsItem", newsItem);
		data.putExtra("Position", newsItemPosition);
		int resultCode = (newsItem == null) ? RESULT_DELETED : RESULT_OK;
		setResult(resultCode, data);
		super.finish();
	}

	/* Menu options for the current screen on the action bar.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_news_menu, menu);
		return true;
	}

	/* Menu options on select callback for this screen. Currently, it only
	 * lets you edit and delete the news item
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.save_news:
			newsItem.setTitle(editTextNewsTitle.getText().toString());
			newsItem.setKeyword(editTextNewsKeyword.getText().toString());
			newsItem.setHighlights(editTextNewsHighlights.getText().toString());
			finish();
			return true;
		case R.id.delete_news:
			newsItem = null;
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
