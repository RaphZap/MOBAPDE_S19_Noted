package com.ccs.s19.noted;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class InputNewActivity extends AppCompatActivity {

    private EditText editTextData;
    private Button btnCreate;
    private Button btnCancel;
    private CheckBox checkBoxPinned;
    private EditText editTextGroup;
    private EditText inputtedHour;
    private EditText inputtedMinute;
    private EditText inputtedSecond;
    private InputNewActivity inActivity;

    private int jobID;

    public static final String UI_UPDATE_TAG =
            "com.ccs.s19.noted.InputNewActivity.ActivityReceiver";
    public static final String CHANNEL_ID=
            "com.ccs.s19.noted.NOTIFICATION";
    private BroadcastReceiver receiver = new ActivityReceiver();

//    private NoteAdapter adapter = new NoteAdapter(this.getApplicationContext());
    private myDbAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_new);

        editTextData = findViewById(R.id.editTextData);
        btnCreate = findViewById(R.id.btnCreate);
        btnCancel = findViewById(R.id.btnCancel);
        checkBoxPinned = findViewById(R.id.checkBoxPinned);
        editTextGroup = findViewById(R.id.editTextGroup);
        inputtedHour = findViewById(R.id.inputHour);
        inputtedMinute = findViewById(R.id.inputMinute);
        inputtedSecond = findViewById(R.id.inputSeconds);
        inActivity = this;

        jobID = 1;

        //(2) The registration of ActivityReceiver is similar to how it is done in the previous
        //    code sample.
        IntentFilter filter = new IntentFilter();
        filter.addAction(UI_UPDATE_TAG);
        registerReceiver(receiver, filter);


        db = new myDbAdapter(getApplicationContext());

        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String addedData = editTextData.getText().toString();
                String group = editTextGroup.getText().toString();
                Boolean isPin = checkBoxPinned.isChecked();
                String hour = inputtedHour.getText().toString();
                String minute = inputtedMinute.getText().toString();
                String second = inputtedSecond.getText().toString();

                if(hour.startsWith("1") || hour.startsWith("2") || hour.startsWith("3") || hour.startsWith("4") ||
                    hour.startsWith("5") || hour.startsWith("6") || hour.startsWith("7") || hour.startsWith("8") ||
                    hour.startsWith("9")){
                    System.out.println(hour + "`````");
                }else hour = "0";
                if(minute.startsWith("1") || minute.startsWith("2") || minute.startsWith("3") || minute.startsWith("4") ||
                    minute.startsWith("5") || minute.startsWith("6") || minute.startsWith("7") || minute.startsWith("8") ||
                    minute.startsWith("9")){
                    System.out.println(minute + "`````");
                }else minute = "0";
                if(second.startsWith("1") || second.startsWith("2") || second.startsWith("3") || second.startsWith("4") ||
                    second.startsWith("5") || second.startsWith("6") || second.startsWith("7") || second.startsWith("8") ||
                    second.startsWith("9")){
                    System.out.println(second + "`````");
                }else second = "0";

                System.out.println(hour + "" + minute + "" + second + "~~~");

                Log.d("ALARM AND BROADCAST","MainActivity triggerAlarm "+jobID);

                //(3) The first thing to be made is an intent. The intent will be directed to the tag
                //    instructed by the string UI_UPDATE_TAG
                Intent alarmIntent = new Intent(UI_UPDATE_TAG);
                alarmIntent.putExtra("ID", jobID);
                jobID++;

                //(4) The next is a PendingIntent which is an intent but used for pending tasks. Note that
                //    for every new PendingIntent to be sent, one must update the request code.
                PendingIntent pendingIntent = PendingIntent.getBroadcast(inActivity,
                        1000000+jobID, alarmIntent, 0);

                int time = (Integer.parseInt(hour) * 3600000) + (Integer.parseInt(minute) * 60000) + (Integer.parseInt(second) * 1000);

                //(5) Finally the AlarmManager manages how the alarm will be sent. This Alarm will manage
                //    and schedule when the alarm is to be sent.
                AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, pendingIntent);

                if (addedData.equalsIgnoreCase("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Note is empty! ",
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    // add data into the DB. id returns -1 if there's a problem inserting
                    System.out.println(hour + "" + minute + "" + second + " ```");
                    long id = db.insertData(addedData,0, group, isPin, hour, minute, second);
                    if (id > 0) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Note added!",
                                Toast.LENGTH_SHORT);
                        toast.show();

                        // Close activity
                        finish();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "ERROR! not addded!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Cancelled!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public class ActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("ALARM AND BROADCAST","ActivityReceiver onReceive");

            createNotificationChannel();
            createBasicNotification();
        }
    }

    private void createNotificationChannel() {
        //(1) In higher versions of Android, there is a need to create notification channels where
        //    notifications are grouped. Not all systems support this as it depends on the Android
        //    version to be used.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Noted", importance);
            channel.setDescription("Noted: A reminder application");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            Log.d("Main a09a_notifications","createNotificationChannel");
        }
    }

    public void createBasicNotification(){
        //(2) Every notification is built using the Notification Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);

        //(3) Various notification attributes can be declared here. Note that ones that are important:
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle(editTextData.getText().toString());
        builder.setContentText(editTextData.getText().toString());
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //(4) These attributes are still important though not required to execute
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background));
        builder.setAutoCancel(true);

        //(5) Notifications are run by calling a notification manager and calling the notify function
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(jobID--, builder.build());
        Log.d("Main a09a_notifications","createNotification");
    }
}
