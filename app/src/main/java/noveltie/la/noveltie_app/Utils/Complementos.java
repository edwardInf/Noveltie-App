package noveltie.la.noveltie_app.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import noveltie.la.noveltie_app.R;

import java.util.TimeZone;

public class Complementos {
    public static String url = "https://noveltie.la";
    public static String FACEBOOK_URL = "https://www.facebook.com/NoveltiePeru/";
    private static final int REQUEST_PHONE_CALL = 1;
    public static String numeroCel = "tel:51995921377";
    private static String textIn ="Me interesa el Servicio ";
    private static String textCot ="Me envia una cotización, Gracias ";
    public static final String urlContacto = "https://docs.google.com/forms/d/e/1FAIpQLScpMVX0f4AwV7zGUVt15sDBcc2aqT0D_USPsCzWxVMBd-ijHA/formResponse";
    public static final String NOMBRE_KEY="entry.1873852370";
    public static final String EMAIL_KEY="entry.1115461043";
    public static final String TELEFONO_KEY="entry.461258092";
    public static final String CIUDAD_KEY="entry.1243197627";
    public static final String DIRECCION_KEY="entry.334187647";
    public static final String MENSAJE_KEY="entry.79940757";


    public static Context context;

    public Complementos(Context context, Intent i, int flag) {
        this.context = context;

        switch (flag){
            case 0:
                TimeZone tz = TimeZone.getTimeZone("America/Lima");
                TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                int simState = telMgr.getSimState();
                switch (simState) {
                    case TelephonyManager.SIM_STATE_ABSENT:
                        Toast.makeText(context, "No Hay SIM", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                        Toast.makeText(context, "SIM esta bloqueada", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                        Toast.makeText(context, "Requiere PIN", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                        Toast.makeText(context, "Requiere PUNK", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.SIM_STATE_UNKNOWN:
                        Toast.makeText(context, "SIM Desconocido", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.SIM_STATE_READY:
                        i.setData(Uri.parse(numeroCel));
                        context.startActivity(i);
                        break;
                }
                break;
        }

    }


    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }

    public static Intent Messenger(Context c){
        try {
            c.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/366418616882106"));
        } catch (Exception e){
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(FACEBOOK_URL));
        }
    }


    public static void AbrirWeb(Context c){
        Uri uriUrl = Uri.parse(url);
        Intent link = new Intent(Intent.ACTION_VIEW, uriUrl);
        c.startActivity(link);

    }

    public static boolean checkLlamar(Context context,String telef){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_PHONE_CALL);
            }else {
                Intent i = new Intent(Intent.ACTION_CALL);
                RealizarLlamada(context,i,0,telef);

            }
        }
        return true;
    }

    public static void RealizarLlamada(Context context, Intent i, int flag,String telef){
        switch (flag){
            case 0:
                TimeZone tz = TimeZone.getTimeZone("America/Lima");
                TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                int simState = telMgr.getSimState();
                switch (simState) {
                    case TelephonyManager.SIM_STATE_ABSENT:
                        Toast.makeText(context, "No Hay SIM", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                        Toast.makeText(context, "SIM esta bloqueada", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                        Toast.makeText(context, "Requiere PIN", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                        Toast.makeText(context, "Requiere PUNK", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.SIM_STATE_UNKNOWN:
                        Toast.makeText(context, "SIM Desconocido", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.SIM_STATE_READY:
                        i.setData(Uri.parse(telef));
                        context.startActivity(i);
                        break;
                }
                break;
        }
    }


    public static boolean appInstalledOrNot(String uri, PackageManager pm) {
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public static void compartirOnWhatsapp(AppCompatActivity appCompatActivity, String textBody) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+numeroCel +"&text="+textIn+"\n"+"*"+textBody+"*"+"\n"+textCot));

        try {
            appCompatActivity.startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
            WarningDialog(appCompatActivity, appCompatActivity.getString(R.string.error_activity_not_found));
        }
    }


    private static void WarningDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setNegativeButton(context.getString(R.string.close), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(true)
                .create().show();
    }


}
