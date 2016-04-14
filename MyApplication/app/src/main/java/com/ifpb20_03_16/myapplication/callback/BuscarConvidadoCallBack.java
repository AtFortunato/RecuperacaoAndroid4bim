package com.ifpb20_03_16.myapplication.callback;

import com.ifpb20_03_16.myapplication.model.Convidado;

import java.util.List;

/**
 * Created by Arthur on 13/04/2016.
 */
public interface BuscarConvidadoCallBack {
    void backBuscarNome(List<Convidado> names);

    void errorBuscarNome(String error);
}
