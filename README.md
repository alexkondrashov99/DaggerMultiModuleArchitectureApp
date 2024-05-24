# Dagger multi-module architecture app



## Description

It's a simple project to demonstrate the example of how we can come up with the multi-module architecture of Android project using Dagger Android Injection and Android Dynamic Feature

### Dependencies/Features

* Dagger DI
* Dagger Android Injection
* Android Dynamic Feature

Inspiration, code snippets, etc.
* [Using Dagger in multi-module apps documentation](https://developer.android.com/training/dependency-injection/dagger-multi-module)
* [Dagger and Dynamic Features - Part 1](https://medium.com/pulselive/everything-they-dont-tell-you-about-instant-apps-problems-with-dagger-android-af5b61f5a419)
* [Dagger and Dynamic Features - Part 2](https://medium.com/pulselive/everything-they-dont-tell-you-about-instant-apps-2-android-manifest-b0b57b78f4b8)
* [Dagger 2 in Android Dynamic Multimodule project](https://medium.com/@skywall/dagger-2-in-android-dynamic-multimodule-project-c90954630615)
* [Dependency injection in a multi-module project](https://medium.com/androiddevelopers/dependency-injection-in-a-multi-module-project-1a09511c14b7)
* [Patterns for accessing code from Dynamic Feature Modules](https://medium.com/androiddevelopers/patterns-for-accessing-code-from-dynamic-feature-modules-7e5dca6f9123)


## How to test DynamicFeature installing locally
1) Download bundletool https://github.com/google/bundletool/releases
2) Build project via .aab
3) Create .apks file with next command: 
```
java -jar "D:\bundletool\bundletool-all-1.16.0.jar" build-apks --local-testing --bundle="D:\bundletool\test.aab" --output="D:\bundletool\test.apks"
```
4) install test.apks on ADB connected device with next command:
```
java -jar "D:\bundletool\bundletool-all-1.16.0.jar" install-apks --apks test.apks
```
OR 
build universal.apk with next command:
```
java -jar "D:\bundletool\bundletool-all-1.16.0.jar" build-apks --bundle="D:\bundletool\test.aab" --output="D:\bundletool\universal.apks" --mode="universal"
```
and then open unversal.apks as ZIP-archive. There will be "universal.apk" file you can install on any device. 

More info about local testing dynamicFeature here 👉 https://developer.android.com/guide/playcore/feature-delivery/on-demand#local-testing
