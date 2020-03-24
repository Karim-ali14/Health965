package com.example.health965.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Common.Common;
import com.example.health965.Models.ClientReservation.ClientReservation;
import com.example.health965.Models.ClientReservation.Row;
import com.example.health965.Models.Reservation.UpDate.ModelOfUpDate;
import com.example.health965.Models.Reservation.UpDate.UpdateStatusOfReservation;
import com.example.health965.R;
import com.example.health965.UI.Client_reservation.Client_ReservationViewModels;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterForClientReservation extends RecyclerView.Adapter<AdapterForClientReservation.ViewHolderForClientReservation> {
    List<Row> list;
    Context context;
    RecyclerView recyclerView;
    Client_ReservationViewModels viewModel;

    public AdapterForClientReservation(List<Row> list, Context context, RecyclerView recyclerView,
                                       Client_ReservationViewModels viewModel) {
        this.list = list;
        this.context = context;
        this.recyclerView = recyclerView;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolderForClientReservation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForClientReservation(LayoutInflater.from(context).inflate(R.layout.model_requests,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderForClientReservation holder, final int position) {
        holder.NameText.setText("أسم العيادة");
        holder.EmailLayOut.setVisibility(View.GONE);
        holder.PhoneLayOut.setVisibility(View.GONE);
        final Row model = list.get(position);
        holder.Name.setText(model.getClinic().getName());
        if (model.getDoctor() != null)
            holder.Doctor.setText(model.getDoctor().getName());
        else
            holder.Doctor.setText("-------");
        try {
            holder.ReservationDate.setText(new SimpleDateFormat("hh:mm a").format(
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(model.getCreatedAt())));

            holder.ReservationTime.setText(new SimpleDateFormat("dd/MM/yyyy").format(
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(model.getCreatedAt())));
        }  catch (ParseException e) {
            e.printStackTrace();
        }
        setStatus(model.getStatus(),holder);
        holder.Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog dialog = new BottomSheetDialog(context,R.style.BottomSheetDialogTheme);
                View bottomSheet = LayoutInflater.from(context).inflate(R.layout.model_bottom_sheet
                        ,(RelativeLayout)holder.view.findViewById(R.id.container));

                dialog.setContentView(bottomSheet);
                dialog.show();
                RelativeLayout LayoutContacted = bottomSheet.findViewById(R.id.LayoutContacted);
                LayoutContacted.setVisibility(View.GONE);
                bottomSheet.findViewById(R.id.LayoutCanceled).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateStatus(model,"cancelled",position);
                        dialog.dismiss();
                    }
                });
                bottomSheet.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void setStatus(String status,ViewHolderForClientReservation holder){
        if (status.equals("cancelled"))
            holder.StatusRequest.setText("تم الالغاء الحجز");
        else if (status.equals("waiting")){
            holder.StatusRequest.setText("انتظر الرد");
        } else if (status.equals("confirmed")){
            holder.StatusRequest.setText("تم تأكيد الحجز");
        } else if (status.equals("finished")){
            holder.StatusRequest.setText("أنتهى");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForClientReservation  extends RecyclerView.ViewHolder {
        TextView NameText,Name,Email,Phone,Doctor,ReservationDate,ReservationTime,StatusRequest;
        RelativeLayout Menu,PhoneLayOut,EmailLayOut;
        View view;
        public ViewHolderForClientReservation (@NonNull View itemView) {
            super(itemView);
            NameText = itemView.findViewById(R.id.NameText);
            Name = itemView.findViewById(R.id.Name);
            Email = itemView.findViewById(R.id.Email);
            Phone = itemView.findViewById(R.id.Phone);
            Doctor = itemView.findViewById(R.id.Doctor);
            ReservationDate = itemView.findViewById(R.id.Date);
            ReservationTime = itemView.findViewById(R.id.Time);
            StatusRequest = itemView.findViewById(R.id.Status);
            Menu = itemView.findViewById(R.id.ListOfChose);
            EmailLayOut = itemView.findViewById(R.id.EmailLayOut);
            PhoneLayOut = itemView.findViewById(R.id.PhoneLayOut);
            view = itemView;
        }
    }

    private void updateStatus(Row model, String status, final int position){
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.show();
        Common.getAPIRequest().onUpDateReservationForClient(
                Common.CurrentUser.getData().getToken().getAccessToken(),
                Common.CurrentUser.getData().getUser().getId() + "",
                model.getId() + "", new ModelOfUpDate(status))
                .enqueue(new Callback<UpdateStatusOfReservation>() {
            @Override
            public void onResponse(Call<UpdateStatusOfReservation> call,
                                   final Response<UpdateStatusOfReservation> response) {
                dialog.dismiss();
                if (response.code() == 200){
                    list.clear();
                    viewModel.getAllClientReservation(context,dialog).observe((LifecycleOwner) context
                            , new Observer<ClientReservation>() {
                                @Override
                                public void onChanged(ClientReservation clientReservation) {
                                    dialog.dismiss();
                                    recyclerView.setAdapter(new AdapterForClientReservation(
                                            clientReservation.getData().getRows(),
                                            context,recyclerView,viewModel));
                                    recyclerView.smoothScrollToPosition(position);
                                }
                            });
                }else {
                    try {
                        Log.i("TTTTTH",response.code()+"   "+new JSONObject(response.errorBody().string()).getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateStatusOfReservation> call, Throwable t) {

            }
        });
    }
}
