package noveltie.la.noveltie_app.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BlogCon {
    @GET("api/v1/posts")
    Call<JsonRespBlog> getJSON();
}
