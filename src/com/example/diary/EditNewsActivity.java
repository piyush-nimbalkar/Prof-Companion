package com.example.diary;

import model.News;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

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

		newsItem = getIntent().getParcelableExtra("NewsItem");
		newsItemPosition = getIntent().getIntExtra("Position", 0);

		editTextNewsTitle.setText(newsItem.getTitle());
		editTextNewsKeyword.setText(newsItem.getKeyword());
		editTextNewsHighlights.setText(newsItem.getHighlights());
	}

	public void finish() {
		Intent data = new Intent();
		data.putExtra("NewsItem", newsItem);
		data.putExtra("Position", newsItemPosition);
		int resultCode = (newsItem == null) ? RESULT_DELETED : RESULT_OK;
		setResult(resultCode, data);
		super.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_news_menu, menu);
		return true;
	}

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
