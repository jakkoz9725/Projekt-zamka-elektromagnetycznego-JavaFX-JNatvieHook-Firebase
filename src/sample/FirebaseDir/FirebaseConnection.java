package sample.FirebaseDir;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

abstract class FirebaseConnection {

    Gson gson;
    Retrofit retrofit;
    FirebaseRestApi firebaseRestApi;

     FirebaseConnection() {
        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://<Topsecred>.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        firebaseRestApi = retrofit.create(FirebaseRestApi.class);
    }
}
