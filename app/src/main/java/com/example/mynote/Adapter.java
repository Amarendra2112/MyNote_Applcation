package com.example.mynote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> notes;

    Adapter(Context context, List<Note> note)
    {
        this.inflater = inflater.from(context);
        this.notes = note;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        String title = notes.get(i).getTitle();
        String date = notes.get(i).getDate();
        String time = notes.get(i).getTime();
        holder.ntitle.setText(title);
        holder.ndate.setText(date);
        holder.ntime.setText(time);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ntitle, ndate,ntime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ntitle = itemView.findViewById(R.id.NTitle);
            ndate = itemView.findViewById(R.id.NDate);
            ntime = itemView.findViewById(R.id.NTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),Details.class);
                    i.putExtra("ID", notes.get(getAdapterPosition()).getTitle());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
