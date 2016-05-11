package com.electroscholars.manash.clientinfoview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientViewMainActivity extends AppCompatActivity {

    private RecyclerView clientRecyclerView;
    private ClientAdapter clientAdapter;

    public class ClientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



        private Context context;
        private LayoutInflater layoutInflater;

        public List<Client> clientList = Collections.emptyList();

        private Client current;

        public ClientAdapter(Context context, List <Client> clientList){
            this.context = context;
            layoutInflater = LayoutInflater.from(context);
            this.clientList = clientList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = layoutInflater.inflate(R.layout.client_recycler_view, parent, false);

            view.setOnClickListener(new ItemOnClickListener());

            ClientViewHolder viewHolder = new ClientViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position){
            ClientViewHolder clientViewHolder = (ClientViewHolder) viewHolder;
            current = clientList.get(position);

            clientViewHolder.clientNameTextView.setText("Name: " + current.getName());
            clientViewHolder.clientContactTextView.setText("Contact: " + current.getContact());
            clientViewHolder.clientEmailTextView.setText("e-mail: " + current.getE_mail());
            clientViewHolder.clientAddressTextView.setText("Address: " + current.getAddress());

        }


        @Override
        public int getItemCount(){
            return clientList.size();
        }

        public class ClientViewHolder extends RecyclerView.ViewHolder {
            public TextView clientNameTextView;
            public TextView clientAddressTextView;
            public TextView clientEmailTextView;
            public TextView clientContactTextView;

            public ClientViewHolder(View itemView){
                super(itemView);
                clientNameTextView = (TextView) itemView.findViewById(R.id.clientNameTextView);
                clientAddressTextView = (TextView) itemView.findViewById(R.id.clientAddressTextView);
                clientEmailTextView = (TextView) itemView.findViewById(R.id.e_mailTextView);
                clientContactTextView = (TextView) itemView.findViewById(R.id.contactTextView);
            }
        }

        class ItemOnClickListener implements View.OnClickListener{
            @Override
            public void onClick(View v){
                int itemPosition = clientRecyclerView.indexOfChild(v);

                Log.d("Itemposition", String.valueOf(itemPosition));

                LayoutInflater dialogClientLayoutInflater = getLayoutInflater();
                View dialogClientView = dialogClientLayoutInflater.inflate(R.layout.dialog_view,
                        null);

                TextView newNameTextView = (TextView) v.findViewById(R.id.clientNameTextView);
                TextView newAddressTextView = (TextView) v.findViewById(R.id
                        .clientAddressTextView);
                TextView newContactTextView = (TextView) v.findViewById(R.id.contactTextView);
                TextView newEmailTextView = (TextView) v.findViewById(R.id.e_mailTextView);

                //Dialog textview fields
                TextView dialogClientName = (TextView) dialogClientView.findViewById(R.id
                        .dialogClientName);
                TextView dialogClientAddress = (TextView) dialogClientView.findViewById(R.id
                        .dialogClientAddress);
                TextView dialogClientContact = (TextView) dialogClientView.findViewById(R.id
                        .dialogClientContact);
                TextView dialogClientEmail = (TextView) dialogClientView.findViewById(R.id
                        .dialogClientEmail);

                dialogClientName.setText(newNameTextView.getText().toString());
                dialogClientAddress.setText(newAddressTextView.getText().toString());
                dialogClientEmail.setText(newEmailTextView.getText().toString());
                dialogClientContact.setText(newContactTextView.getText().toString());


                AlertDialog.Builder builder = new AlertDialog.Builder(ClientViewMainActivity.this);
                builder.setTitle("Selected Client Info");
                builder.setView(dialogClientView);


                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }


    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view_main);

        clientRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        List<Client> clientList = new ArrayList<>();

        Client client1 = new Client("Manash", "KUET", "manashmndl@gmail.com", "0167 60 99 300");
        Client client2 = new Client("Rubel", "KUET", "rubelahmed57@gmail.com", "N/A");
        Client client3 = new Client("Pial", "KUET", "N/A", "N/A");
        Client client4 = new Client("Rajesh", "KUET", "N/A", "N/A");
        Client client5 = new Client("Sunny", "KUET", "sunny@gmail.com", "N/A");

        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
        clientList.add(client4);
        clientList.add(client5);

        clientAdapter = new ClientAdapter(ClientViewMainActivity.this, clientList);

        clientRecyclerView.setAdapter(clientAdapter);
        clientRecyclerView.setLayoutManager(new LinearLayoutManager(ClientViewMainActivity.this));

    }
}
