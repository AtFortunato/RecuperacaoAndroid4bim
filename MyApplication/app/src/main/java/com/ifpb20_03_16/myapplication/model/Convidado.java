package com.ifpb20_03_16.myapplication.model;

import com.google.gson.annotations.SerializedName;
import com.ifpb20_03_16.myapplication.delete.ExcluirJSON;

import java.io.Serializable;

/**
 * Created by Arthur on 13/04/2016.
 */
public class Convidado implements Serializable {

    @ExcluirJSON
    @SerializedName("id")
    private int id;


    @SerializedName("nome")
    private String nome;


    @SerializedName("qrCode")
    private String qrCode;

    public Convidado() {
    }

    public Convidado(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
