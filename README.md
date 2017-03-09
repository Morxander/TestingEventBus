# TestingEventBus

You can read this article on my [Blog](http://blog.morad-edwar.com/intro-to-eventbus-android-library/)

I started to use `EventBus` library of [greenrobot](http://greenrobot.org/eventbus/) and I wanted to share it with you. `EventBus` is a publish/subscribe event bus optimized for Android according to the description in the readme file on their repo. In other word it's a Bus for events to transfer the data between your application classes.
I am using `MVP` as a design pattern for my apps but I will just show how to work with the `EventBus` library on an empty Android Project so every time I'll mention the `Activity` It will be your `Presenter` if you're using `MVP` like me.
Also I created a `github` repo with an example project and you will find its link on the end of this article.

### Create a new Android project
create a new Android project and add the following line to the app gradle file :

    compile 'org.greenrobot:eventbus:3.0.0'
That's how you tell `Android Studio` to download the `EventBus` library and include it to your project.

### Set the UI of the MainActivity
Open the `activity_main.xml` file and add the following lines in it.

    <TextView
        android:id="@+id/mainactivity_textview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="20dp"
        android:textSize="25sp"
        android:text="The message will be here" />

    <Button
        android:id="@+id/mainactivity_button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="Go To Next Activity"/>

Then your edit the MainActivity class to be like this 
	public class MainActivity extends AppCompatActivity {

	    TextView mainActivityTextView;
	    Button mainActivityButton;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// init the views
		mainActivityTextView = (TextView) findViewById(R.id.mainactivity_textview);
		mainActivityButton = (Button) findViewById(R.id.mainactivity_button);
	}
### Create the ChildActivity 
Create a new activity called `ChildActivity`.

**XML** :

    <EditText
        android:id="@+id/childactivity_edittext"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="20dp"
        android:textSize="25sp"
        />

    <Button
        android:id="@+id/childactivity_button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="Send To Main Activity"/>

**Java** :

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        // init the views
        childActivityEditText = (EditText) findViewById(R.id.childactivity_edittext);
        childActivityButton = (Button) findViewById(R.id.childactivity_button);
    }

### Create the Message class
We have to create a class for the message which will be transfered between our activities and you have the freedom to call it whatever you like and to have any method, In this case we will call it `MessageEvent` and we will put a `String` var called `message` and set a getter&setter for it.

    public class MessageEvent {
        private String message;
    
        public String getMessage() {
            return message;
        }
    
        public void setMessage(String message) {
            this.message = message;
        }
    }

### Register your MainActivity to the EventBus
You have to register your activity to the `EventBus` you have to put this like in the `onCreate` method :

    EventBus.getDefault().register(this);

and to understand it, the `EventBus` class is a `Singleton` class and the `getDefault()` is a static method which returns the instance of this class.
also the `register()` is the method which register the views/class to the `EventBus` and we pass the mainactivity object to it.

Now your `MainActivity` is registered but how to pick up the call when the `EventBus` publish the event?
just create a `onMessageEvent` method and use the `@Subscribe` annotation above it.

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent){
        mainActivityTextView.setText(messageEvent.getMessage());
    }
And now every time the `EventBus` will publish the `MessageEvent` you activity will know about it.

### Post the message to the EventBus
On your `ChildActivity` put this code 

        childActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setMessage(childActivityEditText.getText().toString());

                EventBus.getDefault().post(messageEvent);
            }
        });
which is just creating a new object of `MessageEvent` and uses the `post()` method of the `EventBus` to tell it to publish the new `MessageEvent` object to whoever cares about it.

### Why to use EventBus?
It will help you to apply the publisher/subscriber pattern without getting your hand dirty with the details, and of course they already took care of the performance and the optimizations for you, so don't reinvent the wheel.

### Example
I create a `Github` repo to be an example for you : https://github.com/Morxander/TestingEventBus

*I welcome any suggestion or advices about what I wrote here on this article* **:)** 
 
