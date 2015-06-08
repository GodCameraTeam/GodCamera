package com.godpose.camera;


import com.godpose.camera.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;

public class CameraFragment extends Fragment{
    @Override
	public void onCreate(Bundle savedInstanceState)
	{
	// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.camera_styles, container, false);

	}



}
