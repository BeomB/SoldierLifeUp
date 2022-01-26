package com.soldier.soldierlifeup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Internet_Fragment extends Fragment {
    //    FragmentManager fragmentManager = getChildFragmentManager();
    ViewGroup v1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v1 = (ViewGroup) inflater.inflate(R.layout.internet_fragment,container,false);
        configureImageButton();
        return v1;
    }

    public void configureImageButton() {
        final ImageView CountryDefense = (ImageView) v1.findViewById(R.id.CountryDefense);
        final TextView CountryDefensetxt =(TextView)v1.findViewById(R.id.CountryDefensetxt);
        final ImageView Announcement = (ImageView) v1.findViewById(R.id.announcement);
        final TextView Announcementtxt =(TextView)v1.findViewById(R.id.announcementtxt);
        final ImageView News = (ImageView) v1.findViewById(R.id.news);
        final TextView Newstxt =(TextView)v1.findViewById(R.id.newstxt);
        final ImageView Army = (ImageView) v1.findViewById(R.id.Army);
        final TextView Armytxt =(TextView)v1.findViewById(R.id.armytxt);
        final ImageView Navy = (ImageView) v1.findViewById(R.id.Navy);
        final TextView Navytxt =(TextView)v1.findViewById(R.id.Navytxt);
        final ImageView AirForce = (ImageView) v1.findViewById(R.id.AirForce);
        final TextView AirForcetxt =(TextView)v1.findViewById(R.id.AirForcetxt);
        final ImageView Marine = (ImageView) v1.findViewById(R.id.Marine);
        final TextView Marinetxt =(TextView)v1.findViewById(R.id.marinetxt);
        final ImageView TV = (ImageView) v1.findViewById(R.id.Tv);
        final TextView TVtxt =(TextView)v1.findViewById(R.id.TVtxt);
        final ImageView blog = (ImageView) v1.findViewById(R.id.blog);
        final TextView blogtxt =(TextView)v1.findViewById(R.id.blogtxt);


        CountryDefense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_CountryDefense.class);
                startActivity(intent);
            }
        });
        CountryDefensetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_CountryDefense.class);
                startActivity(intent);
            }
        });

        Announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_Announcement.class);
                startActivity(intent);
            }
        });

        Announcementtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_Announcement.class);
                startActivity(intent);
            }
        });

        News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_news.class);
                startActivity(intent);
            }
        });
        Newstxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_news.class);
                startActivity(intent);
            }
        });
        Army.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_Army.class);
                startActivity(intent);
            }
        });
        Armytxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_Army.class);
                startActivity(intent);
            }
        });
        Navy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_Navy.class);
                startActivity(intent);
            }
        });
        Navytxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_Navy.class);
                startActivity(intent);
            }
        });

        AirForce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_AirForce.class);
                startActivity(intent);
            }
        });
        AirForcetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_AirForce.class);
                startActivity(intent);
            }
        });
        Marine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_Marin.class);
                startActivity(intent);
            }
        });

        Marinetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_Marin.class);
                startActivity(intent);
            }
        });

        TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_TV.class);
                startActivity(intent);
            }
        });

        TVtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_TV.class);
                startActivity(intent);
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_blog.class);
                startActivity(intent);
            }
        });

        blogtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Internet_blog.class);
                startActivity(intent);
            }
        });




















    }
}
