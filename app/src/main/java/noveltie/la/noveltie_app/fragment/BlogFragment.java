package noveltie.la.noveltie_app.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import noveltie.la.noveltie_app.R;
import noveltie.la.noveltie_app.Utils.Complementos;
import noveltie.la.noveltie_app.adaptador.BlogAdapter;
import noveltie.la.noveltie_app.api.ApiNoveltie;
import noveltie.la.noveltie_app.api.BlogCon;
import noveltie.la.noveltie_app.api.JsonRespBlog;
import noveltie.la.noveltie_app.modelo.BlogData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlogFragment extends Fragment implements SearchView.OnQueryTextListener {
    public static RecyclerView recyclerView;
    public  static SwipeRefreshLayout swipeListaBlog;
    public static ArrayList<BlogData> blog;
    public static BlogAdapter blogadapter;

    public static Activity activity;
    public static ProgressDialog pd;
    public static boolean bPD=false;
    public static BlogAdapter adapter;
    public static FragmentActivity act;
    public static Context bContext;



    View view;
    public BlogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.blog_fragment, container, false);


        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setTitle("Blog");
        ab.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        recyclerView = (RecyclerView) v.findViewById(R.id.blog_recycler);
        act = getActivity();
        iniciar();

        swipeListaBlog = (SwipeRefreshLayout) v.findViewById(R.id.Refresh_blog_list);
        swipeListaBlog.setColorSchemeResources(R.color.colorAccent);
        swipeListaBlog.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ApiNoveltie.ConexionBlog();
                    }
                },0500);
            }
        });
        return v;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void iniciar() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(bContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        ApiNoveltie.ConexionBlog();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_blog, menu);

        MenuItem itemS = menu.findItem(R.id.action_buscar);
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(itemS);
        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_llamar:
                Complementos.checkLlamar(getActivity(),Complementos.numeroCel);
                default:


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<BlogData> newList = new ArrayList<>();
        for (BlogData blogData: blog){
            String title = blogData.getTitle().toLowerCase();
            if (title.contains(newText))
                newList.add(blogData);
        }
        adapter.setFilterBlog(newList);
        return true;
    }




}
