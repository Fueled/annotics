# Annotics [![](https://jitpack.io/v/Fueled/annotics.svg)](https://jitpack.io/#Fueled/annotics)
Annotation-triggered method call event tracking.

## Install
Add the changes below to your root `build.gradle`:

```groovy
buildscript {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }

    dependencies {
        ...
        classpath 'com.github.fueled.annotics:plugin:{ANNOTICS_VERSION}'
    }
}

allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```
Apply annotics plugin to your app module or/and library modules `build.gradle`:

```groovy
apply plugin: 'annotics'
```
Initialize Annotics in your `Application#onCreate` method:
```
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Annotics.init(new SegmentAdapter());
    }
}
```
When initializing Annotics you will need to provide it with an adapter to the analytics library you are using.

You can implement your own adapter by simply creating a custom class that implements the interface `AnalyticsAdapter` or by simply using an adapter for one of the supported libraries.

The list below shows the libraries that are currentlly supported:

* Google Analytics:
```groovy
compile 'com.github.fueled.annotics:adapter-google:{ANNOTICS_VERSION}'
```
* Segment:

```groovy
compile 'com.github.fueled.annotics:adapter-segment:{ANNOTICS_VERSION}'
```

# Usage

Event can be easily tracked by annotiating either the constuctor or the method you want to track with the `@TrackEvent` annotation. You will then have to specify the event name and the event type. Specifying an event type is optional as by default `EventType.TRACK` is used. The recorded event will also include all parameters passed to the method as event properties.
```java
@TrackEvent(value = "ACCOUNT_SCREEN", type = EventType.SCREEN_VIEW)
public TrackedClass(String title) {
    ...
}
```

You can also track events while preventing method parameters from being recorded.
```java
@TrackEvent(value = "EVENT_UPDATE_CARD_INFO", trackParameters = false)
public void updateCardInformation(String cardNumber, String expDate, String cvv) {
    ...
}
```

Only specific parameters can also be hidden using the `EventValue` annotation and specifying the `EventValue#hidden()` as `true`.
```java
@TrackEvent("EVENT_LOGIN")
public void login(String email, @EventValue(hidden = true) String password) {
    ...
}
```

It's also possible to specify custom names for the parameters by using the `EventValue` annotation and specifying a value for the name to be used.
```java
@TrackEvent("EVENT_UPDATE_ACCOUNT")
public void updateAccount(@EventValue("First Name") String firstName, @EventValue("Last Name") String lastName) {
    ...
}
```

# License

    Copyright 2017 Fueled

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.