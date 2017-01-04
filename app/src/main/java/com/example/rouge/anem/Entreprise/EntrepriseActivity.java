package com.example.rouge.anem.Entreprise;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rouge.anem.Entity.Entreprise;
import com.example.rouge.anem.R;
import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Callback;
import com.example.rouge.anem.Tools.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EntrepriseActivity extends AppCompatActivity {
    private ArrayList<Entreprise> listeEntreprise;
    private Api myModel;
    private ListView listView;
    private Callback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreprise);
        this.callback = new Callback<Void>() {
            public Void call() {
                didReceivedData();
                return null;
            }
        };
        myModel = new Api(this.callback);
        listeEntreprise = new ArrayList<Entreprise>();
        listView = (ListView)findViewById(R.id.lvListe);
        EntrepriseAdapter patientAdapter = new EntrepriseAdapter(getBaseContext(), listeEntreprise);
        listView.setAdapter(patientAdapter);
        try {
            String[] mesparams = {Util.getProperty("url.entreprise", getBaseContext())};
            AsyncTask<String, String, Boolean> mThreadCon = new Api(this.callback).execute(mesparams);
        }catch(IOException i ){
            Log.d("Erreur de propriété", i.toString());
        }
    }

    public void didReceivedData(){
        ArrayList<HashMap<String,String>> result = this.callback.getResult();
        result = result;
    }

}
