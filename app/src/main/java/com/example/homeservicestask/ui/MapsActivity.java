package com.example.homeservicestask.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.homeservicestask.CenterZoomLayoutManager;
import com.example.homeservicestask.R;
import com.example.homeservicestask.adapters.CategoryAdapter;
import com.example.homeservicestask.adapters.ServiceAdapter;
import com.example.homeservicestask.models.CategoryData;
import com.example.homeservicestask.models.Services;
import com.example.homeservicestask.models.ServicesModel;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    RecyclerView lstMainServices;
    RecyclerView lstSubServices;
    CategoryAdapter categoryAdapter;
    private com.google.android.material.bottomsheet.BottomSheetBehavior BottomSheetBehavior;
    ServiceViewModel serviceViewModel;
    CenterZoomLayoutManager linearLayoutManager;
    int categoryId;
    ServiceAdapter serviceAdapter;
    List<CategoryData> categories = new ArrayList<>();
    ImageView btnPre, btnNext;


    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    double latitude, longtude;
    Location lastKnownLocation;
    protected LocationRequest locationRequest;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
checkForLocationRequest();
checkForLocationSettings();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        View bottomSheet = findViewById(R.id.bottom_sheet);
        lstMainServices = (RecyclerView) findViewById(R.id.services_lst);
        lstSubServices = (RecyclerView) findViewById(R.id.sub_services_lst);

        btnPre = (ImageView) findViewById(R.id.btn_pre);
        btnNext = (ImageView) findViewById(R.id.btn_nxt);


        BottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        serviceViewModel = ViewModelProviders.of(this).get(ServiceViewModel.class);
        categoryAdapter = new CategoryAdapter(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        serviceViewModel.getHomeServices(latitude, longtude, 10, "ar");
        linearLayoutManager = new CenterZoomLayoutManager(this, CenterZoomLayoutManager.HORIZONTAL, false);

        lstMainServices.setLayoutManager(linearLayoutManager);
        lstMainServices.setHasFixedSize(true);
        lstMainServices.setAdapter(categoryAdapter);

        serviceViewModel.servicesModelMutableLiveData.observe(this, new Observer<ServicesModel>() {
            @Override
            public void onChanged(ServicesModel servicesModel) {
                categoryAdapter.setData((ArrayList<CategoryData>) servicesModel.getData().getCategories());
                categories = servicesModel.getData().getCategories();
                // Toast.makeText(MainActivity.this, categoryAdapter.getCatAt(), Toast.LENGTH_SHORT).show();
                addServices(6);


            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(linearLayoutManager.findLastCompletelyVisibleItemPosition()+1<categories.size()){
                    lstMainServices.getLayoutManager().scrollToPosition(linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                    addServices(linearLayoutManager.findLastCompletelyVisibleItemPosition() +1);
                }}
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(linearLayoutManager.findFirstCompletelyVisibleItemPosition()>0) {
                    lstMainServices.getLayoutManager().scrollToPosition(linearLayoutManager.findFirstCompletelyVisibleItemPosition() - 1);
                    addServices(linearLayoutManager.findLastCompletelyVisibleItemPosition() - 1);
                }}
        });


    }

    public void checkForLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(1000);
        locationRequest.setNumUpdates(1);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }
    public void checkForLocationSettings() {
        try {
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.addLocationRequest(locationRequest);
            SettingsClient settingsClient = LocationServices.getSettingsClient(this);
            settingsClient.checkLocationSettings(builder.build())
                    .addOnSuccessListener((Activity) MapsActivity.this, new OnSuccessListener<LocationSettingsResponse>() {
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//                            delay(1);
                            //Setting is success...
                            //  Toast.makeText(SplashActivity.this, "Enabled the Location successfully. Now you can press the buttons..", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener((Activity) MapsActivity.this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            int statusCode = ((ApiException) e).getStatusCode();
                            switch (statusCode) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    try {
                                        // Show the dialog by calling startResolutionForResult(), and check the
                                        // result in onActivityResult().

                                        ResolvableApiException rae = (ResolvableApiException) e;
                                        rae.startResolutionForResult((Activity) MapsActivity.this,2);
                                    } catch (Exception ex) {
                                      //  new MyUtils().catchError(MainActivity.this, ex);
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                  //  Toast.makeText(MainActivity.this, "Setting change is not available.Try in another device.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            // new MyUtils().catchError(MainActivity.this, e);

        }
    }



    public int setCategoryId(int id) {
        categoryId = id;
        return categoryId;
    }
    private void scrollToCenter(View v) {
        int itemToScroll = lstMainServices.getChildPosition(v);
        int centerOfScreen = lstMainServices.getWidth() / 2 - v.getWidth() / 2;
        linearLayoutManager.scrollToPositionWithOffset(itemToScroll, centerOfScreen);
    }
    public void addServices(int position) {


        lstSubServices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        serviceAdapter = new ServiceAdapter(getApplicationContext(), (ArrayList<Services>) categories.get(position).getServices());

        lstSubServices.setAdapter(serviceAdapter);
        serviceAdapter.notifyDataSetChanged();
        lstMainServices.smoothScrollToPosition(position);

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    getLocation();

                }
            }
        }
    }
private void getLocation() {
    locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    if (lastKnownLocation != null) {

        latitude = lastKnownLocation.getLatitude();
        longtude = lastKnownLocation.getLongitude();




    }
}
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(mylocation).title("Task"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 15));
                //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (Build.VERSION.SDK_INT < 23) {

            getLocation();

        } else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }


        // Add a marker in Sydney and move the camer
        // LatLng sydney = new LatLng(latitude, longtude);
        //      mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //      mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
