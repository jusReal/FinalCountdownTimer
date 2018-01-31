package com.mrq.android.ibrary;

/**
 * 项目名称：STCameraStreamingDemo
 * 类描述：一个倒计时的工具类
 * <p>
 * sample 1:全设置的例子 (自定义配置)
 * <p>
 * FinalCountDownTimer build = new FinalCountDownTimer.Builder()
 * .setTotalTime(10000)
 * .setCountDownTime(1000)
 * .cancelAuto(true)
 * .setCountDownCallBack(this)
 * .isZeroEnd(false)
 * .setOnTickUnit(FinalCountDownTimer.UNIT_S)
 * .build();
 * build.start();
 * <p>
 * <p>
 * sample 2: 快速设置的例子 (默认配置)
 * <p>
 * FinalCountDownTimer.createDefault(5000, this).start();
 * <p>
 * <p>
 * 创建人：sensetime-jiayinqi
 * 创建时间：18-1-25 上午11:37
 */
public class FinalCountDownTimer {

    /**
     * 设置的总时长   默认5秒
     */
    private long mTotalTime = 5000;

    /**
     * 倒计时的阶梯时长  默认一秒
     */
    private long mCountDownTime = 1000;

    /**
     * onFinish 自动cancel 默认为true
     */
    private boolean mAutoCancel = true;

    /**
     * 最后到1结束 还是到0结束
     */
    private boolean isZeroEnd = true;

    /**
     * onTick 返回的单位 默认是毫秒
     */
    private int mOnTickUnit = UNIT_MS;

    /**
     * onTick 返回单位 毫秒
     */
    public static final int UNIT_MS = 1;

    /**
     * onTick 返回单位 秒
     */
    public static final int UNIT_S = 2;


    private MyCountDownTimer mTimer;

    private FinalCountDownTimer() {
    }

    private FinalCountDownTimer(Builder builder) {
        if (builder == null) {
            throw new IllegalArgumentException("builder must be not null!");
        }

        this.mTotalTime = builder.mTotalTime;
        this.mCountDownTime = builder.mCountDownTime;
        this.mOnTimeDownCallBack = builder.mOnTimeDownCallBack;
        this.mAutoCancel = builder.mAutoCancel;
        this.isZeroEnd = builder.isZeroEnd;
        this.mOnTickUnit = builder.mOnTickUnit;

        mTimer = new MyCountDownTimer(mTotalTime, mCountDownTime) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished % 1000 == 0 && millisUntilFinished != 0) {
                    millisUntilFinished -= 1;
                }
//                Log.i("TAG", "onTick : util + " + (millisUntilFinished + (isZeroEnd ? 0 : 0)));
//                Log.i("MRQ", "onTick : util + " + getMillisUntilFinished(millisUntilFinished));
                //接口回调
                if (mOnTimeDownCallBack != null) {
                    mOnTimeDownCallBack.onTick(getMillisUntilFinished(millisUntilFinished));
                }
            }

            private long getMillisUntilFinished(long millisUntilFinished) {
                if (mOnTickUnit == UNIT_MS) {
                    return millisUntilFinished + (isZeroEnd ? 0 : 1000);
                } else if (mOnTickUnit == UNIT_S) {
                    return (millisUntilFinished + (isZeroEnd ? 0 : 1000)) / 1000;
                } else {
                    return millisUntilFinished + (isZeroEnd ? 0 : 1000);
                }
            }

            @Override
            public void onFinish() {
                if (mOnTimeDownCallBack != null) {
                    mOnTimeDownCallBack.onFinish();
                }
                if (mAutoCancel) {
                    this.cancel();
                }
            }
        };

    }

    /**
     * createDefault 默认每1000ms countDown一次  默认onFinish之后 自动cancel
     *
     * @param totalTime 倒计时的总时长
     * @param callBack  回调接口
     * @return
     */
    public static FinalCountDownTimer createDefault(long totalTime, OnTimeDownCallBack callBack) {
        return new Builder(totalTime, callBack).build();
    }


    public static class Builder {

        private OnTimeDownCallBack mOnTimeDownCallBack;

        private long mTotalTime = 5000;//设置的时长

        private long mCountDownTime = 1000;//倒计时的阶梯时长

        private boolean mAutoCancel = true;

        private boolean isZeroEnd = true;//最后到1结束 还是到0结束

        private int mOnTickUnit = UNIT_MS;

        public Builder() {

        }

        private Builder(long totalTime, OnTimeDownCallBack callBack) {
            this.mTotalTime = totalTime;
            this.mOnTimeDownCallBack = callBack;
        }


        /**
         * 设置倒计时的总时长
         *
         * @param totalTime
         * @return
         */
        public Builder setTotalTime(long totalTime) {
            this.mTotalTime = totalTime;
            return this;
        }

        /**
         * 设置每多少ms 降一次
         *
         * @param countDownTime
         * @return
         */
        public Builder setCountDownTime(long countDownTime) {
            this.mCountDownTime = countDownTime;
            return this;
        }

        /**
         * 设置回调
         *
         * @param callBack
         * @return
         */
        public Builder setCountDownCallBack(OnTimeDownCallBack callBack) {
            this.mOnTimeDownCallBack = callBack;
            return this;
        }

        /**
         * 自动cancel
         *
         * @param cancel
         * @return
         */
        public Builder cancelAuto(boolean cancel) {
            this.mAutoCancel = cancel;
            return this;
        }

        /**
         * 最后0结束还是1结束
         *
         * @param isZeroEnd
         * @return
         */
        public Builder isZeroEnd(boolean isZeroEnd) {
            this.isZeroEnd = isZeroEnd;
            return this;
        }

        /**
         * onTick return ms 还是 s
         *
         * @param unit
         * @return
         */
        public Builder setOnTickUnit(int unit) {
            this.mOnTickUnit = unit;
            return this;
        }

        public FinalCountDownTimer build() {
            return new FinalCountDownTimer(this);
        }

    }

    public void start() {
        if (mTimer != null) {
            mTimer.start();
        }
    }

    public void cancel() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }


    //回调接口的引用
    private OnTimeDownCallBack mOnTimeDownCallBack;

    /**
     * 回调接口
     *
     * @author Justin
     * @Date 18-1-25
     */
    public interface OnTimeDownCallBack {
        void onTick(long millisUntilFinished);

        void onFinish();
    }

}
