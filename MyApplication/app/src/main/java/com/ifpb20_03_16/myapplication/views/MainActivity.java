package com.ifpb20_03_16.myapplication.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ifpb20_03_16.myapplication.AsyncTask.BuscarNomeAsyncTask;
import com.ifpb20_03_16.myapplication.AsyncTask.BuscarQrCodeAsyncTask;
import com.ifpb20_03_16.myapplication.R;
import com.ifpb20_03_16.myapplication.adapter.ConvidadoAdapter;
import com.ifpb20_03_16.myapplication.callback.BuscarConvidadoCallBack;
import com.ifpb20_03_16.myapplication.delete.EstrategiaExclusaoJSON;
import com.ifpb20_03_16.myapplication.model.Convidado;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements TextWatcher, OnItemClickListener, BuscarConvidadoCallBack {
    private static final int REQUEST_CODE = 0;
    @Bind(R.id.edt_nome)
    EditText mEdNome;
    @Bind(R.id.bt_code)
    Button mBtCode;
    @Bind(R.id.lv_nomes)
    ListView mLvNomes;


    private BuscarConvidadoCallBack buscarNomeCallBack;
    private static int TAMANHO_TEXTO = 3;

    List<Convidado> convidados;
    ConvidadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mEdNome.addTextChangedListener(this);

        convidados = new ArrayList<Convidado>();
        adapter = new ConvidadoAdapter(this, convidados);

        mLvNomes.setAdapter(adapter);
        mLvNomes.setOnItemClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.i("EditTextListener", "beforeTextChanged: " + s);

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.i("EditTextListener", "onTextChanged: " + s);
        String nome = s.toString();
        Convidado convidado = new Convidado();
        convidado.setNome(nome);

        if (nome.length() >= TAMANHO_TEXTO) {
            // Gson
            Gson gson = new GsonBuilder().setExclusionStrategies(
                    new EstrategiaExclusaoJSON()).create();
            String stringJSON = gson.toJson(convidado);
            Log.i("EditTextListener", "gson: " + stringJSON);
            BuscarNomeAsyncTask buscarNomeAsyncTask = new BuscarNomeAsyncTask(this);
            buscarNomeAsyncTask.execute(stringJSON);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.i("EditTextListener", "afterTextChanged: " + s);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void backBuscarNome(List<Convidado> names) {

        this.convidados.clear();
        this.convidados.addAll(names);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void errorBuscarNome(String error) {
        convidados.clear();
        adapter.notifyDataSetChanged();

        Toast.makeText(this, error, Toast.LENGTH_LONG);


    }

    @OnClick(R.id.bt_code)
    public void leitorQrCode(Button button) {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int resquestCode, int resultCode, Intent data) {

        if (REQUEST_CODE == resquestCode && RESULT_OK == resultCode) {

            String code = data.getStringExtra("SCAN_RESULT") + data.getStringExtra("SCAN_FORMAT" + "");
            Log.i("EditTextListener", "onTextChanged: " + code);

            String qrCode = code.toString();
            Convidado convidado = new Convidado();
            convidado.setQrCode(qrCode);


            Gson gson = new GsonBuilder().setExclusionStrategies(
                    new EstrategiaExclusaoJSON()).create();
            String stringJSON = gson.toJson(convidado);
            Log.i("EditTextListener", "gson: " + stringJSON);
            BuscarQrCodeAsyncTask buscarQrCodeAsyncTask = new BuscarQrCodeAsyncTask(this);
            buscarQrCodeAsyncTask.execute(stringJSON);


        }

    }
}
