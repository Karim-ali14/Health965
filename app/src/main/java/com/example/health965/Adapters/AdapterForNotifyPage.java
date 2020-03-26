package com.example.health965.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Models.Notification.Data;
import com.example.health965.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterForNotifyPage extends RecyclerView.Adapter<AdapterForNotifyPage.ViewHolderForNotify> {
    List<Data> list;
    Context context;

    public AdapterForNotifyPage(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderForNotify onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForNotify(LayoutInflater.from(context)
                .inflate(R.layout.notify_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForNotify holder, int position) {
        holder.Massage.setText(list.get(position).getBody());
        try {
            holder.Time.setText(countTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .parse(list.get(position).getCreatedAt()),new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (list.size()-1 == position)
            holder.line.setVisibility(View.GONE);
        else
            holder.line.setVisibility(View.VISIBLE);


    }
    public String  countTime(Date startDate, Date endDate) {
        String Time = "";
        long different = endDate.getTime() - startDate.getTime();

        Log.i("TTTTT","startDate : " + startDate);
        Log.i("TTTTT","endDate : " + endDate);
        Log.i("TTTTTT","different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        if (elapsedMinutes == 0){
            Time = "منذ " + elapsedSeconds + " ثانية";
        }else if (elapsedHours == 0){
            Time = "منذ " + elapsedMinutes + " دقائق";
        }else if(elapsedDays == 0){
            Time = "منذ " + elapsedHours + " ساعة";
        }else
            Time = "منذ " + elapsedDays + " يوم";

        Log.i("TTT",Time);
        return Time;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForNotify extends RecyclerView.ViewHolder {
        TextView Massage,Time;
        View line;
        public ViewHolderForNotify(@NonNull View itemView) {
            super(itemView);
            Massage = itemView.findViewById(R.id.massage);
            Time = itemView.findViewById(R.id.Time);
            line = itemView.findViewById(R.id.Line);
        }
    }
}
