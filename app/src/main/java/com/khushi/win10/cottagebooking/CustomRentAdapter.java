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
import com.khushi.win10.cottagebooking.Model.RentListModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win10 on 13/09/2018.
 */

public class CustomRentAdapter extends BaseAdapter {
    private Context context = null;
    private List<RentListModel.DataBean> rentDetailsList;

    public CustomRentAdapter(Context context, List<RentListModel.DataBean> rentDetailsList) {
        this.context = context;
        this.rentDetailsList = rentDetailsList;
    }

    @Override
    public int getCount() {
        return rentDetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return rentDetailsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder{
        ImageView imageView;
        TextView textname;
        TextView textlocation;
        TextView textrating;
        TextView textrank;
        TextView textprice;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return null;
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.single_row_rent,parent,false);
            holder.textname=(TextView)convertView.findViewById(R.id.row_rent_cottagename);
            holder.textlocation=(TextView)convertView.findViewById(R.id.row_rent_location);
            holder.textrating=(TextView)convertView.findViewById(R.id.row_rent_ratings);
            holder.textrank=(TextView)convertView.findViewById(R.id.row_rent_rank);
            holder.textprice=(TextView)convertView.findViewById(R.id.row_rent_price);
            holder.imageView=(ImageView)convertView.findViewById(R.id.row_rent_image);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        Utils.log(Utils.extractImages(rentDetailsList.get(position).getImages())[0]);
//        Picasso.w(context).load(Utils.extractImages(rentDetailsList.get(position).getImages())[0]).into(holder.imageView);
        Picasso.get().load(Utils.extractImages(rentDetailsList.get(position).getImages())[0]).into(holder.imageView);
        holder.textname.setText(rentDetailsList.get(position).getName());
        holder.textlocation.setText(rentDetailsList.get(position).getPlace());
        holder.textrating.setText(rentDetailsList.get(position).getRatings());
        String reviewsNum = rentDetailsList.get(position).getReviews().size()+"";
        holder.textrank.setText(reviewsNum);
        holder.textprice.setText(rentDetailsList.get(position).getPrice());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,RentDetailActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
