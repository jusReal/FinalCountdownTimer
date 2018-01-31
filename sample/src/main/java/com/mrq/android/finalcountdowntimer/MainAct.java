package com.mrq.android.finalcountdowntimer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mrq.android.ibrary.FinalCountDownTimer;

/**
 * 项目名称：FinalCountdownTimer
 * 类描述：
 * 创建人：sensetime-jiayinqi
 * 创建时间：18-1-31 下午2:39
 */
public class MainAct extends Activity implements FinalCountDownTimer.OnTimeDownCallBack {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //可以进行自定义的配置 (可以自定义配置,都不是必选设置,都有默认值)
        FinalCountDownTimer timer = new FinalCountDownTimer.Builder()
                .setTotalTime(10000) //设置倒计时的总时长 默认5000
                .setCountDownTime(1000) //设置每一秒倒计时一次 默认 1000
                .setOnTickUnit(FinalCountDownTimer.UNIT_MS) //设置CallBack的onTick的单位 ms 或 s  默认1000
                .isZeroEnd(true) //设置倒计时到 0 还是 1 默认true
                .cancelAuto(true) //设置onFinish 自动调用cancel  默认true
                .setCountDownCallBack(this) //设置倒计时的回调
                .build();
        timer.start();

        //快速获取Default的Timer 默认每一秒倒计时一次
        FinalCountDownTimer countDownTimer = FinalCountDownTimer.createDefault(10000, new FinalCountDownTimer.OnTimeDownCallBack() {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("TAG", "onTick : " + millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Log.i("TAG", "onFinish : ");
            }
        });
        countDownTimer.start();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        Log.i("TAG", "onTick : " + millisUntilFinished);
    }

    @Override
    public void onFinish() {
        Log.i("TAG", "onFinish : ");
    }
}
