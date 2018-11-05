package com.guojiel.gifemoji;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;

public class JTextView extends AppCompatTextView {

    public JTextView(Context context) {
        super(context);
    }

    public JTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if(text instanceof Spanned){
            ImageSpan[] spans = ((Spanned) text).getSpans(0, text.length(), ImageSpan.class);
            if(spans != null && spans.length > 0){
                for (int i = 0; i < spans.length; i++) {
                    Drawable drawable = spans[i].getDrawable();
                    if(drawable != null){
                        drawable.setCallback(this);
                        drawable.setVisible(true, true);
                    }
                }
            }
        }
        super.setText(text, type);
    }

    private boolean verifySpanDrawable(@NonNull Drawable who){
        CharSequence text = getText();
        if(text instanceof Spanned){
            ImageSpan[] spans = ((Spanned) text).getSpans(0, text.length(), ImageSpan.class);
            if(spans != null && spans.length > 0){
                for (int i = 0; i < spans.length; i++) {
                    Drawable drawable = spans[i].getDrawable();
                    if(drawable == who){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {
        if(!verifySpanDrawable(drawable)){
            super.invalidateDrawable(drawable);
            return;
        }
        invalidate();
    }
}
