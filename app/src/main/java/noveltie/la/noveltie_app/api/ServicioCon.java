package noveltie.la.noveltie_app.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicioCon {
    @GET("api/v1/items")
    Call<JsonRespServ> getJSON();
}
