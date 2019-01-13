package noveltie.la.noveltie_app.fragment;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import noveltie.la.noveltie_app.R;
import noveltie.la.noveltie_app.Utils.Complementos;
import noveltie.la.noveltie_app.adaptador.ServicioAdapter;
import noveltie.la.noveltie_app.api.ApiNoveltie;
import noveltie.la.noveltie_app.api.JsonRespServ;
import noveltie.la.noveltie_app.api.ServicioCon;
import noveltie.la.noveltie_app.modelo.ServicioData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ServiciosFragment extends Fragment {
    private ArrayList<ServicioData> servicios;
    private ServicioAdapter servAdapter;

    public RecyclerView recyclerView;
    View view;
    SwipeRefreshLayout swipeServicios;


    public ServiciosFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.servicios_fragment,null);

        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setTitle("Servicios");
        ab.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        iniciar();
        swipeServicios = (SwipeRefreshLayout) view.findViewById(R.id.Refresh_servicios);
        swipeServicios.setColorSchemeResources(R.color.colorAccent);
        swipeServicios.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iniciar();
                swipeServicios.setRefreshing(false);
            }
        });
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        /*adapter.addFragment(new SongsFragment(), this.getString(R.string.songs));
        adapter.addFragment(new AlbumFragment(), this.getString(R.string.albums));
        adapter.addFragment(new ArtistFragment(), this.getString(R.string.artists));*/
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }


    private void iniciar() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_servicios);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new Complementos.GridSpacingItemDecoration(2, dpToPx(7), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        loadJSON();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_serv, menu);
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

    private void loadJSON() {
        try{
            ApiNoveltie cliente = new ApiNoveltie();
            ServicioCon apiService = cliente.getApiNoveltie().create(ServicioCon.class);
            Call<JsonRespServ> call = apiService.getJSON();
            call.enqueue(new Callback<JsonRespServ>() {
                @Override
                public void onResponse(Call<JsonRespServ> call, Response<JsonRespServ> response) {
                    JsonRespServ jsonResponse = response.body();
                    servicios = new ArrayList<>(Arrays.asList(jsonResponse.getData()));
                    servAdapter = new ServicioAdapter(getActivity(), servicios);
                    recyclerView.setAdapter(servAdapter);
                }
                @Override
                public void onFailure(Call<JsonRespServ> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity(), "Necesitas conexion a Internet!", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
