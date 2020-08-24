package br.com.etecmatao.forcadevendas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.etecmatao.forcadevendas.util.TextInputEditTextUtil.Companion.validEmailFilled
import br.com.etecmatao.forcadevendas.util.TextInputEditTextUtil.Companion.validFilled
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
    }

    fun signIn(v: View) {
        val valid = txtUsername.validEmailFilled() && txtPassword.validFilled()

        if (!valid) return

        auth.signInWithEmailAndPassword(
            txtUsername.text.toString(), txtPassword.text.toString()
        ).addOnCompleteListener {
            it.result?.let { res ->
                if (res.user != null) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    return@let
                }
            }

            Toast.makeText(
                this, getString(R.string.msg_invalid_credential), Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun signUp(v: View) {
        if (!(txtUsername.validEmailFilled() && txtPassword.validFilled())){
            return
        }

        auth.createUserWithEmailAndPassword(
            txtUsername.text.toString(), txtPassword.text.toString()
        ).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(
                    this,
                    getString(R.string.msg_user_created),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
