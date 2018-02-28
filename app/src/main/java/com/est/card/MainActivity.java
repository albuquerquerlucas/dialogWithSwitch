package com.est.card;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private ListView lista, listaOpcoes;
    private List<Cidade> cidades, listaCidadesConformeOpcoes;
    private OpcoesAdapter opcoesAdapter = null;
    private CidadesAdapter cidadesAdapter = null;
    private AlertDialog dialog;
    private HelperDAO dao;
    private Pref p;
    private Cidade cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.p = new Pref(this);
        this.dao = new HelperDAO(this);

        //popularBanco();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.btnAdd = (Button) findViewById(R.id.btn_opcoes);
        this.lista = (ListView) findViewById(R.id.lista);
        this.listaOpcoes = (ListView) findViewById(R.id.lista_opcoes);

        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibeOpcoes(getCidades());
            }
        });

        carregaCards();
    }

    private void carregaCards(){

        listaCidadesConformeOpcoes = new ArrayList<>();

        if(getCidades().size() > 0){
            for(Cidade c : getCidades()){
                if(c.getStatus().equals("on")){
                    listaCidadesConformeOpcoes.add(c);
                }
            }
            cidadesAdapter = new CidadesAdapter(listaCidadesConformeOpcoes, this);
            lista.setAdapter(cidadesAdapter);
            //cidadesAdapter.notifyDataSetChanged();
        }
    }

    private List<Cidade> getCidades(){

        cidades = this.dao.getAllCidades();

        return cidades;
    }

    private void exibeOpcoes(List<Cidade> lista){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_opcao, null);
        dialogBuilder.setView(dialogView);

        listaOpcoes = dialogView.findViewById(R.id.lista_opcoes);

        dialog = dialogBuilder.create();
        dialog.show();

        opcoesAdapter = new OpcoesAdapter(getCidades(),this);
        listaOpcoes.setAdapter(opcoesAdapter);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                carregaCards();
            }
        });
    }

    private void popularBanco(){
        this.dao.insert(new Cidade("Acopiara", "off"));
        this.dao.insert(new Cidade("Catarina", "off"));
        this.dao.insert(new Cidade("Mombaça", "off"));
        this.dao.insert(new Cidade("Iguatu", "off"));
        this.dao.insert(new Cidade("Piquet Carneiro", "off"));
        this.dao.insert(new Cidade("Ubajara", "off"));
        this.dao.insert(new Cidade("Sobral", "off"));
        this.dao.insert(new Cidade("Fortaleza", "off"));
        this.dao.insert(new Cidade("Quixadá", "off"));
        this.dao.insert(new Cidade("Quixeramobim", "off"));
        this.dao.insert(new Cidade("Retiro", "off"));
        this.dao.insert(new Cidade("Brejo Santo", "off"));
        this.dao.insert(new Cidade("Crato", "off"));
        this.dao.insert(new Cidade("Barbalha", "off"));
        this.dao.insert(new Cidade("Retiro", "off"));
        this.dao.insert(new Cidade("Crateús", "off"));
        this.dao.insert(new Cidade("Jardim", "off"));
        this.dao.insert(new Cidade("Santa Quitéria", "off"));
        this.dao.insert(new Cidade("Maranguape", "off"));
        this.dao.insert(new Cidade("Aracatí", "off"));
        this.dao.insert(new Cidade("Cascavel", "off"));
        this.dao.insert(new Cidade("Morada Nova", "off"));
        this.dao.insert(new Cidade("Jucás", "off"));
        this.dao.insert(new Cidade("Quixelô", "off"));
        this.dao.insert(new Cidade("Juazeiro do Norte", "off"));
        this.dao.insert(new Cidade("Barbalha", "off"));
    }
}
