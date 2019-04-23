package sample.FirebaseDir;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.ControllersDir.MainWindowController;

public class FirebaseGetRequest extends FirebaseConnection {
    private static Call<FirebaseDatabase> getCall;
    private  FirebaseDatabase firebaseDatabaseRecived = new FirebaseDatabase();

    public FirebaseGetRequest() {
        getCall = firebaseRestApi.getDatabase();
    }
    public FirebaseDatabase updatedData() {
        return firebaseDatabaseRecived;
    }
    public void getDatabaseHere(MainWindowController mainWindowController) {
                getCall.enqueue(new Callback<FirebaseDatabase>() {
                    @Override
                    public void onResponse(Call<FirebaseDatabase> call, Response<FirebaseDatabase> response) {
                        if (!response.isSuccessful()) {
                            System.out.println("Something went wrong");
                        } else {
                            if(!firebaseDatabaseRecived.equals(response.body())){
                                firebaseDatabaseRecived = response.body();
                                System.out.println("DATA UPDATED");
                                mainWindowController.updateUI(firebaseDatabaseRecived); }
                        }
                        if (call.isExecuted()) {
                            call.clone().enqueue(this);
                        }
                    }

                    @Override
                    public void onFailure(Call<FirebaseDatabase> call, Throwable throwable) {
                        System.out.println("Something went wrong");
                    }
                });
    }
}

