package com.jmgff.xu.doctortotal.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jmgff.xu.doctortotal.R;
import com.jmgff.xu.doctortotal.view.MainActivity;

public class MenuFargment extends ListFragment {

	private List<String> menuList;
	private ListView lv;
	private MainActivity _MainActivity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_menu, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		lv = getListView();
		menuList = new ArrayList<String>();

		menuList.add("量表");
		menuList.add("医学指南");
		menuList.add("更多");
		menuList.add("关于我们");
		menuList.add("menu5");

		lv.setAdapter(new MyAdapter());
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				Activity _Activity = getActivity();
				if (_Activity != null) {
					_MainActivity = (MainActivity) _Activity;

					switch (arg2) {
					case 0:
						_MainActivity.goScale();
						break;
					case 1:
						_MainActivity.goGuide();
						break;
					default:
						break;
					}
				}
			}
		});

	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return menuList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return menuList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			View view = inflater.inflate(R.layout.item_menu_listview, null);
			TextView tv = (TextView) view
					.findViewById(R.id.tv_item_menu_listview);
			tv.setText(menuList.get(arg0));
			return view;
		}

	}
}
