package com.soumission.assistant.assistantsoumission;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

/**
 * Created by Joaquin on 2016-11-07.
 */

public class PostalAdressFilter implements InputFilter {
    private int maxLength;
    public PostalAdressFilter(int length) {
        maxLength = length;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        for (int i = start; i < end; i++) {
                if (!Character.isLetterOrDigit(source.charAt(i))) return "";
        }
        return null;
    }
}
