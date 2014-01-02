package com.jmgff.xu.doctortotal.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jmgff.xu.doctortotal.R;
import com.jmgff.xu.doctortotal.database.DBAdapter;
import com.jmgff.xu.doctortotal.util.GMConstants;
import com.jmgff.xu.doctortotal.view.ScaleMainActivity;

public class ScaleFragment extends Fragment {

	protected static final String TAG = "ScaleFragment";
	private ListView lv;
	private List<String> scales;
	private DBAdapter dbAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("lifecycle", "onCreateView");
		return inflater.inflate(R.layout.fragment_scale, null);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dbAdapter = new DBAdapter(getActivity());
		Log.i("lifecycle", "onCreate");

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("lifecycle", "onActivityCreated");
		scales = lodingScaleData();

		lv = (ListView) getView().findViewById(R.id.lv_fragment_scale_list);

		lv.setAdapter(new MyAdapter());
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.i(TAG, "click");
				TextView _tv=(TextView) arg1.findViewById(R.id.tv_item_scale_listview);
				String scaleName=_tv.getText().toString();
				Intent _Intent=new Intent(getActivity(), ScaleMainActivity.class);
				_Intent.putExtra("scaleName", scaleName);
				startActivity(_Intent);

			}
		});
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return scales.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return scales.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			View view = inflater.inflate(R.layout.item_scale_listview, null);
			TextView tv = (TextView) view
					.findViewById(R.id.tv_item_scale_listview);
			tv.setText(scales.get(position));
			return view;
		}

	}

	public List<String> lodingScaleData() {
		List<String> list = new ArrayList<String>();
		Cursor cursor = dbAdapter
				.select(GMConstants.TABLE_NAME,
						new String[] { GMConstants.TABLE_COLUMN_SNAME },
						GMConstants.TABLE_COLUMN_STYPE + "=?",
						new String[] { GMConstants.TABLE_TYPE_SCALE }, null,
						null, null);
		while (cursor.moveToNext()) {
			list.add(cursor.getString(0));
		}
		if (cursor != null) {
			cursor.close();
			cursor = null;
		}
		dbAdapter.close();
		return list;
	}
}
