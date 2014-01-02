package com.jmgff.xu.doctortotal.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.jmgff.xu.doctortotal.R;
import com.jmgff.xu.doctortotal.model.Answer;
import com.jmgff.xu.doctortotal.model.Question;
import com.jmgff.xu.doctortotal.model.Scale;
import com.jmgff.xu.doctortotal.util.GMUtil;

public class QuestionActivity extends SherlockActivity {

	/**
	 * 题目展示的主activity questions:所有问题的集合 currentQuestionNum:当前题目号
	 * tvTotalPoint:显示总分的文本 tvCurrentQuestionNum:显示当前题目号的文本
	 * tvCurrentQuestionName:当前题目名字的文本 totalPoints,装有题号和分数的map total:总分
	 * 
	 */

	private Context mContext;

	private Scale scale;
	private ArrayList<Question> questions;
	private Question currentQuestion;
	private int currentQuestionNum = 1;

	private Button btnPrevious, btnNext;
	private RadioGroup rgQuestion;

	private TextView tvTotalPoint, tvCurrentQuestionNum, tvCurrentQuestionName;

	private Map<Integer, Float> totalPoints;
	private Float total = 0.0f;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		mContext = this;

		initTitle();
		initData();

		initView();
		initAndChangeRadioGroup();
		changeBtnState();

	}

	/**
	 * 根据当前题号改变btn状态
	 */
	private void changeBtnState() {
		if (1 == currentQuestionNum) {
			// 第一题
			btnPrevious.setVisibility(View.INVISIBLE);
			btnNext.setText(R.string.btn_next);
		} else if (questions.size() == currentQuestionNum) {
			// 最后一题
			btnPrevious.setVisibility(View.VISIBLE);
			btnNext.setText(R.string.btn_complete);
		} else {
			// 中间题目
			btnPrevious.setVisibility(View.VISIBLE);
			btnNext.setText(R.string.btn_next);
		}

		tvCurrentQuestionNum.setText(getResources()
				.getString(R.string.question) + currentQuestionNum);
	}

	/**
	 * 初始化view
	 */
	private void initView() {
		btnPrevious = (Button) findViewById(R.id.btn_activity_question_previous);
		btnNext = (Button) findViewById(R.id.btn_activity_question_next);
		tvTotalPoint = (TextView) findViewById(R.id.tv_activity_question_total_point);
		tvCurrentQuestionNum = (TextView) findViewById(R.id.tv_activity_question_current_question_num);
		rgQuestion = (RadioGroup) findViewById(R.id.rg_activity_question);
		tvCurrentQuestionName = (TextView) findViewById(R.id.tv_activity_question_question_name);

		/**
		 * 点击按钮需要做 0.改变题号. 1.改变当前问题 2.改变按钮状态 2.5.改变总分 3.改变屏幕显示(当前题号,总分,题目名称)
		 */
		// 后退按钮点击事件
		btnPrevious.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// if (currentQuestionNum > 1) {
				if (totalPoints.containsKey(currentQuestionNum)) {
					totalPoints.remove(currentQuestionNum);
				}
				totalPoints.remove(currentQuestionNum - 1);
				setTotal();

				currentQuestionNum = currentQuestionNum - 1;
				currentQuestion = questions.get(currentQuestionNum - 1);
				initAndChangeRadioGroup();
				changeBtnState();
				// } else {
				// GMUtil.showToast(mContext,
				// getResources()
				// .getString(R.string.is_first_question), 0,
				// 0);
				// }
			}
		});

		// next按钮点击事件
		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (totalPoints.containsKey(currentQuestionNum)) {
					String _text = ((Button) v).getText().toString();
					if (!_text.equals(getResources().getString(
							R.string.btn_complete))) {
						currentQuestionNum = currentQuestionNum + 1;
						currentQuestion = questions.get(currentQuestionNum - 1);
						initAndChangeRadioGroup();
					} else {
						// 完成答题
						GMUtil.showToast(mContext, "回答结束", 0, 0);
						Intent _Intent = new Intent(mContext,
								ResultActivity.class);
						_Intent.putExtra("scale", scale);
						_Intent.putExtra("total", total);
						
						QuestionActivity.this.startActivity(_Intent);

					}

					changeBtnState();
				} else {
					GMUtil.showToast(mContext, "请回答问题", 0, 0);
				}

			}
		});
	}

	/**
	 * 初始化答案组
	 */
	private void initAndChangeRadioGroup() {
		rgQuestion.removeAllViews();
		tvCurrentQuestionName.setText(currentQuestion.getSname());
		for (int i = 0; i < currentQuestion.getAnswers().size(); i++) {
			RadioButton rb = new RadioButton(this);
			final Answer answer = currentQuestion.getAnswers().get(i);
			rb.setTextSize(20);
			rb.setTextColor(getResources().getColor(R.color.black));
			rb.setText(currentQuestion.getAnswers().get(i).getContent());
			rb.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					for (int j = 0; j < currentQuestion.getAnswers().size(); j++) {
						currentQuestion.getAnswers().get(j).setCheck(false);
					}
					answer.setCheck(true);
					totalPoints.put(currentQuestionNum, answer.getSpoint());
					setTotal();
				}
			});

			rgQuestion.addView(rb);
		}
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		scale = (Scale) getIntent().getSerializableExtra("scale");

		questions = scale.getQuestions();
		currentQuestion = questions.get(currentQuestionNum - 1);
		totalPoints = new HashMap<Integer, Float>();
	}

	/**
	 * 初始化title
	 */
	private void initTitle() {
		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.titlebar));
		getSupportActionBar().setTitle(getIntent().getStringExtra("scaleName"));
	}

	/**
	 * 设置总分
	 */
	private void setTotal() {
		if (totalPoints != null && totalPoints.size() >= 0) {
			total = 0.0f;
			for (int i = 0; i < totalPoints.size(); i++) {

				total = total + totalPoints.get(i + 1);
			}

			tvTotalPoint.setText(total + "");
		}
	}

}
