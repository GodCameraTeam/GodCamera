package com.godpose.camera;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by chenyee on 15/6/8.
 */
public class PersonAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private Context context;
    public static final List<String> more_list = new ArrayList<String>();//为条目提供数据
    public PersonAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        more_list.add(context.getResources().getString(R.string.item1));
        more_list.add(context.getResources().getString(R.string.item2));
        more_list.add(context.getResources().getString(R.string.item3));
        more_list.add(context.getResources().getString(R.string.item4));
        more_list.add(context.getResources().getString(R.string.item5));
    }
    @Override
    public int getCount() {
        return more_list.size();  //条目数量
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(
                    R.layout.person_item, null);
        }
        ImageView icon = (ImageView) convertView.findViewById(R.id.item_icon);//设置每个条目的图标
        if(0 == position){
          //  icon.setBackgroundResource(R.drawable.icon_square);
        }else if(1 == position){
          //  icon.setBackgroundResource(android.R.drawable.ic_menu_share);
        }else if(2 == position){
          //  icon.setBackgroundResource(R.drawable.icon_setting);
        }else if(3 == position){
          //  icon.setBackgroundResource(android.R.drawable.ic_menu_edit);
        }else{
          //  icon.setBackgroundResource(R.drawable.menu_exit);
        }
        TextView text = (TextView) convertView.findViewById(R.id.more_item_text); //设置条目的文字说明
        text.setText(more_list.get(position));
        return convertView;
    }

}
