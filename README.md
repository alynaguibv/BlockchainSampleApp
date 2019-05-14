[![Build Status](https://travis-ci.org/alynaguibv/BlockchainSampleApp.svg?branch=master)](https://travis-ci.org/alynaguibv/BlockchainSampleApp)


## Block chain application 
 
### Description
Sample application to fetch and display average USD market price across major bitcoin exchanges. 

![image info](./documentation/img/screenshot_blockchain_stats.jpg)


### How to run the sample application locally

- Preconditions: 
1. Android SDK API level 28 is installed. for more information please refer to http://www.androiddocs.com/sdk/installing/index.html
2. You have an Android Emulator/ Device to run the application.
3. To create/ download an Android Virtual Device please refer to https://developer.android.com/studio/run/managing-avds
4. "unzip" utility to be installed.

- Steps: 
1. Download the repository folder.
2. Open terminal window then execute the following commands:
```shell
unzip BlockchainSampleApp.zip
cd BlockchainSampleApp
./gradlew assembleDevDebug
adb install -t -r "app/build/outputs/apk/dev/debug/app-dev-debug.apk"
adb shell am start -n app.sample.blockchainsampleapp/.blockchainstats.ui.BlockchainStatsActivity
```