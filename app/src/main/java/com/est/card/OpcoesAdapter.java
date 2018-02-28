package com.est.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mrluke on 25/02/2018.
 */

public class OpcoesAdapter extends BaseAdapter {

    private static LayoutInflater mInflater = null;
    private List<Cidade> listaCidades;
    private Context context;
    private Pref p;
    private HelperDAO dao;
    private Cidade cidade;

    public OpcoesAdapter(List<Cidade> listaCidades, Context context) {
        this.listaCidades = listaCidades;
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.p = new Pref(context);
        this.dao = new HelperDAO(context);
    }

    @Override
    public int getCount() {
        return this.listaCidades.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaCidades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View rowView = convertView;
        rowView = mInflater.inflate(R.layout.item_opcao, null);

        holder.opcao = (Switch) rowView.findViewById(R.id.switch_opcao);

        holder.opcao.setText(listaCidades.get(position).getCidade());
        final String op = holder.opcao.getText().toString();

        if(holder.opcao.getText().toString().equals(op)){
            if(p.recupera(op).equals("on")){
                holder.opcao.setChecked(true);
            }else{
                holder.opcao.setChecked(false);
            }
        }

        holder.opcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.grava(op, op);
                selecionaOpcoes(v, op);
            }
        });

        return rowView;
    }

    private void selecionaOpcoes(View view, String o){
        Switch op = (Switch) view;
        if(op.getText().toString().equals(p.recupera(op.getText().toString()))){
            if(op.isChecked()){
                p.grava(p.recupera(op.getText().toString()), "on");
                this.dao.updateCidade(o, "on");
                //this.dao.update(new Cidade(o, "on"));
            }else{
                p.grava(p.recupera(op.getText().toString()), "off");
                this.dao.updateCidade(o, "off");
                //this.dao.update(new Cidade(o, "off"));
            }
        }
    }

    public class ViewHolder{
        Switch opcao;
    }
}

