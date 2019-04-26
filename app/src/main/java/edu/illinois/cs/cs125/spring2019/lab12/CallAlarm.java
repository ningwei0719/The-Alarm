package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * call.
 */
public class CallAlarm extends BroadcastReceiver {
    /**
     * recieve.
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(final Context context, final Intent intent) {
        Intent intent1 = new Intent(context, AlarmAlert.class);
        Bundle bundle = new Bundle();
        bundle.putString("STR_CALLER", "");
        intent1.putExtras(bundle);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
