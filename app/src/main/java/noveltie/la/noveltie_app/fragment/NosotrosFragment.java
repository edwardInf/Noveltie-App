package noveltie.la.noveltie_app.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import noveltie.la.noveltie_app.R;
import noveltie.la.noveltie_app.Utils.Complementos;

public class NosotrosFragment extends Fragment {

    View view;
    private final int REQUEST_PHONE_CALL = 1;
    public LinearLayout telefono1,telefono2,web,facebook,messenger,ubicacion;
    String numeroTelef;

    public NosotrosFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.nosotros_fragment,null);
        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setTitle("Nosotros");
        ab.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(false);

        telefono1 = (LinearLayout)view.findViewById(R.id.layout_telefono1);
        telefono2 = (LinearLayout)view.findViewById(R.id.layout_telefono2);
        web = (LinearLayout)view.findViewById(R.id.layout_web);
        facebook = (LinearLayout)view.findViewById(R.id.layout_facebook);
        messenger = (LinearLayout)view.findViewById(R.id.layout_messenger);
        ubicacion = (LinearLayout)view.findViewById(R.id.layout_ubicacion);

        telefono1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numeroTelef = "tel:51995921377";
                Complementos.checkLlamar(getActivity(),numeroTelef);
            }
        });

        telefono2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numeroTelef = "tel:51952917079";
                Complementos.checkLlamar(getActivity(),numeroTelef);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Complementos.AbrirWeb(getActivity());
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = Complementos.Messenger(getActivity());
                startActivity(facebookIntent);
            }
        });
        messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("fb://messaging/" + "366418616882106")));
            }
        });
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return view;
    }

}
