package sample.FirebaseDir;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface FirebaseRestApi {
    @GET("/Control.json")
    Call<FirebaseDatabase> getDatabase();

    @POST("/Control.json")
    Call<FirebaseDatabase> postDatabase(@Body FirebaseDatabase firebaseDatabase);

    @PATCH("/Control.json")
    Call<FirebaseDatabase> updateDatabase(@Body FirebaseDatabase firebaseDatabase);
}
