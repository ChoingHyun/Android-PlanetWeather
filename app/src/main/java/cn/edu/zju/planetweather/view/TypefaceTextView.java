package cn.edu.zju.planetweather.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by changhuiyuan on 15/8/4.
 */
public class TypefaceTextView extends TextView {

    private String font;

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FontTextView, defStyleAttr, 0);
//        font = a.getString(R.styleable.FontTextView_font);
//        a.recycle();
//        try {
//            setFont(font,context); // public method to set custom font
//        }catch (Throwable throwable){
//            L.e(throwable.toString());
//        }
    }


    public void setFont(String font, Context context) {
        this.font = font;
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), font);
        setTypeface(typeface);
    }
}
