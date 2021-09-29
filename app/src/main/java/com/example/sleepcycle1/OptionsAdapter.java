package com.example.sleepcycle1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder> {

    private static final String TAG = "OptionsAdapter";

    private ArrayList<String> mOptionsPrimary = new ArrayList<>();
    private ArrayList<String> mOptionsSecondary = new ArrayList<>();
    private Context mContext;

    public OptionsAdapter(ArrayList<String> mOptionsPrimary, ArrayList<String> mOptionsSecondary, Context context) {
        this.mOptionsPrimary = mOptionsPrimary;
        this.mOptionsSecondary = mOptionsSecondary;
        this.mContext = context;
    }

    @NonNull
    @Override
    public OptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_options_list, parent, false);
        OptionsViewHolder viewHolder = new OptionsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OptionsViewHolder holder, int position) {

        holder.optionsPrimary.setText(mOptionsPrimary.get(holder.getAdapterPosition()));
        holder.optionsSecondary.setText(mOptionsSecondary.get(holder.getAdapterPosition()));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimePickerActivity();
            }
        });
    }

    private void openTimePickerActivity() {
        Intent intent = new Intent(mContext, TimePickerActivityWake.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mOptionsPrimary.size();
    }

    public class OptionsViewHolder extends RecyclerView.ViewHolder{

        TextView optionsPrimary;
        TextView optionsSecondary;
        ConstraintLayout parentLayout;

        public OptionsViewHolder(@NonNull View itemView) {
            super(itemView);

            optionsPrimary = itemView.findViewById(R.id.firstPrimaryOption);
            optionsSecondary = itemView.findViewById(R.id.firstSecondaryOption);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
