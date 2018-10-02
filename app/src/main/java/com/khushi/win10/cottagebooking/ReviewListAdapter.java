package com.khushi.win10.cottagebooking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.khushi.win10.cottagebooking.Model.RentListModel;

import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {
    private List<RentListModel.DataBean.ReviewsBean> reviewList;

    ReviewListAdapter(List<RentListModel.DataBean.ReviewsBean> reviewList){
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_review,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(reviewList.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        TextView dateTv,contentTv;

        ViewHolder(View itemView){
            super(itemView);
            ratingBar = itemView.findViewById(R.id.review_rating_bar);
            dateTv = itemView.findViewById(R.id.review_date);
            contentTv = itemView.findViewById(R.id.review_text);
        }

        void bind(RentListModel.DataBean.ReviewsBean bean){
            ratingBar.setRating(Float.valueOf(bean.getRatings()));
            dateTv.setText(bean.getDate());
            contentTv.setText(bean.getReview());
        }
    }
}
