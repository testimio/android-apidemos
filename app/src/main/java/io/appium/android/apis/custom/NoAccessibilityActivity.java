package io.appium.android.apis.custom;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import io.appium.android.apis.R;

public class NoAccessibilityActivity extends Activity {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_accessibility);
        final ViewGroup vg = findViewById(R.id.buttons_parent);
        result = findViewById(R.id.result);

        // Click listeners
        for (int i = 0; i < vg.getChildCount(); i++){
            vg.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v instanceof Button){
                        result.setText(((Button) v).getText());
                    }
                }
            });
        }

        // Accessibility events delegate
        final CompoundButton toggle = findViewById(R.id.toggle_accessibility);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                vg.setAccessibilityDelegate(isChecked ? null : delegate);
            }
        });
        toggle.setChecked(true);
    }

    View.AccessibilityDelegate delegate = new View.AccessibilityDelegate(){
        @Override
        public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
            Log.i("NoAccessibilityActivity", "Event: " + event.toString());
            event.setEventType(AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START);
            return super.onRequestSendAccessibilityEvent(host, child, event);
        }
    };
}
