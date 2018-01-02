package com.nik.androspace.pollingappmaster;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout firstclick,secondclick;
    View firstoption, secondoption;
    TextView textfirst, textsecond;
    int total;
    int first = 0;
    int second = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstclick = findViewById(R.id.optionfirstclick);
        secondclick = findViewById(R.id.optionsecondclick);
        firstoption = findViewById(R.id.firstoption);
        secondoption = findViewById(R.id.secondoption);
        textfirst= (TextView)findViewById(R.id.text2);
        textsecond= (TextView)findViewById(R.id.text4);

        firstclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                first = first+1;
                total = first+second;

                float finalfirst = getPercentage(first,total);
                showanimation(finalfirst,firstoption);
                textfirst.setText(finalfirst+"%");
                Log.e("first",finalfirst+", "+ first +", "+total);

                float finalsecond = getPercentage(second,total);
                showanimation(finalsecond,secondoption);
                textsecond.setText(finalsecond+"%");
                Log.e("second",finalsecond+", "+ second +", "+total);
            }
        });

        secondclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                second = second+1;
                total = first+second;

                float finalsecond = getPercentage(second,total);
                showanimation(finalsecond,secondoption);
                textsecond.setText(finalsecond+"%");
                Log.e("second",finalsecond+", "+ second +", "+total);

                float finalfirst = getPercentage(first,total);
                showanimation(finalfirst,firstoption);
                textfirst.setText(finalfirst+"%");
                Log.e("first",finalfirst+", "+ first +", "+total);
            }
        });

    }

    public static float getPercentage(int n, int total) {
        float proportion = ((float) n) / ((float) total);
        return proportion * 100;
    }

    public void showanimation(float toweight,final View viewToAnimate){
        ViewWeightAnimationWrapper animationWrapper = new ViewWeightAnimationWrapper(viewToAnimate);
        ObjectAnimator anim = ObjectAnimator.ofFloat(animationWrapper,
                "weight",
                animationWrapper.getWeight(),
                toweight);
        anim.setDuration(2500);
        anim.start();
    }

    public class ViewWeightAnimationWrapper {
        private View view;

        public ViewWeightAnimationWrapper(View view) {
            if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                this.view = view;
            } else {
                throw new IllegalArgumentException("The view should have LinearLayout as parent");
            }
        }

        public void setWeight(float weight) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.weight = weight;
            view.getParent().requestLayout();
        }

        public float getWeight() {
            return ((LinearLayout.LayoutParams) view.getLayoutParams()).weight;
        }
    }
}

