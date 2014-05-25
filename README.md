BlueJava
===============================================================
– An Android application for information about movies
---------------------------------------------------------------

Before you install the application
----------------------------------
You will need the following to install and run the .apk file:
- Android SDK (http://developer.android.com/sdk)
- An Android device allowing USB debugging or an Android Emulator


How to run and install the application
---------------------------------------
In the app/ directory you will find a file named app.apk.
This is the file used to distribute and install our application.

Either make sure to navigate in to the platform-tools/ directory
or make it available in your PATH.

Follow this if you have an Android device:
1. Make sure it's setup and connected via USB
2. Run the following command:

adb -d install path/to/app.apk

Follow this if you have an Android Emulator:
1. Execute the android tool with avd option:
android avd
2. In the Virtual Devices view, select an AVD and click Start.
3. Install the apk from your SDK's tools/ folder:
adb install path/to/app.apk
