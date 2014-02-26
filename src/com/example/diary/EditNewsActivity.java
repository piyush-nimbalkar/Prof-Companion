package com.example.diary;

import model.News;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditNewsActivity extends Activity implements OnClickListener {

	private News newsItem;
	private int newsItemPosition;
	private EditText editTextNewsTitle, editTextNewsHighlights, editTextNewsKeyword;
	private Button buttonEditNewsDone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_news);

		editTextNewsTitle = (EditText) findViewById(R.id.editTextEditNewsTitle);
		editTextNewsKeyword = (EditText) findViewById(R.id.editTextEditNewsKeyword);
		editTextNewsHighlights = (EditText) findViewById(R.id.editTextEditNewsHighlights);
		buttonEditNewsDone = (Button) findViewById(R.id.buttonEditNewsDone);

		newsItem = getIntent().getParcelableExtra("NewsItem");
		newsItemPosition = getIntent().getIntExtra("Position", 0);

		editTextNewsTitle.setText(newsItem.getTitle());
		editTextNewsKeyword.setText(newsItem.getKeyword());
		editTextNewsHighlights.setText(newsItem.getHighlights());

		buttonEditNewsDone.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonEditNewsDone:
			newsItem.setTitle(editTextNewsTitle.getText().toString());
			newsItem.setKeyword(editTextNewsKeyword.getText().toString());
			newsItem.setHighlights(editTextNewsHighlights.getText().toString());
			finish();
			break;
		}
	}

	public void finish() {
		Intent data = new Intent();
		data.putExtra("NewsItem", newsItem);
		data.putExtra("Position", newsItemPosition);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
