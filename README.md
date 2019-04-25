dynamic run code for android

delete com.example.dynamicCmd.java

build

write com.example.dynamicCmd.java

build

assembleDynamicCode

dx --dex --output=dynamic_dex.jar dynamicCode.jar

adb push dynamic_dex.jar /sdcard/test/dynamic_dex.jar 

run `DynamicCmdManager.getInstance().invoke()`