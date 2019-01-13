package noveltie.la.noveltie_app.fragment;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;


import noveltie.la.noveltie_app.R;
import noveltie.la.noveltie_app.Utils.Complementos;


public class ContactoFragment extends Fragment {

    private EditText nombre,email,telefono,ciudad,direccion,mensaje;
    RequestQueue queue;
    ProgressDialog progressDialog;
    Snackbar snackbar;


    View view;
    public ContactoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.contacto_fragment,null);

        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setTitle("Contacto");
        ab.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);



        nombre = (EditText)view.findViewById(R.id.edt_ContactNombre);
        email = (EditText)view.findViewById(R.id.edt_ContactEmail);
        telefono = (EditText)view.findViewById(R.id.edt_ContactTelef);
        ciudad = (EditText)view.findViewById(R.id.edt_ContactCiudad);
        direccion = (EditText)view.findViewById(R.id.edt_direcc);
        mensaje = (EditText)view.findViewById(R.id.edt_Mensaje);


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Enviando...");


        queue = Volley.newRequestQueue(getActivity());

        Button sendButton = (Button)view.findViewById(R.id.btn_enviarForm);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(nombre.getText().toString()) ||
                        TextUtils.isEmpty(email.getText().toString()) ||
                        TextUtils.isEmpty(telefono.getText().toString())||
                        TextUtils.isEmpty(ciudad.getText().toString()) ||
                        TextUtils.isEmpty(direccion.getText().toString()) ||
                        TextUtils.isEmpty(mensaje.getText().toString()) )
                {
                    snackbar = Snackbar.make(view, "Llenar todos los Campos", Snackbar.LENGTH_LONG);
                    snackbar.getView().setBackgroundColor(getResources().getColor(R.color.error));
                    snackbar.show();
                } else {
                    postData(nombre.getText().toString().trim(), email.getText().toString().trim(),
                            telefono.getText().toString().trim(),ciudad.getText().toString().trim(),
                            direccion.getText().toString().trim(),mensaje.getText().toString().trim());

                }
            }
        });

        return view;
    }

    public void postData(final String nomb, final String ema, final String telef,
                         final String ciu, final String direcc, final String mens)  {

        progressDialog.show();
        StringRequest request = new StringRequest(
                Request.Method.POST,
                Complementos.urlContacto,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "Response: " + response);
                        if (response.length() > 0) {
                            snackbar = Snackbar.make(view, "Mensaje Enviado", Snackbar.LENGTH_LONG);
                            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            snackbar.show();
                            nombre.setText(null);
                            email.setText(null);
                            telefono.setText(null);
                            ciudad.setText(null);
                            direccion.setText(null);
                            mensaje.setText(null);
                        } else {
                            Snackbar.make(view, "Intenta Otra Vez", Snackbar.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Snackbar.make(view, "Error al Enviar Datos", Snackbar.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Complementos.NOMBRE_KEY, nomb);
                params.put(Complementos.EMAIL_KEY, ema);
                params.put(Complementos.TELEFONO_KEY, telef);
                params.put(Complementos.CIUDAD_KEY, ciu);
                params.put(Complementos.DIRECCION_KEY, direcc);
                params.put(Complementos.MENSAJE_KEY, mens);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

}
