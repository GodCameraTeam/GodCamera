package com.example.camera;

import java.util.ArrayList;
import java.util.List;

import android.R.menu;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class MainActivity extends FragmentActivity implements OnClickListener,
               OnPageChangeListener
{
	private ViewPager mViewPager;
	private List<Fragment> mTabs  = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private String[] mTitles =  new String[]
    		{
    		"Camera","Pitcure","Person Information"
    		};
    private List<ImageButton> mTabIndicators = new ArrayList<ImageButton>();
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initDatas();
		mViewPager.setAdapter(mAdapter);
		initEvent();
	}

	private void initDatas() {
		// TODO Auto-generated method stub
		Fragment  camerafragment = new CameraFragment();
		mTabs.add(camerafragment);
		Fragment picturefragment = new PictureFragment();
		mTabs.add(picturefragment);
		Fragment personfragment = new PersonInformation();
		mTabs.add(personfragment);
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mTabs.size();
			}
			
			@Override
			public Fragment getItem(int position) {
				// TODO Auto-generated method stub
				return mTabs.get(position);
			}
		};
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		mViewPager.setOnPageChangeListener(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		ImageButton imagebuttoncamera = (ImageButton) findViewById(R.id.ImgButCamera);
		mTabIndicators.add(imagebuttoncamera);
		ImageButton imagebuttonpicture = (ImageButton) findViewById(R.id.ImgButPicture);
		mTabIndicators.add(imagebuttonpicture);
		ImageButton imagebuttoninpersonformation = (ImageButton) findViewById(R.id.ImgButPerson);
		mTabIndicators.add(imagebuttoninpersonformation);
		
		imagebuttoncamera.setOnClickListener(this);
		imagebuttoninpersonformation.setOnClickListener(this);
		imagebuttonpicture.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		 clickTab(view);
	}

	private void clickTab(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.ImgButCamera:
			mViewPager.setCurrentItem(0,false);
			break;
		case R.id.ImgButPicture:
			mViewPager.setCurrentItem(1,false);
			break;
		case R.id.ImgButPerson:
			mViewPager.setCurrentItem(2,false);
			break;

		default:
			break;
		}
	}
}
