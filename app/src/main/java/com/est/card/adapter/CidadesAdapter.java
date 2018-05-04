package com.est.card.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.est.card.entity.Cidade;
import com.est.card.R;

import java.io.File;
import java.util.List;

/**
 * Created by Mrluke on 25/02/2018.
 */

public class CidadesAdapter extends BaseAdapter {

    private static LayoutInflater mInflater = null;
    private List<Cidade> lista;
    private Context context;
    private String flag;
    public File imgFile;

    public CidadesAdapter(List<Cidade> lista, Context context) {
        this.lista = lista;
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.lista.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder = new ViewHolder();
        View rowView = convertView;
        rowView = mInflater.inflate(R.layout.item_layou, null);

        holder.txtCidade = (TextView) rowView.findViewById(R.id.txt_texto);
        holder.imgClick = (ImageView) rowView.findViewById(R.id.img_click);
        holder.txtNumber = (TextView) rowView.findViewById(R.id.txt_number_generate);
        holder.imgDel = (ImageView) rowView.findViewById(R.id.img_del);

        holder.txtCidade.setText(lista.get(position).getCidade());


        if(imgFile != null && imgFile.exists()){
            holder.txtNumber.setText(imgFile.getPath());
            holder.imgDel.setVisibility(View.VISIBLE);

            holder.imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.txtNumber.setText("");
                    holder.imgDel.setVisibility(View.GONE);
                }
            });
        }

        holder.imgClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "" + lista.get(position).getCidade(), Toast.LENGTH_SHORT).show();
                flag = lista.get(position).getCidade();
                holder.txtNumber.setText(generateNumber(flag));
                //holder.imgDel.setVisibility(View.VISIBLE);

                callCamera();

               /*holder.imgDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.txtNumber.setText("");
                        holder.imgDel.setVisibility(View.GONE);
                    }
                });*/
            }
        });

        return rowView;
    }

    public class ViewHolder{
        TextView txtCidade;
        ImageView imgClick;
        TextView txtNumber;
        ImageView imgDel;
    }

    public String generateNumber(String flag){
        Toast.makeText(context, "" + flag, Toast.LENGTH_SHORT).show();
        long numero = (int) (Math.random() * 9999);
        return String.valueOf(numero);
    }

    public void callCamera(){
        String diretorio = Environment.getExternalStorageDirectory() + "/teste";
        File dir = new File(diretorio);
        if (!dir.exists()) {dir.mkdirs();}
        imgFile = new File(dir,  "teste.png");
        Uri temp = FileProvider.getUriForFile(this.context, "com.est.card.fileprovider", imgFile);

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraIntent.resolveActivity(this.context.getPackageManager()) != null){
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, temp);
            ((Activity) this.context).startActivityForResult(cameraIntent, 1);
        }
    }
}
