package com.jmgff.xu.doctortotal.view;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jmgff.xu.doctortotal.R;
import com.jmgff.xu.doctortotal.fragment.GuideFragment;
import com.jmgff.xu.doctortotal.fragment.MenuFargment;
import com.jmgff.xu.doctortotal.fragment.ScaleFragment;
import com.jmgff.xu.doctortotal.util.GMUtil;

import android.os.Bundle;

public class MainActivity extends SlidingFragmentActivity {

	private SlidingMenu sm;
	private ScaleFragment scaleFragment;
	private GuideFragment guideFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.titlebar));
		setContentView(R.layout.layout_main_content);
		setBehindContentView(R.layout.layout_menu);

		sm = getSlidingMenu();
		sm.setBehindWidth((int) (GMUtil.GET_SCREEN_WIDTH(this) * 0.4));
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.layout_menu, new MenuFargment()).commit();
		scaleFragment = new ScaleFragment();
		guideFragment = new GuideFragment();

		goScale();

	}

	public void goScale() {
		setTitle(R.string.scale);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.layout_main_content, scaleFragment).commit();
		sm.showContent();

	}

	public void goGuide() {
		setTitle(R.string.guide);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.layout_main_content, guideFragment).commit();
		sm.showContent();
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
