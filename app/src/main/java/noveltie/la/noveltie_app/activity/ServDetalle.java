package noveltie.la.noveltie_app.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import noveltie.la.noveltie_app.R;
import noveltie.la.noveltie_app.Utils.Complementos;

public class ServDetalle extends AppCompatActivity {
    TextView tv_titulo,tv_simbolo,tv_precio,tv_promo, tv_simboloPromo,tv_servDescp;
    ImageView img_serv,img_falsa;
    Button btn_llamar,btn_mensaje;
    private static final int REQUEST_PHONE_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicios_detalle_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_servDetalle);
        String subCateg = getIntent().getExtras().getString("subcateg");
        toolbar.setTitle(subCateg);
        setSupportActionBar(toolbar);

        img_falsa = (ImageView)findViewById(R.id.img_serviciosN);
        img_serv = (ImageView)findViewById(R.id.img_serviciosDetalle);
        tv_titulo = (TextView)findViewById(R.id.txt_nombServN);
        tv_simbolo = (TextView)findViewById(R.id.txt_precioServSymbN);
        tv_precio = (TextView)findViewById(R.id.txt_precioServN);
        tv_simboloPromo = (TextView)findViewById(R.id.txt_promocionServSymbol);
        tv_promo = (TextView)findViewById(R.id.txt_promocionServicio);
        HtmlTextView htmlTextView = (HtmlTextView) findViewById(R.id.txt_contenido_ServDetalle);
        tv_servDescp = (TextView)findViewById(R.id.txt_desc_serv);
        tv_servDescp.setText("Descripcion");
        btn_llamar = (Button)findViewById(R.id.btn_llamarServicio);
        btn_mensaje = (Button)findViewById(R.id.btn_mensajeServicio);

        final String imagen = getIntent().getExtras().getString("img");
        final String titulo = getIntent().getExtras().getString("nombre");
        String simbolo = getIntent().getExtras().getString("simbolo");
        String precio = getIntent().getExtras().getString("precio");
        Boolean bandera = getIntent().getExtras().getBoolean("bandera");
        String promo = getIntent().getExtras().getString("precio_promo");
        String descp = getIntent().getExtras().getString("descp");


        img_falsa.setVisibility(View.GONE);
        Glide.with(this)
                .load(imagen)
                .thumbnail(0.1f)
                .into(img_serv);
        tv_titulo.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
        tv_titulo.setText(titulo);
        tv_simbolo.setText(simbolo);
        tv_simbolo.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
        tv_precio.setText(precio);
        tv_precio.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);

        if (bandera.equals(true)){
            tv_precio.setPaintFlags(tv_precio.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            tv_simbolo.setPaintFlags(tv_simbolo.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            tv_simboloPromo.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
            tv_promo.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);

            tv_simboloPromo.setText(simbolo);
            tv_promo.setText(promo);
        }else {
            tv_promo.setVisibility(View.GONE);
            tv_simboloPromo.setVisibility(View.GONE);
        }
        htmlTextView.setHtml(descp, new HtmlResImageGetter(htmlTextView));

        btn_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermisos();

            }
        });

         final PackageManager pm = getPackageManager();
        btn_mensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed = Complementos.appInstalledOrNot("com.whatsapp",pm);
                if(installed) {
                    Complementos.compartirOnWhatsapp(ServDetalle.this,titulo);
                } else {
                    Toast.makeText(getApplicationContext(),"Instale Whatsapp",Toast.LENGTH_SHORT).show();
                }            }
        });


    }



    public boolean checkPermisos(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(ServDetalle.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ServDetalle.this, new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_PHONE_CALL);
            }else {
                Intent i = new Intent(Intent.ACTION_CALL);
                new Complementos(this,i,0);
            }
        }
        return true;
    }



}
