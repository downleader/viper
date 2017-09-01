# VIPER
VIPER is a software design pattern initially adapted for iOS. This is VIPER for Android!

## Basics

[VIPER](https://www.objc.io/issues/13-architecture/viper/) = [MVP](https://antonioleiva.com/mvp-android/) + [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)

## Overview
The purpose of this project is to provide basic interfaces of the VIPER components to form a basic module skeleton for further development. Extensive generics usage ensures compile-time safety of the developed components.

## Details
* There is no out-of-the-box routing in Android. Every UI state change must be performed manually. So in this implementation _R_ stands for _Repository_ instead, the component acting as a data storage. VIPER is still VIPER.

* The current project version has the Interactor component retrofitted to simplify the logic. So, the Presenter talks to the Repository instead now. VIPER is VPER now.

* In Android, the View component is the one supposed to be in control. That is, an Activity is an entry point. Assuming that, the View is responsible for VIPER module instantiation: creating and binding all the components together.

* Configuration change is a thing. So there was designed an ability for each component to save its own state during any of the configuration changes. The View here is responsible to force the VIPER module to save and restore its state at the appropriate points in time, and the VIPER module is responsible to propagate these commands down to the components bound to it.

* The Repository is implemented as a component that is loosely coupled with a Presenter. So the Presenter registers itself as a listener in the Repository. That allows the Repository to be retained across configuration changes (assuming the Presenter unregisters itself before the configuration change happens and a new Presenter registers itself in the Repository afterwards), for example, in a retained Fragment. This is useful if the Repository performs data management by itself.

* If the Repository does not perform data management by itself (e.g. the Repository is just a wrapper around the Loader), then the Repository can be destroyed during the configuration change and recreated afterwards, reconnecting to the underlying data manager with a new Context.