package com.evgeny.lebedev.themovies.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evgeny.lebedev.themovies.Model.Cast;
import com.evgeny.lebedev.themovies.Model.Crew;
import com.evgeny.lebedev.themovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.ViewHolderCredit> {

    private List creditList;
    public boolean isCast;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolderCredit extends RecyclerView.ViewHolder {


        public ImageView imageView;
        public TextView textViewTop;
        public TextView textViewBottom;

        public ViewHolderCredit(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_credit_profile_path);
            textViewTop = itemView.findViewById(R.id.card_credit_top);
            textViewBottom = itemView.findViewById(R.id.card_credit_bottom);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

    public CreditAdapter(List creditList, boolean isCast) {
        this.creditList = creditList;
        this.isCast = isCast;
    }

    @NonNull
    @Override
    public ViewHolderCredit onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_credit, viewGroup, false);
        ViewHolderCredit viewHolderCredit = new ViewHolderCredit(view, listener);
        return viewHolderCredit;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCredit viewHolderCredit, int i) {

        if (isCast) {
            Cast cast = (Cast) creditList.get(i);
            viewHolderCredit.textViewTop.setText(cast.getCharacter());
            viewHolderCredit.textViewBottom.setText(cast.getName());
            Picasso.get().load(cast.getProfilePathSmall())
                    .placeholder(R.drawable.placeholder_vertical)
                    .into(viewHolderCredit.imageView);
        } else {

            Crew crew = (Crew) creditList.get(i);
            viewHolderCredit.textViewTop.setText(crew.getJob());
            viewHolderCredit.textViewBottom.setText(crew.getName());
            Picasso.get().load(crew.getProfilePathSmall())
                    .placeholder(R.drawable.placeholder_vertical)
                    .into(viewHolderCredit.imageView);
        }


    }

    @Override
    public int getItemCount() {
        return creditList.size();
    }


}
