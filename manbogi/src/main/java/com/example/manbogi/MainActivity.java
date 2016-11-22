package com.example.manbogi;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensormanager;
    private Sensor accSensor;

    private TextView textview;
    private Button resetButton;

    public static int cnt = 0;
    //걸음수 초기값

    private long lastTime;
    //가장 최근 측정한 시간
    private float speed;

    private float lastX;
    private float lastY;
    private float lastZ;
    //가장 최근 측정한 X,Y,Z

    private float x,y,z;

    private static final int SHAKE_THRESHOLD=800;
    //흔들렸다에 대한 기준 -> test해보고 변경해 주어야 한다.

    private static final int DATA_X=SensorManager.DATA_X;
    private static final int DATA_Y=SensorManager.DATA_Y;
    private static final int DATA_Z=SensorManager.DATA_Z;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensormanager = (SensorManager)getSystemService((SENSOR_SERVICE));
        //SensorManager의 인스턴트를 가져온다
        accSensor = sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //가속도 센서
        textview = (TextView) findViewById(R.id.cntView);
        resetButton = (Button) findViewById(R.id.resetBtn);

        textview.setText(""+cnt);//

    }

    @Override //SensorEventListener를 사용하기 위한 필수 구현 메서드
    public void onSensorChanged(SensorEvent event) {
        //센서값이 바뀔때마다 리스너 오브젝트의 onSensorChanged가 호출된다.
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            //가속도 센서라면
            long currentTime= System.currentTimeMillis();
            long gabOfTime = (currentTime-lastTime);
            if(gabOfTime>250){
                //최근 측정한 시간과 현재시간을 비교하여 1초이상일때, 흔듬을 감지한다.
                lastTime=currentTime;
                x= event.values[SensorManager.DATA_X];
                y= event.values[SensorManager.DATA_Y];
                z= event.values[SensorManager.DATA_Z];

                speed= Math.abs(x+y+z-lastX-lastY-lastZ)/gabOfTime*10000;

                if(speed>SHAKE_THRESHOLD){
                    textview.setText(""+(++cnt));
                }

                lastX=event.values[DATA_X];
                lastY=event.values[DATA_Y];
                lastZ=event.values[DATA_Z];
            }
        }


    }
    public void mOnClick(View v) {
        //reset버튼을 눌렀을시에 호출되는 함수이다.
        switch (v.getId()) {
            case R.id.resetBtn :
                cnt = 0;
                textview.setText("" + cnt);
                break;
        }
    }

    @Override //SensorEventListener를 사용하기 위한 필수 구현 메서드
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //정확도가 변할때 호출되는 함수이다.
    }

    @Override
    public void onStart() {
        super.onStart();
        if (accSensor != null)
            sensormanager.registerListener(this, accSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensormanager != null)
            sensormanager.unregisterListener(this);
    }


}
