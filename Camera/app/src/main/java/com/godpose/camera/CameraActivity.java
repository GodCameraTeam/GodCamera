package com.godpose.camera;

import com.godpose.camera.GalleryAdapter.OnItemClickLitener;;
import android.app.Activity;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CameraActivity extends Activity implements SurfaceHolder.Callback, Camera.ShutterCallback, Camera.PictureCallback{
    Camera mCamera;
    ImageView image;
    SurfaceView mPreview;
    int images=R.drawable.hat;
    static int TURN=90;
    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;
    private List<Integer> mDatas2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity_main);
        //得到图片
        image=(ImageView)findViewById(R.id.mView);
        //image.setImageResource(images);
        mPreview=(SurfaceView)findViewById(R.id.preview);
        //设置相机surface宽高比
        LayoutParams lp = mPreview.getLayoutParams();
        lp.width = LayoutParams.MATCH_PARENT;
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = width * 4 /3;
        lp.width = width;
        lp.height = height;
        mPreview.setLayoutParams(lp);
        mPreview.getHolder().addCallback(this);
        mPreview.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mCamera=Camera.open();
        //mCamera.setDisplayOrientation(90);
        initDatas();
        //得到控件  
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        //设置布局管理器  
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器  
        mAdapter = new GalleryAdapter(this, mDatas2);
        mAdapter.setOnItemClickLitener(new OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                image.setImageResource(mDatas.get(position));
                //Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
    private void initDatas()
    {
        mDatas = new ArrayList<Integer>(Arrays.asList(
                R.drawable.hat,R.drawable.hat2,R.drawable.man1,R.drawable.hat,R.drawable.hat2,R.drawable.man1));
        mDatas2 = new ArrayList<Integer>(Arrays.asList(
                R.drawable.demo1,R.drawable.demo2,R.drawable.demo1,R.drawable.demo1,R.drawable.demo1,R.drawable.demo1));
    }

    @Override
    public void onPause(){
        super.onPause();
        mCamera.stopPreview();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mCamera.release();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(mPreview.getHolder());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters params = mCamera.getParameters();
        List<Camera.Size> sizes =params.getSupportedPreviewSizes();
        Camera.Size selected = sizes.get(0);
        params.setPreviewSize(selected.width,selected.height);
        mCamera.setParameters(params);
        mCamera.setDisplayOrientation(90);
        mCamera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void onCancelClick(View v){
        finish();
    }

    public void onSnapClick(View v){
        mCamera.takePicture(this, null, null, this);
    }

    public void onTurnClick(View v){
        if(TURN==0) {
            TURN=90;
            mCamera.setDisplayOrientation(90);
        }
        else{
            TURN=0;
            mCamera.setDisplayOrientation(0);
        }
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        try {

            SharedPreferences perferences;
            SharedPreferences.Editor editor;
            perferences = getSharedPreferences("totalpicture",MODE_PRIVATE);
            editor = perferences.edit();
            int total = perferences.getInt("total",0);
            total++;
            editor.putInt("total",total);
            editor.commit();
//            Time t= new Time();
//            t.setToNow();
//            String month = Integer.toString(t.month);
//            String date = Integer.toString(t.monthDay);
//            String hour = Integer.toString(t.hour);
//            String minute = Integer.toString(t.minute);
//            String second = Integer.toString(t.second);

            FileOutputStream out = openFileOutput(Integer.toString(total)+".jpg",Activity.MODE_PRIVATE);
            out.write(data);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    @Override
    public void onShutter() {
        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
    }
}
