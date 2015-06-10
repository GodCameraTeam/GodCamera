package com.godpose.camera;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;

public class PersonInformation extends ListFragment{
	private ListView list;
    private View view;

    private final UMSocialService mController = UMServiceFactory
            .getUMSocialService(Constants.DESCRIPTOR);

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        view=inflater.inflate(R.layout.person,container,false);
        initViewListener(view);
        setShareContent();
        return view;
    }

    private void startActivity(Class<?>cls){
        Intent intent = new Intent(getActivity(), cls);
        this.startActivity(intent);
    }
    /**
     * @功能描述 : 初始化视图控件，比如Button
     */
    private void setShareContent() {
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(getActivity(), "1104704300",
                "dAIyrTfCKruVlIKv");
        qZoneSsoHandler.addToSocialSDK();
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(getActivity(), "1104704300",
                "dAIyrTfCKruVlIKv");
        qqSsoHandler.addToSocialSDK();
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
    }
    private void initViewListener(View rootView) {

        // 分享(先选择平台)
      //  rootView.findViewById(R.id.open_share).setOnClickListener(this);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            // create fragment and add it to Activity.
            super.onCreate(savedInstanceState);
            //  b = savedInstanceState;
            PersonAdapter adapter = new PersonAdapter(getActivity());
            setListAdapter(adapter);
        }



    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        System.out.println("Click On List Item!!!");
        super.onListItemClick(l, v, position, id);
        switch(position) {
            case 0: //关于


                break;
            case 1:
                //分享
                //  Intent intent=new Intent(Intent.ACTION_SEND);
                //  intent.setType("text/plain");
                //  intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                //  intent.putExtra(Intent.EXTRA_TEXT, "I would like to share this with you...");
                //  startActivity(Intent.createChooser(intent, getTitle()));
                break;
            case 2:
                //帮助
                //  startActivity(HelpActivity.class);
                break;
            case 3:
                //意见反馈
                // AppConnect.getInstance(MoreActivity.this).showFeedback();
                 break;
            case 4://退出
                //AlertDlgUtil.AlertExit(MoreActivity.this);
                break;
            case 5:
                mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT,
                        SHARE_MEDIA.DOUBAN,
                        SHARE_MEDIA.RENREN);
                mController.setShareContent("超炫酷的创意摄影APP，不信你点击：http://www.godpose.com !");
                mController.openShare(getActivity(), false);
                break;
        }
    }
//    public void onClick(View v) {
//        int id = v.getId();
//        if (id == R.id.open_share) {
//            mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
//                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT,
//                    SHARE_MEDIA.DOUBAN,
//                    SHARE_MEDIA.RENREN);
//            mController.openShare(getActivity(), false);
//        }
//
//    }

//
//    public void onListItemClick(AdapterView<?> parent, View view,
//                            int position, long id) {
//            switch(position){
//                case 0: //关于
//                    mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
//                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT,
//                    SHARE_MEDIA.DOUBAN,
//                    SHARE_MEDIA.RENREN);
//            mController.openShare(getActivity(), false);
//
//                    break;
//                case 1:
//                    //分享
//                  //  Intent intent=new Intent(Intent.ACTION_SEND);
//                  //  intent.setType("text/plain");
//                  //  intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//                  //  intent.putExtra(Intent.EXTRA_TEXT, "I would like to share this with you...");
//                  //  startActivity(Intent.createChooser(intent, getTitle()));
//                    break;
//                case 2:
//                    //帮助
//                  //  startActivity(HelpActivity.class);
//                    break;
//                case 3:
//                    //意见反馈
//                   // AppConnect.getInstance(MoreActivity.this).showFeedback();
//                   // break;
//                case 4://退出
//                    //AlertDlgUtil.AlertExit(MoreActivity.this);
//                    //break;
//
//            }
//    }
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    /**使用SSO授权必须添加如下代码 */
    UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode) ;
    if(ssoHandler != null){
        ssoHandler.authorizeCallBack(requestCode, resultCode, data);
    }
}
}
