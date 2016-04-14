package com.ifpb20_03_16.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifpb20_03_16.myapplication.R;
import com.ifpb20_03_16.myapplication.model.Convidado;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arthur on 13/04/2016.
 */
public class ConvidadoAdapter extends BaseAdapter {
    Context context;
    List<Convidado> convidados;
    LayoutInflater mInflater;

    public ConvidadoAdapter(Context context, List<Convidado> convidados) {
        this.context = context;
        this.convidados = convidados;
    }

    public ConvidadoAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return convidados.size();
    }

    @Override
    public Object getItem(int position) {
        return convidados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return convidados.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder = null;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_list_convidado, parent, false);
            holder = new ViewHolder(view);

            Convidado convidado = convidados.get(position);

            holder.mTvQrCode.setText(convidado.getQrCode());
            holder.mTvId.setText(convidado.getId() + "");
            holder.mtvNome.setText(convidado.getNome());
        } else {
            view = convertView;
        }
        return view;
    }

     class ViewHolder {

        @Bind(R.id.tv_id)
        TextView mTvId;
        @Bind(R.id.tv_nome)
        TextView mtvNome;
        @Bind(R.id.tv_qr_code)
        TextView mTvQrCode;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
