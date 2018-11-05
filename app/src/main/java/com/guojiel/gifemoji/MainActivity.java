package com.guojiel.gifemoji;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.TextView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mJTv = findViewById(R.id.mJTv);
        SpannableStringBuilder sps = new SpannableStringBuilder();
        sps.append("我是第一个gif表情->");
        sps.append(genGiftSpan(R.drawable.emoji_6));
        sps.append("\r\n");

        sps.append("我也是");
        sps.append(genGiftSpan(R.drawable.emoji_1));
        sps.append("gif表情哦");

        mJTv.setText(sps);
    }

    private SpannableString genGiftSpan(@DrawableRes int id){
        SpannableString sps = new SpannableString("\uFFFC");
        GifDrawable drawable = null;
        try {
            drawable = new GifDrawable(getResources(), id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int drawableSize = dp2px(12);
        drawable.setBounds(0, 0, drawableSize, drawableSize);
        ImageSpan imageSpan = new ImageSpan(drawable);
        sps.setSpan(imageSpan, 0, sps.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sps;
    }


    private int dp2px(float dp){
        return (int) (getResources().getDisplayMetrics().density * dp + 0.5f);
    }

}
