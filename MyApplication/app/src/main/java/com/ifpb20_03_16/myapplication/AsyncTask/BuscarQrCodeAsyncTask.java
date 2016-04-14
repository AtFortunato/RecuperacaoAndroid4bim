package com.ifpb20_03_16.myapplication.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ifpb20_03_16.myapplication.callback.BuscarConvidadoCallBack;
import com.ifpb20_03_16.myapplication.model.Convidado;
import com.ifpb20_03_16.myapplication.util.HttpService;
import com.ifpb20_03_16.myapplication.util.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 14/04/2016.
 */
public class BuscarQrCodeAsyncTask extends AsyncTask<String, Void, Response> {

    private BuscarConvidadoCallBack buscarConvidadoCallBack;

    public BuscarQrCodeAsyncTask(BuscarConvidadoCallBack buscarConvidadoCallBack) {
        this.buscarConvidadoCallBack = buscarConvidadoCallBack;
    }

    @Override
    protected Response doInBackground(String... stringJSONs) {
        Response response = null;

        String stringJSON = stringJSONs[0];
        Log.i("EditTextListener", "doInBackground (JSON): " + stringJSON);

        try {
            Log.e("NotificationWearApp", "erro do back " + stringJSON);
            response = HttpService.sendJSONPostResquest("/pesquisar/qrcode",
                    stringJSON);

        } catch (IOException e) {

            Log.e("EditTextListener", e.getMessage());
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {

        int codeHttp = response.getStatusCodeHttp();

        Log.i("EditTextListener", "Código HTTP: " + codeHttp + " Conteúdo: "
                + response.getContentValue());

        if (codeHttp != HttpURLConnection.HTTP_OK) {

            buscarConvidadoCallBack.errorBuscarNome(response.getContentValue());

        } else {

            Gson gson = new Gson();
            List<Convidado> convidados = gson.fromJson(response.getContentValue(),
                    new TypeToken<ArrayList<Convidado>>() {
                    }.getType());

            buscarConvidadoCallBack.backBuscarNome(convidados);
        }
    }

}
