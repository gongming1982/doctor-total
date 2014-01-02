package com.jmgff.xu.doctortotal.view;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.jmgff.xu.doctortotal.R;
import com.jmgff.xu.doctortotal.database.DBAdapter;
import com.jmgff.xu.doctortotal.model.Answer;
import com.jmgff.xu.doctortotal.model.Question;
import com.jmgff.xu.doctortotal.model.Scale;
import com.jmgff.xu.doctortotal.util.GMConstants;
import com.jmgff.xu.doctortotal.util.GMUtil;

public class ScaleMainActivity extends SherlockActivity {

	protected static final int FINISH_LODING = 1;

	private Context mContext;

	private String scaleName;
	private Scale scale;

	private TextView tvScontent;
	private TextView tvSname;
	private TextView tvSremarks;

	private Button btnStartScale;

	private ProgressDialog pd;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			pd.dismiss();
			switch (msg.what) {
			case FINISH_LODING:
				GMUtil.showToast(
						mContext,
						getResources().getString(
								R.string.toast_message_loding_finish), 0, 0);
				tvSname.setText(scale.getSname());
				tvScontent.setText(scale.getContent());
				tvSremarks.setText(scale.getSremarks());
				printScale();
				break;

			default:
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scale_main);
		mContext = this;

		scaleName = getIntent().getStringExtra("scaleName");

		ActionBar ab = getSupportActionBar();

		ab.setTitle(scaleName);
		ab.setBackgroundDrawable(getResources()
				.getDrawable(R.drawable.titlebar));

		pd = new ProgressDialog(this);

		pd.setCancelable(false);
		pd.setMessage(getResources().getString(
				R.string.progress_dialog_title_waitting));

		tvSname = (TextView) findViewById(R.id.tv_activity_scale_main_sname);
		tvScontent = (TextView) findViewById(R.id.tv_activity_scale_main_scontent);
		tvSremarks = (TextView) findViewById(R.id.tv_activity_scale_main_sremarks);
		btnStartScale = (Button) findViewById(R.id.btn_activity_scale_main_start_scale);
		btnStartScale.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent(mContext, QuestionActivity.class);
				_Intent.putExtra("scale", scale);
				_Intent.putExtra("scaleName", scaleName);
				mContext.startActivity(_Intent);
			}
		});

		initScaleData();

	}

	private void initScaleData() {
		pd.show();
		new Thread() {
			public void run() {
				scale = new Scale();

				DBAdapter dbAdapter = new DBAdapter(mContext);
				Cursor _scaleCursor = dbAdapter
						.select(GMConstants.TABLE_NAME,
								null,
								GMConstants.TABLE_COLUMN_SNAME + "=? and "
										+ GMConstants.TABLE_COLUMN_STYPE + "=?",
								new String[] { scaleName,
										GMConstants.TABLE_TYPE_SCALE }, null,
								null, null);
				while (_scaleCursor.moveToNext()) {
					scale.setSid(_scaleCursor.getInt(0));
					scale.setStype(_scaleCursor.getInt(1));
					scale.setPid(_scaleCursor.getInt(2));
					scale.setSname(_scaleCursor.getString(3));
					scale.setContent(_scaleCursor.getString(4));
					scale.setSpoint(_scaleCursor.getFloat(5));
					scale.setSremarks(_scaleCursor.getString(8));
					scale.setDescription(_scaleCursor.getString(9));

				}

				if (_scaleCursor != null) {
					_scaleCursor.close();
					_scaleCursor = null;
				}

				ArrayList<Question> questions = new ArrayList<Question>();
				Cursor _questionCursor = dbAdapter.select(
						GMConstants.TABLE_NAME, null,
						GMConstants.TABLE_COLUMN_PID + "=? and "
								+ GMConstants.TABLE_COLUMN_STYPE + "=?",
						new String[] { scale.getSid().toString(),
								GMConstants.TABLE_TYPE_QUESTION }, null, null,
						null);

				// Cursor _questionCursor = dbAdapter.select(
				// "SELECT * FROM scale WHERE pid=? and stype=?", new String[] {
				// scale.getSid().toString(),
				// GMConstants.TABLE_TYPE_QUESTION });

				while (_questionCursor.moveToNext()) {
					Question question = new Question();
					question.setSid(_questionCursor.getInt(0));
					question.setStype(_questionCursor.getInt(1));
					question.setPid(_questionCursor.getInt(2));
					question.setSname(_questionCursor.getString(3));
					question.setContent(_questionCursor.getString(4));
					question.setSpoint(_questionCursor.getInt(5));
					question.setSremarks(_questionCursor.getString(8));

					ArrayList<Answer> answers = new ArrayList<Answer>();

					Cursor _answerCursor = dbAdapter.select(
							GMConstants.TABLE_NAME, null,
							GMConstants.TABLE_COLUMN_PID + "=? and "
									+ GMConstants.TABLE_COLUMN_STYPE + "=?",
							new String[] { question.getSid() + "",
									GMConstants.TABLE_TYPE_ANSWER }, null,
							null, null);

					while (_answerCursor.moveToNext()) {
						Answer answer = new Answer();
						answer.setSid(_answerCursor.getInt(0));
						answer.setStype(_answerCursor.getInt(1));
						answer.setPid(_answerCursor.getInt(2));
						answer.setSname(_answerCursor.getString(3));
						answer.setContent(_answerCursor.getString(4));
						answer.setSpoint(_answerCursor.getInt(5));
						answer.setSremarks(_answerCursor.getString(8));

						answers.add(answer);
					}

					question.setAnswers(answers);

					if (_answerCursor != null) {
						_answerCursor.close();
						_answerCursor = null;
					}
					questions.add(question);

				}
				scale.setQuestions(questions);
				if (_questionCursor != null) {
					_questionCursor.close();
					_questionCursor = null;
				}

				if (dbAdapter != null) {
					dbAdapter.close();
					dbAdapter = null;
				}

				Message msg = mHandler.obtainMessage();
				msg.what = FINISH_LODING;
				mHandler.sendMessage(msg);

			};
		}.start();
	}

	private void printScale() {
		System.out.println(scale.getSname());
		for (Question question : scale.getQuestions()) {
			System.out.println(question.getSname());
			for (Answer answer : question.getAnswers()) {

				System.out.println(answer.getContent());
				System.out.println(answer.getSpoint());
			}
		}
	}
}
