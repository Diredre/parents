package com.example.parents.Widget;

import android.view.animation.LinearInterpolator;

/**
 * 登录界面按钮动画的计算工具
 */
public class JellyInterpolator extends LinearInterpolator {

    private float factor;


    public JellyInterpolator() {
        this.factor = 0.15f;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input)
                * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}