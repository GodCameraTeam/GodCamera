package com.godpose.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class PersonInformation extends ListFragment{
	private ListView list;
    private View view;


    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        view=inflater.inflate(R.layout.person,container,false);

        return view;
    }

    private void startActivity(Class<?>cls){
        Intent intent = new Intent(getActivity(), cls);
        this.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  b = savedInstanceState;
        PersonAdapter adapter = new PersonAdapter(getActivity());
        setListAdapter(adapter);


    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        System.out.println("Click On List Item!!!");
        super.onListItemClick(l, v, position, id);
    }

  //  @Override
   // public void onListItemClick(AdapterView<?> parent, View view,
   //                         int position, long id) {
//            switch(position){
//                case 0: //关于
//                 //   startActivity(AboutActivity.class);
//
//                    break;
//                case 1:
//                    //分享
//                  //  Intent intent=new Intent(Intent.ACTION_SEND);
//                  //  intent.setType("text/plain");
//                    intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//                    intent.putExtra(Intent.EXTRA_TEXT, "I would like to share this with you...");
//                    startActivity(Intent.createChooser(intent, getTitle()));
//                    break;
//                case 2:
//                    //帮助
//                    startActivity(HelpActivity.class);
//                    break;
//                case 3:
//                    //意见反馈
//                    AppConnect.getInstance(MoreActivity.this).showFeedback();
//                    break;
//                case 4://退出
//                    AlertDlgUtil.AlertExit(MoreActivity.this);
//                    break;
//            }
 //   }
}
