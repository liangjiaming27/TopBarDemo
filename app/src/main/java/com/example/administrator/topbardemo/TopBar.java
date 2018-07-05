package com.example.administrator.topbardemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TopBar extends RelativeLayout {

    private ImageView imageLeft;
    private TextView textViewCenter;
    private Button btRight;

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    private int mTitleTextColor;
    private int mTitleSize;
    private String mTitle;
    private boolean isVisible;

    private LayoutParams mLeftlayoutParams;
    private LayoutParams mRightlayoutParams;
    private LayoutParams mCenterlayoutParams;

    private topbarClickListener topbarClickListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor,0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
//        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitleSize = (int) ta.getDimension(R.styleable.TopBar_titleTextSize, 10);

        mTitle = ta.getString(R.styleable.TopBar_title);
        isVisible = ta.getBoolean(R.styleable.TopBar_rightVisible, false);

        ta.recycle();

        imageLeft = new ImageView(context);
        textViewCenter = new TextView(context);
        btRight = new Button(context);

        if (isVisible) {
            btRight.setVisibility(GONE);
        } else {
            btRight.setVisibility(VISIBLE);
        }

        imageLeft.setImageDrawable(mLeftBackground);
//        imageLeft.setBackgroundColor(mLeftTextColor);


        textViewCenter.setSingleLine();
        textViewCenter.setEllipsize(TextUtils.TruncateAt.END);
        textViewCenter.setTextColor(mTitleTextColor);
        textViewCenter.setText(mTitle);
        //传递过来的是sp 需要做转换
        Log.e("TopBar","mTitleSize:  "+mTitleSize);
        textViewCenter.setTextSize(18);

        btRight.setTextColor(mRightTextColor);
        btRight.setText(mRightText);
        btRight.setBackground(mRightBackground);

        textViewCenter.setGravity(Gravity.CENTER);

        mLeftlayoutParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);

        mLeftlayoutParams.setMargins(30,0,0,0);
//        imageLeft.setLayoutParams(mLeftlayoutParams);

        mLeftlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        //添加到viewGroup
        addView(imageLeft, mLeftlayoutParams);

        mRightlayoutParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);

        mRightlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(btRight, mRightlayoutParams);

        mCenterlayoutParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);

        mCenterlayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(textViewCenter, mCenterlayoutParams);

        imageLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topbarClickListener != null) {
                    topbarClickListener.leftClick();
                }
            }
        });

        btRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topbarClickListener != null) {
                    topbarClickListener.RightClick();
                }
            }
        });
    }

    public void setOnTopbarClickListener(topbarClickListener listener) {
        topbarClickListener = listener;
    }
}
