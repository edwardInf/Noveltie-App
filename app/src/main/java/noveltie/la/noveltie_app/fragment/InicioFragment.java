package noveltie.la.noveltie_app.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import noveltie.la.noveltie_app.R;

public class InicioFragment extends Fragment {

    View view;
    private WebView mWebView;
    SwipeRefreshLayout swipeInicio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.inicio_fragment,null);

        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setTitle("Noveltie");
        ab.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        mWebView = (WebView) view.findViewById(R.id.webview_inicio);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());


        if (isOnline(getActivity())) {
            mWebView.loadUrl("http://noveltie.la");
        } else {
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }

        swipeInicio = (SwipeRefreshLayout) view.findViewById(R.id.Refresh_inicio);
        swipeInicio.setColorSchemeResources(R.color.colorAccent);
        swipeInicio.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                        mWebView.loadUrl("http://noveltie.la/we");
                        swipeInicio.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

}
