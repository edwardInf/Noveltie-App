package noveltie.la.noveltie_app.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import noveltie.la.noveltie_app.R;

import java.util.List;

import noveltie.la.noveltie_app.modelo.NavMenu;

public class NavMenuAdapter extends BaseAdapter {
    Context mCtx;
    List<NavMenu> mOpcion;
    int mPosition = 0;
    public NavMenuAdapter(Context ctx, List<NavMenu> options){
        mCtx = ctx;
        mOpcion = options;
    }


    public void setData(List<NavMenu> options){
        mOpcion = options;
    }

    public void selectItem(int position){
        mPosition = position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.menu_item,null);
        }

        NavMenu option = mOpcion.get(position);

        LinearLayout llIndicator = (LinearLayout) convertView.findViewById(R.id.llIndicator);

        if(mPosition>=0){
            if(position == mPosition){
                convertView.setBackgroundColor(mCtx.getResources().getColor(R.color.colorPrimary_light));
                llIndicator.setVisibility(View.VISIBLE);
            }else{
                convertView.setBackgroundColor(mCtx.getResources().getColor(R.color.colorFondoGeneral));
                llIndicator.setVisibility(View.INVISIBLE);
            }
        }else {
            convertView.setBackgroundColor(mCtx.getResources().getColor(R.color.colorFondoGeneral));
            llIndicator.setVisibility(View.INVISIBLE);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        TextView txtOption = (TextView) convertView.findViewById(R.id.txtOption);

        txtOption.setText(option.getOpcion());
        imgIcon.setImageResource(option.getmIconResource());

        return convertView;
    }

    @Override
    public int getCount() {
        return mOpcion.size();
    }

    @Override
    public Object getItem(int position) {
        return mOpcion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
