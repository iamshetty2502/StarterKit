package com.app.utils;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.app.App;
import com.app.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static Pattern pattern;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean validateEmail(final String hex) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();

    }

    public static void phoneNumberValidator(EditText phoneNumber) {
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phone = s.toString();
                if (phone.startsWith("+")) {
                    if (phone.length() > 13) {
                        phoneNumber.setText(phone.substring(0, 13));
                        phoneNumber.setSelection(phoneNumber.getText().length());
                    } else {
                        if (phone.length() > 3) {
                            if (!phone.startsWith("+919") && !phone.startsWith("+918") && !phone.startsWith("+917")) {
                                phoneNumber.setError("Mobile number not valid");
                            }
                        }
                    }
                } else if (phone.startsWith("0")) {
                    if (phone.length() > 1) {
                        if (phone.startsWith("09") || phone.startsWith("08") || phone.startsWith("07")) {
                            if (phone.length() > 11) {
                                phoneNumber.setText(phone.substring(0, 11));
                                phoneNumber.setSelection(phoneNumber.getText().length());
                            }
                        } else {
                            if (phone.length() > 11) {
                                phoneNumber.setText(phone.substring(0, 11));
                                phoneNumber.setSelection(phoneNumber.getText().length());
                            }
                            phoneNumber.setError("Mobile number not valid");
                        }
                    }
                } else if (phone.startsWith("9") || phone.startsWith("8") || phone.startsWith("7")) {
                    if (phone.length() > 10) {
                        phoneNumber.setText(phone.substring(0, 10));
                        phoneNumber.setSelection(phoneNumber.getText().length());
                    }
                } else {
                    if (phone.length() > 10) {
                        phoneNumber.setText(phone.substring(0, 10));
                        phoneNumber.setSelection(phoneNumber.getText().length());
                    }
                    phoneNumber.setError("Mobile number not valid");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static void PasswordCheck(EditText password, EditText confirmpassword, final int passwordLength) {
        confirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!password.getText().toString().equals(confirmpassword.getText().toString())) {
                    confirmpassword.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.red));
                } else {
                    confirmpassword.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.black));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!password.getText().toString().equals(s.toString())) {
                    confirmpassword.setError("Password is not matching");
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < passwordLength) {
                    password.setError("Password length should be greater then 8");
                }
            }
        });
    }

    public static boolean isValidPhoneNumber(String phone) {
        if (phone.startsWith("+")) {
            if (phone.length() > 13) {
                return false;
            } else if (phone.length() == 13) {
                if (!phone.startsWith("+919") && !phone.startsWith("+918") && !phone.startsWith("+917")) {
                    return false;
                }
                return true;
            }
            return false;
        } else if (phone.startsWith("0")) {
            if (phone.length() > 11) {
                return false;
            } else if (phone.length() == 11) {
                if (!phone.startsWith("09") && !phone.startsWith("08") && !phone.startsWith("07")) {
                    return false;
                }
                return true;
            }
            return false;
        } else if (phone.startsWith("9") || phone.startsWith("8") || phone.startsWith("7")) {
            if (phone.length() > 10 || phone.length() < 10) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static void textCapsValidator(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.length() > 0) {
                    if (Character.isDigit(s.charAt(0))) {
                        editText.setError("Name cannot start with numbers.");
                    } else {
                        if (text.startsWith(" ")) {
                            editText.setText(text.trim());
                            editText.setSelection(editText.getText().length());
                        }
                        if (text.length() == 1 && !text.equals(text.toUpperCase())) {
                            editText.setText(text.toUpperCase());
                            editText.setSelection(editText.getText().length());
                        }
                        if (text.length() > 1) {
                            if (s.charAt(s.length() - 1) == s.charAt(s.length() - 2) && Character.isWhitespace(s.charAt(s.length() - 2))) {
                                String temp = text.substring(0, text.length() - 1);
                                editText.setText(temp);
                                editText.setSelection(temp.length());
                            }
                            if (Character.isWhitespace(s.charAt(s.length() - 2)) && !Character.isWhitespace(s.charAt(s.length() - 1))) {
                                String temp = s.charAt(s.length() - 1) + "";
                                if (!temp.equals(temp.toUpperCase())) {
                                    editText.setText(text.substring(0, text.length() - 1) + temp.toUpperCase());
                                    LogUtils.LogError("UP TEXT " + editText.getText());
                                    editText.setSelection(editText.getText().length());
                                }
                            }
                        }
                        if (text.contains(".")) {
                            editText.setText(text.replace(".", ""));
                            editText.setSelection(editText.getText().length());

                        }
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
