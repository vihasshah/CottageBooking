package com.khushi.win10.cottagebooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.khushi.win10.cottagebooking.Model.NewsModel;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Win10 on 14/09/2018.
 */

public class CustomNewsAdapter extends BaseAdapter {
    private Context context;
    private List<NewsModel.DataBean> newslist;

    public CustomNewsAdapter(Context context, List<NewsModel.DataBean> newslist) {
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
        TextView textitle,textContent;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return null;
        ViewHolder holder;
        if(convertView==null){
            holder= new CustomNewsAdapter.ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.single_row_propertynews,parent,false);
            holder.imageviewnews= (ImageView) convertView.findViewById(R.id.propertynews_image1);
            holder.textitle=(TextView)convertView.findViewById(R.id.row_propertynews_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.row_propertynews_small_content);
            convertView.setTag(holder);
        } else {
            holder=(CustomNewsAdapter.ViewHolder)convertView.getTag();
        }
        String imgUrl;
        if(newslist.get(position).getImage().contains("http:")){
            imgUrl = newslist.get(position).getImage();
        }else {
            imgUrl = "http://" + Utils.SERVER_URL + newslist.get(position).getImage();
            Utils.log(imgUrl.trim());
        }
        Picasso.get().load(imgUrl.trim()).into(holder.imageviewnews);
        holder.textitle.setText(newslist.get(position).getTitle());
        holder.textContent.setText(newslist.get(position).getDetails());

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
