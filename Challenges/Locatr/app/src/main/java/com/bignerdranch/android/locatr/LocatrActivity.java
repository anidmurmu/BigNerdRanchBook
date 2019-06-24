package com.bignerdranch.android.locatr;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class LocatrActivity extends SingleFragmentActivity {
  public static final int REQUEST_ERROR = 0;

  @Override
  protected Fragment createFragment() {
    return LocatrFragment.newInstance();
  }

  @Override
  protected void onResume() {
    super.onResume();

    GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
    int errorCode = apiAvailability.isGooglePlayServicesAvailable(this);

    if (errorCode != ConnectionResult.SUCCESS) {
      Dialog errorDialog = apiAvailability
          .getErrorDialog(this, errorCode, REQUEST_ERROR,
              new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                  // Leave if services are unavailable
                  finish();
                }
              });

      errorDialog.show();
    }
  }
}
