package salesbooster.clientcardview;

import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import salesbooster.clientcardview.Model.Client;
import salesbooster.clientcardview.Model.ClientRecyclerViewAdapter;

public class ClientCardViewMainActivity extends AppCompatActivity {

    private RecyclerView clientRecyclerView;
    private RecyclerView.Adapter clientRecyclerViewAdapter;
    private RecyclerView.LayoutManager clientRecyclerViewLayoutManager;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_recycler_view);

        clientRecyclerView = (RecyclerView) findViewById(R.id.clientRecyclerView);
        clientRecyclerViewLayoutManager = new LinearLayoutManager(this);
        clientRecyclerView.setLayoutManager(clientRecyclerViewLayoutManager);
        clientRecyclerViewAdapter = new ClientRecyclerViewAdapter(getDataSet());
        clientRecyclerView.setAdapter(clientRecyclerViewAdapter);

    }

    @Override
    protected void onResume(){
        super.onResume();
        ((ClientRecyclerViewAdapter) clientRecyclerViewAdapter).setOnItemClickListener(new ClientRecyclerViewAdapter.ClientClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                position++;
                Toast.makeText(ClientCardViewMainActivity.this, "Clicked item " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Client> getDataSet(){
        ArrayList<Client> clients = new ArrayList<>();
        for (int i = 1; i < 20; i++){
            Client client = new Client("Name: " + i, "Address: " + i, "E-mail: " + i, "Contact: " + i);
            clients.add(client);
        }
        return clients;
    }

}
