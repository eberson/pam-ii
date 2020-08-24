package br.com.etecmatao.forcadevendas.util

import android.content.res.Resources
import android.text.TextUtils
import br.com.etecmatao.forcadevendas.R
import br.com.etecmatao.forcadevendas.util.StringUtil.Companion.isEmailValid
import com.google.android.material.textfield.TextInputEditText

class TextInputEditTextUtil {
    companion object {
        fun TextInputEditText.validFilled(): Boolean {
            this.error = null

            if (!TextUtils.isEmpty(this.text.toString())){
                return true
            }

            this.error = Resources.getSystem().getString(R.string.msg_required_field)
            return false
        }

        fun TextInputEditText.validEmailFilled(): Boolean {
            if (!this.validFilled()){
                return false
            }

            if (this.text.toString().isEmailValid()){
                return true;
            }

            this.error = Resources.getSystem().getString(R.string.msg_invalid_email)
            return false;
        }
    }
}