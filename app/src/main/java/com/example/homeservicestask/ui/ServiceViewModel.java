package com.example.homeservicestask.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.homeservicestask.models.ServicesModel;
import com.example.homeservicestask.server.ServiceClient;
import com.example.homeservicestask.server.ServiceInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ServiceViewModel extends ViewModel {

    MutableLiveData<ServicesModel> servicesModelMutableLiveData = new MutableLiveData<>();

    public void getHomeServices(Double lat, Double longt, int radius, String lang) {
        ServiceInterface serviceInterface = ServiceClient.cteateService(ServiceInterface.class);
        serviceInterface.getData(lat,longt,radius,lang
        ).enqueue(new Callback<ServicesModel>() {
            @Override
            public void onResponse(Call<ServicesModel> call, Response<ServicesModel> response) {
                servicesModelMutableLiveData.setValue(response.body());
                Log.d(TAG, "message: ");
                Log.d(TAG, "message: ");
            }

            @Override
            public void onFailure(Call<ServicesModel> call, Throwable t) {
                Log.d(TAG, "error: ");
                Log.d(TAG, "error: ");
            }

        });
    }

}
