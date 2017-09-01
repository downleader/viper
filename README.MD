# VIPER
VIPER is a software design pattern initially adapted for iOS. This is VIPER for Android! Actually...it's VPER.

## Basics

[VIPER](https://www.objc.io/issues/13-architecture/viper/) = [MVP](https://antonioleiva.com/mvp-android/) + [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)

## Overview
The purpose of this project is to provide basic interfaces of the VIPER components to form a basic module skeleton for further development. Extensive generics usage ensures compile-time safety of the developed components.

## Details
* There is no out-of-the-box routing in Android. Every UI state change must be performed manually. So in this implementation _R_ stands for _Repository_ instead, the component acting as a data storage. Here VIPER is still VIPER.

* The current project version has the Interactor component retrofitted to simplify the logic. So, the Presenter talks to the Repository instead now. Here VIPER becomes VPER now.

* In Android, the View component is the one supposed to be in control. That is, an `Activity` is an entry point. Assuming that, the View is responsible for the VPER module instantiation: creating and binding all the components together.

* Configuration change is a thing. So there was designed an ability for each component to save its own state during any of the configuration changes. The View here is responsible to force the VPER module to save and restore its state at the appropriate points in time, and the VPER module is responsible to propagate these commands down to the components bound to it.

* The Repository is implemented as a component that is loosely coupled with a Presenter. So the Presenter registers itself as a listener in the Repository. That allows the Repository to be retained across configuration changes (assuming the Presenter unregisters itself before the configuration change happens and a new Presenter registers itself in the Repository afterwards), for example, in a retained Fragment. This is useful if the Repository performs data management by itself.

* If the Repository does not perform data management by itself (e.g. the Repository is just a wrapper around the Loader), then the Repository can be destroyed during the configuration change and recreated afterwards, reconnecting to the underlying data manager with a new Context.

## Usage
Every VPER module should have a separate package with a separate subpackage for each component. The VPER module class can be dropped to the root of the module package. Using VPER is just defining and implementing the interfaces for the components.

Default components were created in order to skip state saving and restoring process. The ability to do that is still preserved, the VPER components are just rendered stateless by using empty `DefaultState` class.

### Create an Entity
```java
public class Message {
    
    private final String mText;
    
    public Message(String text) {
        mText = text;
    }
    
    public String getText() {
        return mText;
    }
    
    @Override
    public String toString() {
        return mText;
    }
}
```
It's a POJO.

### Create a Repository

```java
public interface Repository extends DefaultRepository<Repository.Presenter> {
    
    void loadMessage();
    
    interface Presenter extends DefaultPresenter {
        
        void onLoad(Message message);
        
    }
}
```
`Repository` interface needs to extend `DefaultRepository` interface, parameterized with the expected `Repository.Presenter` interface. This expected interface will be implemented by the actual Presenter component and needs to extend `DefaultPresenter` interface.

### Implement the Repository
```java
public class RepositoryImpl extends AbstractRepository<Repository.Presenter>
        implements Repository {

    // ...

    // Implementation can be any. For example, we can supply
    // any newly registered Presenter with the latest data.
    @Override
    public void registerPresenter(Repository.Presenter presenter) {
        super.registerPresenter(presenter);
        if (mPendingMessage != null) {
            mPresenter.onLoad(mPendingMessage);
            mPendingMessage = null;
        }
    }
    
    // ...
}
```
`RepositoryImpl` class needs to implement `Repository` interface and extend `AbstractRepository` abstract class, parameterized with the expected `Repository.Presenter` interface, specified by `Repository` interface.

### Create a Presenter
```java
public interface Presenter extends Repository.Presenter {
    
    void onUserArrived();
    
    interface View extends DefaultView {
        
        void displayProgress(boolean show);
        
        void greetUser(Message message);
        
    }
}
```
`Presenter` interface needs to extend the expected `Repository.Presenter` interface. The expected `View` interface will be implemented by the actual View component and needs to extend `DefaultView` interface.

### Implement the Presenter
```java
public class PresenterImpl extends AbstractPresenter<Repository,
        Repository.Presenter, Presenter.View> implements Presenter {
        
    public PresenterImpl(Repository repository, Presenter.View view) {
        super(repository, view);
    }
    
    @Override
    public void onBind() {
        super.onBind();
        mRepository.registerPresenter(this);
    }
    
    @Override
    public void onUnbind(boolean destroy) {
        mRepository.unregisterPresenter(this);
        super.onUnbind(destroy);
    }
    
    @Override
    public void onUserArrived() {
        mView.displayProgress(true);
        mRepository.loadMessage();
    }
    
    @Override
    public void onLoad(Message message) {
        mView.displayProgress(false);
        mView.greetUser(message);
    }
}
```
`PresenterImpl` class needs to implement `Presenter` interface and extend `AbstractPresenter` abstract class, parameterized with `Repository` interface, the expected `Repository.Presenter` interface and the expected `Presenter.View` interface. The Presenter implementation is required to register and unregister itself from the Repository when needed. It usually needs to be done in `onBind()` and `onUnbind(boolean)` callbacks as that's what they were designed for.

**Warning**: if you do something in `onUnbind(boolean)` callback, make sure you call super implementation last.

### Implement the View
```java
public class ViperActivity extends AppCompatActivity implements Presenter.View {
    
    private ViperModule mViperModule;
    
    private ProgressBar mProgressBar;
    
    private TextView mMessageView;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viper);
        setupViper();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mViperModule.getPresenter().onUserArrived();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViperModule.unbind(isFinishing());
    }
    
    @Override
    public void onBind() {
        // The View is bound to the module
    }
    
    @Override
    public void onUnbind(boolean destroy) {
        // The View is unbound from the module
        mViperModule = null;
    }
    
    @Override
    public DefaultState onSaveState() {
        // Default implementation doesn't have a state
        return null;
    }
    
    @Override
    public void onRestoreState(DefaultState state) {
        // Default implementation doesn't have a state
    }
    
    @Override
    public void displayProgress(boolean show) {
        mMessageView.setVisibility(show ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    
    @Override
    public void greetUser(Message message) {
        mMessageView.setText(message.getText());
    }
    
    private void setupViper() {
        Repository repository = new RepositoryImpl();
        Presenter presenter = new PresenterImpl(repository, this);
        
        mViperModule = new ViperModule(repository, presenter, this);
        mViperModule.bind();
    }
}
```
It can be `Activity`, `Fragment`, or any other class, not necessarily related to UI. The View class needs to implement the expected `Presenter.View` interface. The View is also responsible for the module instantiation and its lifecycle control.

### Create the Module
```java
public class ViperModule extends DefaultModule<Repository,
        Presenter, Presenter.View> {
    
    public ViperModule(Repository repository,
                       Presenter presenter,
                       Presenter.View view) {
        super(repository, presenter, view);
    }
}
```
The module class needs to extend `DefaultModule` class, parameterized with `Repository` interface, `Presenter` interface and the expected `Presenter.View` interface. The only required code in the module class is a call to the super constructor of `DefaultModule`.

## Lifecycle
The lifecycle of the VPER components was introduced because of two requirements:
* the Presenter needs to know when to register and unregister itself from the Repository;
* each component needs a way to save and restore its state when it's appropriate.

That's why the base interface of a VPER component looks like this:
```java
public interface ViperComponent<State extends ViperState> {
    
    void onBind();
    
    void onUnbind(boolean destroy);
    
    State onSaveState();
    
    void onRestoreState(State state);
    
}
```

The requirements described above can be satisfied by `Activity` only (even if your View is `Fragment`, only its parent `Activity` knows about the timing of the configuration changes). That's why the View needs to call:
* `bind()` on the VPER module (which in turn calls `onBind()` on each of the components);
* `unbind(boolean)` on the VPER module (which in turn calls `onUnbind(boolean)` on each of the components), where the boolean parameter tells whether the components should destroy themselves or they may continue running (like the Repository);
* `saveState(Bundle)` on the VPER module so that each component can save its state to the passed Bundle using a custom `Transformer` implementation to preserve Clean Architecture principles;
* `restoreState(Bundle)` on the VPER module so that each component can restore its state from the passed Bundle using a custom `Transformer` implementation to preserve Clean Architecture principles.

Default VPER components are stateless, so there is no need to call any methods to save or restore it.

## Examples
In the project's code there are two VPER modules implemented: `welcome` and `goodbye`. If you wanna go hardcore and maintain a separate state for each of the components, go and learn how `welcome` module is implemented. If you just wanna code already, jump into `goodbye` module and see how easy and safe it is to use VPER in Android.

## Support
If you have any questions, don't hesitate to contact me [via email](mailto:andrey.vashkevich@gmail.com).

Good luck!