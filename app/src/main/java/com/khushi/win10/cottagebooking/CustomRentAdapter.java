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
 * Created by Win10 on 13/09/2018.
 */

public class CustomRentAdapter extends BaseAdapter {
    Context context;
    ArrayList<RentListModel> rentDetailsList;

    public CustomRentAdapter(Context context, ArrayList<RentListModel> rentDetailsList) {
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
        holder.imageView.setImageResource(rentDetailsList.get(position).getImageViewCottage());
        holder.textname.setText(rentDetailsList.get(position).getName());
        holder.textlocation.setText(rentDetailsList.get(position).getLocation());
        holder.textrating.setText(rentDetailsList.get(position).getRating());
        holder.textrank.setText(rentDetailsList.get(position).getRank());
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
