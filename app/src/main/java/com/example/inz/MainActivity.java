package com.example.inz;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {


    TextView progressHorizontal;
    TextView progressVertical;
    TextView progressSpeed;

    int MinimalValueHorizontal=-90;
    int fixedValueHorizontal;

    int MinimalValueVertical=-90;
    int fixedValueVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button up = (Button) findViewById(R.id.upButton);
        Button down = (Button) findViewById(R.id.downButton);
        Button left = (Button) findViewById(R.id.leftButton);
        Button right = (Button) findViewById(R.id.rightButton);



        progressHorizontal = findViewById(R.id.Progresshorizontal);
        progressVertical = findViewById(R.id.Progressvertical);


        SeekBar SeekBarHorizontal = (SeekBar) findViewById(R.id.seekBarHorizontal);
        SeekBar SeekBarVertical = (SeekBar) findViewById(R.id.seekBarVertical);


        SeekBarHorizontal.setProgress(90);
        SeekBarVertical.setProgress(90);


        final Spinner speedSpinner = (Spinner) findViewById(R.id.speedspinner);
        String[] spinnerElements = {"Wolno","Średnio","Szybko"};
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, spinnerElements);

        speedSpinner.setAdapter(adapter);
        speedSpinner.setSelection(1);
        speedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BackgroundTask b = new BackgroundTask();
                switch (position){
                    case 0:
                        b.execute("s130");
                        break;

                    case 1:
                        b.execute("s200");
                        break;
                    case 2:
                        b.execute("s235");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        int progresshor = SeekBarHorizontal.getProgress();
        int progressver = SeekBarVertical.getProgress();


        fixedValueHorizontal = progresshor+MinimalValueHorizontal;
        fixedValueVertical = progressver+MinimalValueVertical;

        progressHorizontal.setText(String.valueOf(fixedValueHorizontal));
        progressVertical.setText(String.valueOf((fixedValueVertical)));


        SeekBarHorizontal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar SeekBarHorizontal, int progress, boolean fromUser) {
                fixedValueHorizontal = progress+MinimalValueHorizontal;
                progressHorizontal.setText(String.valueOf(fixedValueHorizontal));
                BackgroundTask b = new BackgroundTask();
                b.execute('h'+String.valueOf(fixedValueHorizontal));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBarVertical.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fixedValueVertical = progress+MinimalValueVertical;
                progressVertical.setText(String.valueOf(fixedValueVertical));
                BackgroundTask b = new BackgroundTask();
                b.execute('v'+String.valueOf(fixedValueVertical));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        @SuppressLint("WrongViewCast") WebView stream = (WebView) findViewById(R.id.videoView);
        stream.setWebViewClient(new WebViewClient());
        stream.loadUrl("http://192.168.4.2:81/stream");



//        center.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BackgroundTask b = new BackgroundTask();
//                b.execute("z");
//            }
//        });

//        upCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BackgroundTask b = new BackgroundTask();
//                b.execute("e");
//            }
//        });
//
//        downCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BackgroundTask b = new BackgroundTask();
//                b.execute("c");
//            }
//        });


        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    BackgroundTask b = new BackgroundTask();
                    b.execute("f");
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    BackgroundTask b = new BackgroundTask();
                    b.execute("s");
                }
                return false;
            }
        });

        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    BackgroundTask b = new BackgroundTask();
                    b.execute("d");
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    BackgroundTask b = new BackgroundTask();
                    b.execute("s");
                }
                return false;
            }
        });

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    BackgroundTask b = new BackgroundTask();
                    b.execute("r");
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    BackgroundTask b = new BackgroundTask();
                    b.execute("s");
                }
                return false;
            }
        });

        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    BackgroundTask b = new BackgroundTask();
                    b.execute("l");
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    BackgroundTask b = new BackgroundTask();
                    b.execute("s");
                }
                return false;
            }
        });
    }


    class BackgroundTask extends AsyncTask<String,Void,String>{

        String ip, message, ready;
        Socket soc;
        BufferedOutputStream dos;


        @Override
        protected String doInBackground(String... params){
        ip="192.168.4.1";
        message = params[0];

            try {
                int i;
                InetAddress local = InetAddress.getByName(ip);
                int msg_length = message.length();
                DatagramSocket s = new DatagramSocket(12312);
                byte mess[] = message.getBytes();
                DatagramPacket p = new DatagramPacket(mess, msg_length, local, 12312 );
                for(i=0; i<2;i++){
                    s.send(p);
                }
                s.close();
                    //soc= new Socket(ip,80);



                    //dos = new BufferedOutputStream(soc.getOutputStream());

//                    dos.write(mess);
//                    dos.flush();
//                    ready="ready";
//                    dos.close();
//                    soc.close();;

            } catch(IOException e){
                e.printStackTrace();;
            }

            return null;
        }
    }
}
