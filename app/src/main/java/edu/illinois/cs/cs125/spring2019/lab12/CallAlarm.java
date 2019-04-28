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
        Intent turnUp = new Intent(context, AlarmAlert.class);
        Bundle bundle = new Bundle();
        bundle.putString("STR_CALLER", "");
        turnUp.putExtras(bundle);
        turnUp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(turnUp);
    }
}
