package com.electroscholars.manash.salesboostercomplete.ViewClient;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.electroscholars.manash.salesboostercomplete.HelperClass.Client;
import com.electroscholars.manash.salesboostercomplete.R;

import java.util.ArrayList;

/**
 * Created by Manash on 5/17/2016.
 */
public class ClientRecyclerViewAdapter extends RecyclerView.Adapter<ClientRecyclerViewAdapter.ClientHolder> {
    private ArrayList<Client> clientDataSet;
    private static ClientClickListener clientClickListener;

    public static class ClientHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView e_mail;
        TextView address;
        TextView contact;

        public ClientHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.clientName);
            address = (TextView) view.findViewById(R.id.clientAddress);
            e_mail = (TextView) view.findViewById(R.id.clientEmail);
            contact = (TextView) view.findViewById(R.id.clientContact);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            clientClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClientClickListener clientClickListener){
        this.clientClickListener = clientClickListener;
    }

    public ClientRecyclerViewAdapter(ArrayList<Client> clientDataSet){
        this.clientDataSet = clientDataSet;
    }

    @Override
    public ClientHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_client_card_view, parent,
                false);
        ClientHolder clientHolder = new ClientHolder(view);
        return clientHolder;
    }

    @Override
    public void onBindViewHolder(ClientHolder holder, int position){
        holder.contact.setText(clientDataSet.get(position).getContact());
        holder.e_mail.setText(clientDataSet.get(position).getE_mail());
        holder.address.setText(clientDataSet.get(position).getAddress());
        holder.name.setText(clientDataSet.get(position).getName());
    }

    public void addItem(Client client, int index){
        clientDataSet.add(index, client);
        notifyItemInserted(index);
    }

    public void deleteItem(int index){
        clientDataSet.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() { return clientDataSet.size(); }

    public interface ClientClickListener {
        public void onItemClick(int position, View v);
    }

}

