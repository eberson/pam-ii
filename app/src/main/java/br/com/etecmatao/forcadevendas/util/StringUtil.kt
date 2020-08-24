package br.com.etecmatao.forcadevendas.util

import android.text.TextUtils

class StringUtil {
    companion object {
        fun String.isEmailValid(): Boolean {
            if (TextUtils.isEmpty(this)){
                return false
            }

            return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches();
        }

        fun String.isPasswordValid(): Boolean {
            if (TextUtils.isEmpty(this)){
                return false;
            }

            return this.trim().length >= 6;
        }
    }
}