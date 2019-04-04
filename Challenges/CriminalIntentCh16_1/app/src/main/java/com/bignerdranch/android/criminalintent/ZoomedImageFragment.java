package com.bignerdranch.android.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class ZoomedImageFragment extends DialogFragment {

    private static final String CRIME_PHOTO = "crime_photo";

    public static ZoomedImageFragment newInstance(String imgPath) {

        Bundle args = new Bundle();
        args.putString(CRIME_PHOTO, imgPath);

        ZoomedImageFragment fragment = new ZoomedImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        String path = (String) getArguments().getSerializable(CRIME_PHOTO);
        final View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_zoomed_image, null);
        final ImageView imageView = view.findViewById(R.id.zoom_img);

        final Bitmap bitmap = PictureUtils.getScaledBitmap(path, getActivity());
        imageView.setImageBitmap(bitmap);

        dialog.setContentView(view);
        return dialog;
    }
}
