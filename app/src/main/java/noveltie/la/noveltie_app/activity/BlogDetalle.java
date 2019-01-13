package noveltie.la.noveltie_app.activity;

import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import noveltie.la.noveltie_app.R;

public class BlogDetalle extends AppCompatActivity {
    public ImageView img_blog;
    public TextView tv_fech,tv_titul;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blog_detalle_activity);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_blogDetalle);
        toolbar.setTitle(");
        setSupportActionBar(toolbar);*/

        img_blog = (ImageView) findViewById(R.id.img_blogDetalle);
        tv_fech = (TextView) findViewById(R.id.txt_fecha_blogD);
        tv_titul = (TextView) findViewById(R.id.txt_titulo_blogD);
        HtmlTextView htmlTextView = (HtmlTextView) findViewById(R.id.txt_contenido_blogD);

        String imagen = getIntent().getExtras().getString("img");
        String fecha = getIntent().getExtras().getString("date_blog");
        String titulo = getIntent().getExtras().getString("title_blog");
        String contenido = getIntent().getExtras().getString("content_blog");
        final String link = getIntent().getExtras().getString("link_blog");

        tv_fech.setText(fecha);
        tv_titul.setText(titulo);

        htmlTextView.setHtml(contenido, new HtmlResImageGetter(htmlTextView));
        Glide.with(this)
                .load(imagen)
                .thumbnail(0.1f)
                .into(img_blog);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_blog);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, link);
                startActivity(Intent.createChooser(intent, "Compartir con:"));
            }
        });


    }

}
