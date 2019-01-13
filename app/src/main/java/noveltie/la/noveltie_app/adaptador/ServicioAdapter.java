package noveltie.la.noveltie_app.adaptador;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import java.util.ArrayList;


import noveltie.la.noveltie_app.activity.ServDetalle;
import noveltie.la.noveltie_app.modelo.ServicioData;
import noveltie.la.noveltie_app.R;

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ViewHolder>{
    private ArrayList<ServicioData> itemServ;
    private Context Scontext;
    int cont = 0,tm;
    Integer [] array;

    public ServicioAdapter(Context servContext, ArrayList<ServicioData> itemServ) {
        this.Scontext = servContext;
        this.itemServ = itemServ;
    }

    @Override
    public ServicioAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.servicios_card, viewGroup, false);

        if (cont==0){
            tm = itemServ.size();

        }else {
            //Toast.makeText(Scontext,String.valueOf(tm-cont),Toast.LENGTH_SHORT).show();


        }



        return new ServicioAdapter.ViewHolder(view);
    }
    int j = 0;
    @Override
    public void onBindViewHolder(ServicioAdapter.ViewHolder viewHolder,final int i) {
        if(!("ERP").equals(String.valueOf(itemServ.get(i).getCategoryName())))
        {
            j=j+1;
            //array = new Integer[j];

        }

        cargar(viewHolder,i);

        if (((tm-1)-i)==0){

            //Toast.makeText(Scontext,String.valueOf(j),Toast.LENGTH_SHORT).show();

        }

    }
    public void cargar(ServicioAdapter.ViewHolder viewHolder, int i){
        viewHolder.tv_titulo.setText(itemServ.get(i).getName());
        viewHolder.tv_simbolo.setText(itemServ.get(i).getSymbol());
        viewHolder.tv_precio.setText(itemServ.get(i).getPrice());

        if (itemServ.get(i).getPromotion().getFlag().equals(true)){
            viewHolder.tv_precio.setPaintFlags(viewHolder.tv_precio.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.tv_simbolo.setPaintFlags(viewHolder.tv_simbolo.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.tv_simboloPromo.setText(itemServ.get(i).getSymbol());
            viewHolder.tv_promo.setText(itemServ.get(i).getPromotion().getPrice());
        }else {
            viewHolder.tv_promo.setVisibility(View.GONE);
            viewHolder.tv_simboloPromo.setVisibility(View.GONE);
        }

        Glide.with(viewHolder.itemView.getContext())
                .load(itemServ.get(i).getImagePrimary())
                .thumbnail(0.1f)
                .into(viewHolder.img_serv);
        if(!("ERP").equals(String.valueOf(itemServ.get(i).getCategoryName())))
        {
            cont=cont+1;
            //array[cont]= new Integer(i);
        }


    }

    @Override
    public int getItemCount() {
        return itemServ.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_titulo,tv_simbolo,tv_precio,tv_promo, tv_simboloPromo;
        ImageView img_serv;

        ViewHolder(View view) {
            super(view);
            img_serv = (ImageView) view.findViewById(R.id.img_serviciosN);
            tv_titulo = (TextView)view.findViewById(R.id.txt_nombServN);
            tv_simbolo = (TextView)view.findViewById(R.id.txt_precioServSymbN);
            tv_precio = (TextView)view.findViewById(R.id.txt_precioServN);
            tv_simboloPromo = (TextView)view.findViewById(R.id.txt_promocionServSymbol);
            tv_promo = (TextView)view.findViewById(R.id.txt_promocionServicio);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(Scontext, ServDetalle.class);
                        intent.putExtra("img", itemServ.get(pos).getImagePrimary());
                        intent.putExtra("nombre", itemServ.get(pos).getName());
                        intent.putExtra("simbolo", itemServ.get(pos).getSymbol());
                        intent.putExtra("precio", itemServ.get(pos).getPrice());
                        intent.putExtra("bandera", itemServ.get(pos).getPromotion().getFlag());
                        intent.putExtra("precio_promo", itemServ.get(pos).getPromotion().getPrice());
                        intent.putExtra("subcateg", itemServ.get(pos).getSubcategoryName());
                        intent.putExtra("descp", itemServ.get(pos).getDescription());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Scontext.startActivity(intent);
                    }
                }
            });
            }

    }


    public void eliminar(int i){

            itemServ.remove(i);
            notifyItemRemoved(i);
            notifyItemRangeChanged(i,itemServ.size());
            Toast.makeText(Scontext,String.valueOf(cont),Toast.LENGTH_SHORT).show();


    }



}
