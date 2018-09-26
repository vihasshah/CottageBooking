package com.khushi.win10.cottagebooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Win10 on 14/09/2018.
 */

public class CustomNewsAdapter extends BaseAdapter {
    Context context;
    ArrayList<NewsModel> newslist;

    public CustomNewsAdapter(Context context, ArrayList<NewsModel> newslist) {
        this.context = context;
        this.newslist = newslist;
    }

    @Override
    public int getCount() {
        return newslist.size();
    }

    @Override
    public Object getItem(int position) {
        return newslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder{

        ImageView imageviewnews;
        TextView textitle;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return null;
        ViewHolder holder;
        if(convertView==null){
            holder= new CustomNewsAdapter.ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.single_row_propertynews,parent,false);
            holder.imageviewnews= (ImageView) convertView.findViewById(R.id.propertynews_image1);
            holder.textitle=(TextView)convertView.findViewById(R.id.propertynews_textview1);
            convertView.setTag(holder);
        } else {
            holder=(CustomNewsAdapter.ViewHolder)convertView.getTag();
        }
        holder.imageviewnews.setImageResource(newslist.get(position).getImagepropertynews());
        holder.textitle.setText(newslist.get(position).getTitle());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,PropertyNewsDetailActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
