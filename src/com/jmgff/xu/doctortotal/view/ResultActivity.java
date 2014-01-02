package com.jmgff.xu.doctortotal.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.jmgff.xu.doctortotal.R;
import com.jmgff.xu.doctortotal.model.Scale;

public class ResultActivity extends SherlockActivity {

	private Scale scale;
	private float total;
	private TextView tvTotal, tvDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Intent intent = getIntent();

		scale = (Scale) intent.getSerializableExtra("scale");

		getSupportActionBar().setTitle(scale.getSname());
		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.titlebar));

		total = intent.getFloatExtra("total", 0.0f);

		tvTotal = (TextView) findViewById(R.id.tv_activity_result_total);
		tvDescription = (TextView) findViewById(R.id.tv_activity_result_scale_description);

		tvTotal.setText(total + "");
		tvDescription.setText(scale.getDescription());

	}

}
