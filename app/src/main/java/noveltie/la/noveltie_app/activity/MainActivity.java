package noveltie.la.noveltie_app.activity;

import android.content.pm.PackageManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import noveltie.la.noveltie_app.R;
import noveltie.la.noveltie_app.adaptador.NavMenuAdapter;
import noveltie.la.noveltie_app.fragment.BlogFragment;
import noveltie.la.noveltie_app.fragment.ContactoFragment;
import noveltie.la.noveltie_app.fragment.InicioFragment;
import noveltie.la.noveltie_app.fragment.NosotrosFragment;
import noveltie.la.noveltie_app.fragment.ServiciosFragment;
import noveltie.la.noveltie_app.modelo.NavMenu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static DrawerLayout mDrawerLayout;
    ListView lvNavDraw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navegarInicio();
    }


    List<NavMenu> menuNavMenu = new ArrayList<>();
    private NavMenuAdapter adapterNavMenu;


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*void iniciarMenuNavDraw(){

        menuNavMenu.add(new NavMenu(R.drawable.ic_menu_home, getString(R.string.nav_inicio)));
        menuNavMenu.add(new NavMenu(R.drawable.ic_menu_assignment, getString(R.string.nav_servicios)));
        menuNavMenu.add(new NavMenu(R.drawable.ic_menu_people, getString(R.string.nav_nosotros)));
        menuNavMenu.add(new NavMenu(R.drawable.ic_menu_blog, getString(R.string.nav_blog)));
        menuNavMenu.add(new NavMenu(R.drawable.ic_menu_email, getString(R.string.nav_contacto)));
        adapterNavMenu = new NavMenuAdapter(this, menuNavMenu);


        lvNavDraw.setAdapter(adapterNavMenu);
        lvNavDraw.setOnItemClickListener(this);

        setListViewHeightBasedOnChildren(lvNavDraw);


    }*/
    static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, DrawerLayout.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            navegarInicio();

        } else if (id == R.id.nav_servicios) {
            navegarServicios();
        } else if (id == R.id.nav_nosotros) {
            navegarNosotros();
        } else if (id == R.id.nav_blog) {
            navegarBlog();
        } else if (id == R.id.nav_contacto) {
            navegarContacto();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void navegarInicio() {

        Fragment fragment = new InicioFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commitAllowingStateLoss();
    }

    public void navegarServicios() {
        Fragment fragment = new ServiciosFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commitAllowingStateLoss();
    }

    public void navegarNosotros() {
        Fragment fragment = new NosotrosFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commitAllowingStateLoss();
    }

    public void navegarBlog() {
        Fragment fragment = new BlogFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commitAllowingStateLoss();
    }

    public void navegarContacto() {
        Fragment fragment = new ContactoFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commitAllowingStateLoss();
    }

}
