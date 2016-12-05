package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;


public class DiaryFragment extends Fragment {

    TextView Datepick;
    EditText content;

    private static final int PICK_FROM_CAMERA = 1;
    private ImageView imgview;

    public DiaryFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View contentView = inflater.inflate(R.layout.fragment_diary,container,false);

        Calendar cal = Calendar.getInstance();
        DateFormat formatter5 = DateFormat.getDateInstance(DateFormat.FULL);
        formatter5.setTimeZone(cal.getTimeZone());
        String formatted5 = formatter5.format(cal.getTime());
        Datepick = (TextView)contentView.findViewById(R.id.date);
        Datepick.setText(formatted5);

        content = (EditText)contentView.findViewById(R.id.text);
        String contents = content.getText().toString();
        contents = contents.replace("'","''");

        imgview = (ImageView) contentView.findViewById(R.id.image);
        ImageButton buttonCamera = (ImageButton) contentView.findViewById(R.id.camera);

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // 카메라 호출
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

                // 이미지 잘라내기 위한 크기
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 0);
                intent.putExtra("aspectY", 0);
                intent.putExtra("outputX", 170);
                intent.putExtra("outputY", 210);

                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, PICK_FROM_CAMERA);
                } catch (ActivityNotFoundException e) {
                    // Do nothing for now
                }
            }
        });

        return contentView;

    }
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {


            Bundle extras2 = data.getExtras();
            if (extras2 != null) {
                Bitmap photo = extras2.getParcelable("data");
                imgview.setImageBitmap(photo);
            }

    }
}
