package com.app.fontawesome;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Kalpesh on 24/10/17.
 */

public class FontAwesome {

    public static final String FA_SORT = "\uF0dC";
    public static final String FA_FILTER = "\uF0B0";
    /**
     * FontAwesome name with extension. Required to reference the font from the Assets folder
     */
    public static String FONT_NAME = "fonts/fontawesome.ttf";
    public static String FA_SWITCH = "\uf021";

    public static String FA_APPROVE = "\uF164";
    public static String FA_REPLICATE = "\uF0C5";
    public static String FA_REJECT = "\uF165";
    public static String FA_CHAT = "\uF086";
    public static String FA_COPY = "\uF0C5";
    public static String FA_ATTACHMENT = "\uF0C6";
    public static String FA_EDIT = "\uF044";
    public static String FA_DELETE = "\uF014";
    public static String FA_FLIP_TO_INVOICE = "\uf0fe";

    public static String FA_DECLINE = "\uF00D";
    public static String FA_SEND_MSG = "\uF0E0";
    public static String FA_NOTIFICATION = "\uF0F3";
    public static String FA_PURCHASE_ORDERS = "\uF0F6";
    public static String FA_INVOICES = "\uF0C5";
    public static String FA_PAYMENT_INSTRUCTIONS = "\uF0CA";
    public static String FA_PAYMENTS = "\uF0D6";
    public static String FA_HOME = "\uF015";
    public static String FA_LOGOUT = "\uF08B";
    public static String FA_INFO = "\uF05A";
    public static String FA_DELETE_ITEM = "\uF1F8";
    public static String FA_DOWNLOAD_ITEM = "\uF019";
    public static String FA_VIEW = "\uf06e";
    public static String FA_ADD = "\uf067";
    public static String FA_NUMBER_DOWN = "\uf160";
    public static String FA_NUMBER_UP = "\uf161";
    public static String FA_CLIP_TO_INVOICE = "\uf0fe";
    public static String FA_CHANGE_DUE_DATE = "\uf073";
    public static String FA_ENVELOPE = "\uf0e0";


    /**
     * Get FontAwesome font/type face.
     *
     * @param context = current context.
     * @return FontAwesome font.
     */
    public static Typeface getFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), FONT_NAME);
    }


    /**
     * Sets the icon with the unicode character supported by FontAwesome
     *
     * @param context     = current context.
     * @param unicode     = unicode for the icon.
     * @param uiComponent = UI component (like TextView, Button, etc) to which the icon is to be
     *                    set.
     */
    public static void setIcon(Context context, String unicode, Object uiComponent) {

        Typeface fontAwesome = getFont(context);
        if (uiComponent instanceof TextView) {
            TextView textView = (TextView) uiComponent;
            textView.setTypeface(fontAwesome);
            textView.setText(unicode);

        } else if (uiComponent instanceof Button) {
            Button button = (Button) uiComponent;
            button.setTypeface(fontAwesome);
            button.setText(unicode);
        }
    }

    public static void setIconWithText(Context context, String unicode, Object uiComponent, String text) {

        Typeface fontAwesome = getFont(context);
        if (uiComponent instanceof RadioButton) {
            RadioButton radiobutton = (RadioButton) uiComponent;
            radiobutton.setTypeface(fontAwesome);
            radiobutton.setText(text + " " + unicode);
        }
    }

}
