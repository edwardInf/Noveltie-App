package noveltie.la.noveltie_app.api;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import noveltie.la.noveltie_app.adaptador.BlogAdapter;
import noveltie.la.noveltie_app.fragment.BlogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiNoveltie {
    public static Retrofit retrofit=null;

    public static Retrofit getApiNoveltie(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://app.noveltie.la")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            BlogCon request = retrofit.create(BlogCon.class);

        return retrofit;
    }

    public static void ConexionBlog(){
        try{
            ApiNoveltie cliente = new ApiNoveltie();
            BlogCon apiService = cliente.getApiNoveltie().create(BlogCon.class);
            Call<JsonRespBlog> call = apiService.getJSON();
            call.enqueue(new Callback<JsonRespBlog>() {
                @Override
                public void onResponse(Call<JsonRespBlog> call, Response<JsonRespBlog> response) {
                    JsonRespBlog jsonResponse = response.body();

                    BlogFragment.blog = new ArrayList<>(Arrays.asList(jsonResponse.getData()));
                    BlogFragment.adapter = new BlogAdapter(BlogFragment.activity, BlogFragment.blog);
                    BlogFragment.recyclerView.setAdapter(BlogFragment.adapter);

                    BlogFragment.swipeListaBlog.setRefreshing(false);
                    BlogFragment.swipeListaBlog.setRefreshing(false);
                    if (BlogFragment.bPD==true){
                        BlogFragment.pd.hide();
                    }
                }
                @Override
                public void onFailure(Call<JsonRespBlog> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(BlogFragment.act, "Necesitas conexion a Internet!", Toast.LENGTH_SHORT).show();
                    BlogFragment.swipeListaBlog.setRefreshing(false);
                    if (BlogFragment.bPD==true){
                        BlogFragment.pd.hide();
                    }
                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(BlogFragment.bContext, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
