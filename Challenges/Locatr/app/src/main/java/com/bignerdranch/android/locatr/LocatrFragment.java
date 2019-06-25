package com.bignerdranch.android.locatr;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocatrFragment extends Fragment {
  public static final String TAG = "LocatrFragment";

  private ImageView mImageView;
  private GoogleApiClient mClient;

  public static LocatrFragment newInstance() {
    return new LocatrFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    mClient = new GoogleApiClient.Builder(getActivity())
        .addApi(LocationServices.API)
        .addConnectionCallbacks(new ConnectionCallbacks() {
          @Override
          public void onConnected(@androidx.annotation.Nullable Bundle bundle) {
            getActivity().invalidateOptionsMenu();
          }

          @Override
          public void onConnectionSuspended(int i) {

          }
        })
        .build();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_locatr, container, false);

    mImageView = view.findViewById(R.id.image);

    return view;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragment_locatr, menu);

    MenuItem searchItem = menu.findItem(R.id.action_locate);
    searchItem.setEnabled(mClient.isConnected());
  }

  private void findImage() {
    LocationRequest request = LocationRequest.create();
    request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    request.setNumUpdates(1);
    request.setInterval(0);
    /*LocationServices.FusedLocationApi
        .requestLocationUpdates(mClient, request, new LocationListener() {
          @Override
          public void onLocationChanged(Location location) {
            Log.i(TAG, "Got a fix: " + location);
          }
        });*/
  }


  @Override
  public void onStart() {
    super.onStart();

    getActivity().invalidateOptionsMenu();
    mClient.connect();
  }

  @Override
  public void onStop() {
    super.onStop();

    mClient.disconnect();
  }
}
