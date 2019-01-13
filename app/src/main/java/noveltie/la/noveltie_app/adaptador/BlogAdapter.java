package noveltie.la.noveltie_app.adaptador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import noveltie.la.noveltie_app.activity.BlogDetalle;
import noveltie.la.noveltie_app.modelo.BlogData;
import noveltie.la.noveltie_app.R;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder>{
    private ArrayList<BlogData> blog;
    private Context Bcontext;


    public BlogAdapter(Context blogContext, ArrayList<BlogData> blog) {
        this.Bcontext = blogContext;
        this.blog = blog;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.blog_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tv_fecha.setText(blog.get(i).getDate());
        viewHolder.tv_titulo.setText(blog.get(i).getTitle());
        viewHolder.tv_link.setText(blog.get(i).getLink_blog());
        Glide.with(viewHolder.itemView.getContext())
                .load(blog.get(i).getImage_xlarge_460())
                .thumbnail(0.1f)
                .into(viewHolder.img_blogL);
    }

  @Override
    public int getItemCount() {
        return blog.size();
    }

    public interface OnItemClickListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_titulo,tv_fecha,tv_content,tv_link;
        public ImageView img_blogL;
        private Context context;


        public ViewHolder(View view) {
            super(view);
            img_blogL = (ImageView) view.findViewById(R.id.img_blog);
            tv_fecha = (TextView)view.findViewById(R.id.txt_fecha_blog);
            tv_titulo = (TextView)view.findViewById(R.id.txt_titulo_blog);
            tv_link = (TextView)view.findViewById(R.id.txt_link_blog);

            context = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context.getApplicationContext(), BlogDetalle.class);
                        intent.putExtra("img", blog.get(pos).getImage_xlarge_460());
                        intent.putExtra("date_blog", blog.get(pos).getDate());
                        intent.putExtra("title_blog", blog.get(pos).getTitle());
                        intent.putExtra("content_blog", blog.get(pos).getContent());
                        intent.putExtra("link_blog", blog.get(pos).getLink_blog());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.getApplicationContext().startActivity(intent);

                    }
                }
            });

        }

    }



    public void setFilterBlog(ArrayList<BlogData> newList){
        this.blog = new ArrayList<>();
        this.blog.addAll(newList);
        notifyDataSetChanged();
    }



}
