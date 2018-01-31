# FinalCountdownTimer
一个根据Android CountDownTimer修改和封装的倒计时的库
  (A revise and encapsulated countdown Library Based on Android CountDownTimer)

# Smaple
        
        //可以进行自定义的配置 (可以自定义配置,都不是必选设置,都有默认值)
        FinalCountDownTimer timer = new FinalCountDownTimer.Builder()
                .setTotalTime(10000) 
                .setCountDownTime(1000) 
                .setOnTickUnit(FinalCountDownTimer.UNIT_MS) 
                .isZeroEnd(true) 
                .cancelAuto(true) 
                .setCountDownCallBack(this) 
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
        
        默认值及方法解释:    
        setTotalTime //设置倒计时的总时长 默认5000
        setCountDownTime //设置每一秒倒计时一次 默认 1000
        setOnTickUnit //设置CallBack的onTick的单位 ms 或 s  默认1000
        isZeroEnd //设置倒计时到 0 还是 1 默认true
        cancelAuto //设置onFinish 自动调用cancel  默认true
        setCountDownCallBack //设置倒计时的回调

# Getting started
        git clone https://github.com/MrQ-Android/FinalCountdownTimer.git
        正在部署到Jcenter仓库的路上.... 以后就可以直接compile了

# Build
      git clone https://github.com/MrQ-Android/FinalCountdownTimer.git

# Bugs and Feedback
For bugs, questions and discussions please use the Github Issues.  
OR .    
Write Email to Mrq_android@163.com

# LICENSE
      Copyright [2017-present] [Mr.Q]

      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0
      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.


