package com.soumission.assistant.assistantsoumission;


import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Classe qui sert juste à changer le format de texte d'un champ pour nombre.
// L'utilisateur peut entrer qu'un seul point et 2 décimales
public class DecimalDigitsInputFilter implements InputFilter {
    Pattern mPattern;

    public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
        mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher = mPattern.matcher(dest);

        if(!matcher.matches()) return "";

        return null;
    }
}
