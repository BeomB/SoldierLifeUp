package com.soldier.soldierlifeup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Home_Fragment extends Fragment {
    //    FragmentManager fragmentManager = getChildFragmentManager();
    private WebView mWebView;
    ArmyDictionary fragmentDictionary = new ArmyDictionary();
    ViewGroup v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = (ViewGroup) inflater.inflate(R.layout.home_fragment,container,false);
        configureImageButton();
        return v;
    }

    public void configureImageButton() {
        final ImageView dictionary = (ImageView) v.findViewById(R.id.dictionary);
        ImageView hotel = (ImageView) v.findViewById(R.id.hotel);
        ImageView Discount_info = (ImageView) v.findViewById(R.id.information);
        ImageView TMO = (ImageView) v.findViewById(R.id.train);



        TextView dictionarytxt = (TextView) v.findViewById(R.id.dictionarytxt);
        TextView servicetxt = (TextView) v.findViewById(R.id.servicetxt);
        TextView saletxt = (TextView) v.findViewById(R.id.saletxt);
        TextView Tmotxt = (TextView) v.findViewById(R.id.Tmotxt);

        //--------------------------------------------------------------------

        final TextView mainarmytxt = (TextView)v.findViewById(R.id.mainarmytxt);
        final TextView armytxt = (TextView)v.findViewById(R.id.armytxt);
        final TextView marintxt = (TextView)v.findViewById(R.id.marintxt);
        final TextView policetxt = (TextView)v.findViewById(R.id.policetxt);

        //--------------------------------------------------------------------- ViewFlipper
        ViewFlipper viewFlipperfirst = (ViewFlipper)v.findViewById(R.id.viewFlipperfirst);
        ViewFlipper viewFlippersecond = (ViewFlipper)v.findViewById(R.id.viewFlippersecond);
        ViewFlipper viewFlipperthird = (ViewFlipper)v.findViewById(R.id.viewFlipperthird);

        viewFlipperfirst.startFlipping();
        viewFlipperfirst.setFlipInterval(3000);

        viewFlippersecond.startFlipping();
        viewFlippersecond.setFlipInterval(3000);

        viewFlipperthird.startFlipping();
        viewFlipperthird.setFlipInterval(3000);

        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArmyDictionary.class);
                startActivity(intent);
            }
        });
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArmyService.class);
                startActivity(intent);
            }
        });
        Discount_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArmySale.class);
                startActivity(intent);
            }
        });
        TMO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArmyTmo.class);
                startActivity(intent);
            }
        });
        dictionarytxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArmyDictionary.class);
                startActivity(intent);
            }
        });
        servicetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArmyService.class);
                startActivity(intent);
            }
        });
        saletxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArmySale.class);
                startActivity(intent);
            }
        });
        Tmotxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArmyTmo.class);
                startActivity(intent);
            }
        });


        //----------------------------------------------------------------------------------------
        mainarmytxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_CountryDefense.class);
                startActivity(intent);
            }
        });

        armytxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Internet_Army.class);
                startActivity(intent);
            }
        });

        marintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_Navy.class);
                startActivity(intent);
            }
        });

        policetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_AirForce.class);
                startActivity(intent);
            }
        });


    }
}