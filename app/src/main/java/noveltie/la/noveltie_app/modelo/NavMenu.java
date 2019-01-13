package noveltie.la.noveltie_app.modelo;

public class NavMenu {
    int mIcono;
    String opcion;

    public NavMenu(int iconResource, String opt){
        mIcono = iconResource;
        opcion = opt;
    }

    public int getmIconResource() {
        return mIcono;
    }


    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

}
