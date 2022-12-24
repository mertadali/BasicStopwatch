package com.mertadali.runnablehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int number;
    Runnable runnable;
    Handler handler; // runnable ve messages ile çalışmaya yarar.
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        number=0;
    }
    /*public void start(View view){
        Eğerki ifadeyi bu şekilde yapmak isteseydik sayı 1 den 100 e kadar 1 sn artsın isteriz.
        Ancak thread kullanmazsak bize direkt 100 sonucunu verir ve biz o aralıkta ne olduğunu görmeyiz.
        app de thread demek uyutmak demek app her 1 sn uyusun öyle göstersin mantıklı yol olsada bizim
        textview ile buttonlarımız arası bağlantıyı kaybederiz eğer ki arka planda uyusun istersek işte
        bu sefer runnable kullanmalıyız.

        while (number<100){
            textView.setText("Time:" +number);
            number++;
            textView.setText("Time:" +number);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    public void start(View view){

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText("Time:" +number);
                number++;
                textView.setText("Time:" +number);
                handler.postDelayed(runnable,1000);
                // her 1 sn de bir runnable a bu işlemi yap demek.


            }
        };
        handler.post(runnable);
        button.setEnabled(false); /* eğer bunu kullanmazsak buttona her bastığımızda daha hızlı bir
         şekilde  saymaya başlardı. */


    }

    public  void stop(View view){
        button.setEnabled(true);

        handler.removeCallbacks(runnable); // bu ifade arka planda çalışan runnableyi durdurdu.

        textView.setText("Time:" +number);

    }
}