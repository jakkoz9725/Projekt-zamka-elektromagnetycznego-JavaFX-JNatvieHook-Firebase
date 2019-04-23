package sample.FirebaseDir;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirebaseUpdateRequest extends FirebaseConnection{

    private FirebaseRestApi firebaseRestApi = retrofit.create(FirebaseRestApi.class);

    public void updateFirebaseDatabase(FirebaseDatabase firebaseDatabase){
        Call<FirebaseDatabase> update = firebaseRestApi.updateDatabase(firebaseDatabase);

        update.enqueue(new Callback<FirebaseDatabase>() {
            @Override
            public void onResponse(Call<FirebaseDatabase> call, Response<FirebaseDatabase> response) {
                if(!response.isSuccessful()){
                    System.out.println("Something is wrong");
                }
            }
            @Override
            public void onFailure(Call<FirebaseDatabase> call, Throwable throwable) {
            }
        });
    }
}
