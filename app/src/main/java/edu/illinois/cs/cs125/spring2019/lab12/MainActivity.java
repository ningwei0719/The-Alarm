package edu.illinois.cs.cs125.spring2019.lab12;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * main.
 */
public class MainActivity extends AppCompatActivity {
    /** */
    TextView show1;
    /** */
    TextView show2;
    /** */
    TextView show3;
    /** */
    Button setTime1;
    /** */
    Button setTime2;
    /** */
    Button setTime3;
    /** */
    Button delete1;
    /** */
    Button delete2;
    /** */
    Button delete3;
    /** */
    String show1String = null;
    /** */
    String show2String = null;
    /** */
    String show3String = null;
    /** */
    String defalutString = "No Alarm";
    /** */
    AlertDialog builder=null;
    /** */
    Calendar c = Calendar.getInstance();
    /** */
    private MediaPlayer mediaPlayer;

    /**
     * create.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.clockmusic2);
        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        show1String = settings.getString("TIME1", defalutString);
        show2String = settings.getString("TIME2", defalutString);
        show3String = settings.getString("TIME3", defalutString);

        InitSetTime1();
        InitSetTime2();
        InitSetTime3();
        InitDelete1();
        InitDelete2();
        InitDelete3();

        show1.setText(show1String);
        show2.setText(show2String);
        show3.setText(show3String);
    }

    /**
     * setTime1.
     */
    private void InitSetTime1() {
        show1 = (TextView) findViewById(R.id.show1);
        setTime1 = (Button) findViewById(R.id.settime1);
        setTime1.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(final TimePicker view, final int hourOfDay,
                                                  final int minute) {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);
                                c.set(Calendar.MILLISECOND, 0);

                                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent.getBroadcast(
                                        MainActivity.this, 0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
                                }

                                String tmpS = format(hourOfDay) + "：" + format(minute);
                                show1.setText(tmpS);

                                //SharedPreferences保存数据，并提交
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();

                                Toast.makeText(MainActivity.this, "Set the Alarm at" + tmpS,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, mHour, mMinute, true).show();
            }
        });
    }

    /**
     * delete1.
     */
    private void InitDelete1() {
        delete1 = (Button) findViewById(R.id.delete1);
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        MainActivity.this, 0, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(MainActivity.this, "alarm deleted",
                        Toast.LENGTH_SHORT).show();
                show1.setText("No Alarm");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "No Alarm");
                editor.commit();
            }
        });
    }

    /**
     * settime2.
     */
    private void InitSetTime2() {
        show2 = (TextView) findViewById(R.id.show2);
        setTime2 = (Button) findViewById(R.id.settime2);
        setTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);


                new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(final TimePicker view, final int hourOfDay,
                                                  final int minute) {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);
                                c.set(Calendar.MILLISECOND, 0);

                                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent.getBroadcast(
                                        MainActivity.this, 0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
                                }

                                String tmpS = format(hourOfDay) + "：" + format(minute);
                                show2.setText(tmpS);

                                //SharedPreferences保存数据，并提交
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();

                                Toast.makeText(MainActivity.this, "Set the Alarm at" + tmpS,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, mHour, mMinute, true).show();
            }
        });
    }

    /**
     * delete2.
     */
    private void InitDelete2() {
        delete2 = (Button) findViewById(R.id.delete2);
        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        MainActivity.this, 0, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(MainActivity.this, "Alarm Deleted",
                        Toast.LENGTH_SHORT).show();
                show2.setText("No Alarm");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "Mo Alarm");
                editor.commit();
            }
        });
    }

    /**
     * settime3.
     */
    private void InitSetTime3() {
        show3 = (TextView) findViewById(R.id.show3);
        setTime3 = (Button) findViewById(R.id.settime3);
        setTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);


                new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(final TimePicker view, final int hourOfDay,
                                                  final int minute) {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);
                                c.set(Calendar.MILLISECOND, 0);

                                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent.getBroadcast(
                                        MainActivity.this, 0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
                                }

                                String tmpS = format(hourOfDay) + "：" +  format(minute);
                                show3.setText(tmpS);

                                //SharedPreferences保存数据，并提交
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();

                                Toast.makeText(MainActivity.this, "Set the Alarm at" + tmpS,
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }, mHour, mMinute, true).show();
            }
        });
    }

    /**
     * delete3.
     */
    private void InitDelete3() {
        delete3 = (Button) findViewById(R.id.delete3);
        delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        MainActivity.this, 0, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(MainActivity.this, "Alarm Deleted",
                        Toast.LENGTH_SHORT).show();
                show3.setText("No Alarm");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "No Alarm");
                editor.commit();
            }
        });
    }

    /**
     * keyup.
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(final int keyCode, final KeyEvent event) {
        mediaPlayer.stop();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mediaPlayer.stop();
            builder = new AlertDialog.Builder(MainActivity.this)

                    .setTitle("Tip: ")
                    .setMessage("Do you want to exit the app?")
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog,
                                                    final int whichButton) {
                                    mediaPlayer.stop();
                                    MainActivity.this.finish();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog,
                                                    final int whichButton) {
                                    mediaPlayer.stop();
                                    builder.dismiss();
                                }
                            }).show();
        }
        return true;
    }

    /**
     * format.
     * @param x - .
     * @return x.
     */
    private String format(final int x) {
        String s = "" + x;
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }

}
