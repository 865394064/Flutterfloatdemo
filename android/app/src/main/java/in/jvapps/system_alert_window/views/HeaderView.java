package in.jvapps.system_alert_window.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Map;

import in.jvapps.system_alert_window.models.Decoration;
import in.jvapps.system_alert_window.models.Padding;
import in.jvapps.system_alert_window.utils.Commons;
import in.jvapps.system_alert_window.utils.UiBuilder;

import static in.jvapps.system_alert_window.utils.Constants.*;

public class HeaderView {
    private final Map<String, Object> headerMap;
    private final Context context;
    private final UiBuilder uiBuilder = UiBuilder.getInstance();

    public HeaderView(Context context, Map<String, Object> headerMap) {
        this.context = context;
        this.headerMap = headerMap;
    }


    @SuppressWarnings("unused")
    public RelativeLayout getRelativeView() {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        Decoration decoration = uiBuilder.getDecoration(context, Commons.getMapFromObject(headerMap, KEY_DECORATION));
        if (decoration != null) {
            GradientDrawable gd = uiBuilder.getGradientDrawable(decoration);
            relativeLayout.setBackground(gd);
        } else {
            relativeLayout.setBackgroundColor(Color.WHITE);
        }
        Map<String, Object> titleMap = Commons.getMapFromObject(headerMap, KEY_TITLE);
        Map<String, Object> subTitleMap = Commons.getMapFromObject(headerMap, KEY_SUBTITLE);
        Map<String, Object> buttonMap = Commons.getMapFromObject(headerMap, KEY_BUTTON);
        Padding padding = uiBuilder.getPadding(context, headerMap.get(KEY_PADDING));
        relativeLayout.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
        boolean isShowButton = (buttonMap != null);
        assert titleMap != null;
        View textColumn = createTextColumn(titleMap, subTitleMap);
        if (isShowButton) {
            String buttonPosition = (String) headerMap.get(KEY_BUTTON_POSITION);
            Button button = uiBuilder.getButtonView(context, buttonMap);
            if ("leading".equals(buttonPosition)) {
                relativeLayout.addView(button);
                relativeLayout.addView(textColumn);
            } else {
                relativeLayout.addView(textColumn);
                relativeLayout.addView(button);
            }
        } else {
            relativeLayout.addView(textColumn);
        }

        return relativeLayout;
    }

    public LinearLayout getView() {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundColor(Color.GREEN);
        Log.d("WindowServiceNew", Commons.getMapFromObject(headerMap, KEY_DECORATION).toString());
        Decoration decoration = uiBuilder.getDecoration(context, Commons.getMapFromObject(headerMap, KEY_DECORATION));
        if (decoration != null) {
            GradientDrawable gd = uiBuilder.getGradientDrawable(decoration);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Log.d("WindowServiceNew", gd.getColor().toString());
            }
            linearLayout.setBackground(gd);
        } else {
            linearLayout.setBackgroundColor(Color.WHITE);
        }
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Map<String, Object> titleMap = Commons.getMapFromObject(headerMap, KEY_TITLE);
        Map<String, Object> subTitleMap = Commons.getMapFromObject(headerMap, KEY_SUBTITLE);
        Map<String, Object> buttonMap = Commons.getMapFromObject(headerMap, KEY_BUTTON);
        Padding padding = uiBuilder.getPadding(context, headerMap.get(KEY_PADDING));
        linearLayout.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
        boolean isShowButton = (buttonMap != null);
        //assert titleMap != null;
        View textColumn = createTextColumn(titleMap, subTitleMap);
        if (isShowButton) {
            String buttonPosition = (String) headerMap.get(KEY_BUTTON_POSITION);
            Button button = uiBuilder.getButtonView(context, buttonMap);
            if ("leading".equals(buttonPosition)) {
                linearLayout.addView(button);
                if (textColumn != null) {
                    linearLayout.addView(textColumn);
                }
            } else {
                if (textColumn != null) {
                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            1.0f
                    );
                    textColumn.setLayoutParams(param);
                    linearLayout.addView(textColumn);
                }
                linearLayout.addView(button);
            }
        } else {
            linearLayout.addView(textColumn);
        }

        return linearLayout;
    }

    private View createTextColumn(Map<String, Object> titleMap, Map<String, Object> subTitleMap) {
        TextView titleView = uiBuilder.getTextView(context, titleMap);
        if (subTitleMap != null) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(titleView);
            linearLayout.addView(uiBuilder.getTextView(context, subTitleMap));
            return linearLayout;
        }
        return titleView;
    }
}