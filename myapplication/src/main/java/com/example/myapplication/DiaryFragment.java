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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;


public class DiaryFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView Datepick;
    EditText content;

    private static final int PICK_FROM_CAMERA = 1;
    private ImageView imgview;

    Spinner spinner;
    Button btnAdd;
    EditText inputLabel;

    View contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        contentView = inflater.inflate(R.layout.fragment_diary,container,false);

        Calendar cal = Calendar.getInstance();
        DateFormat formatter5 = DateFormat.getDateInstance(DateFormat.FULL);
        formatter5.setTimeZone(cal.getTimeZone());
        String formatted5 = formatter5.format(cal.getTime());
        Datepick = (TextView)contentView.findViewById(R.id.date);
        Datepick.setText(formatted5);

        content = (EditText)contentView.findViewById(R.id.text);
        String contents = content.getText().toString();
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
        //spinner start

        spinner = (Spinner)contentView.findViewById(R.id.spinner);
        btnAdd= (Button)contentView.findViewById(R.id.btn_add);
        inputLabel = (EditText)contentView.findViewById(R.id.input_label);

        spinner.setOnItemSelectedListener(this);

        loadSpinnerData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = inputLabel.getText().toString();

                if(label.trim().length()>0){
                    DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
                    db.insertLabel(label);

                    Toast.makeText(getActivity().getApplicationContext(), "db"+db.getAllLabels(),
                            Toast.LENGTH_SHORT).show();
                    inputLabel.setText("");

                    loadSpinnerData();



                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter label name",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        return contentView;

    }
    private void loadSpinnerData(){
        DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
        List<String> labels = db.getAllLabels();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,labels);

        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);


        spinner.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String label = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
