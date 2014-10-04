package com.example.geoquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends ActionBarActivity {

	private Button trueButton;
	private Button falseButton;
	private Button nextButton;
	private TextView questionTextView;
	private int currentIndex = 0;
	private TrueFalse[] questionBank = new TrueFalse[] {
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, false),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true) };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_activity);

		questionTextView = (TextView) findViewById(R.id.question_text_view);
		updateQuestion();
		trueButton = (Button) findViewById(R.id.true_button);
		trueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(true);
			}
		});

		falseButton = (Button) findViewById(R.id.false_button);
		falseButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(false);
			}
		});

		nextButton = (Button) findViewById(R.id.next_button);
		nextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				currentIndex = (currentIndex + 1) % questionBank.length;
				updateQuestion();
			}
		});
	}

	private void updateQuestion() {
		int question = questionBank[currentIndex].getQuestion();
		questionTextView.setText(question);
	}

	private void checkAnswer(boolean userPressedTrue) {
		boolean answerIsTrue = questionBank[currentIndex].isTrueQuestion();
		int messageResourceId;

		if (userPressedTrue == answerIsTrue) {
			messageResourceId = R.string.correct_toast;
		} else {
			messageResourceId = R.string.incorrect_toast;
		}

		Toast.makeText(this, messageResourceId, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will automatically handle clicks on
		// the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}

}
