package com.example.shopease2
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterActivity : AppCompatActivity() {
    private lateinit var btnRegister: Button
    private lateinit var login: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var email: TextView
    private lateinit var password: TextView
    private lateinit var confirmpass: TextView
    private lateinit var username: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        btnRegister = findViewById(R.id.btnRegister)
        login = findViewById(R.id.login)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmpass = findViewById(R.id.confirmpass)
        username=findViewById(R.id.username)
        auth = Firebase.auth
        login.setOnClickListener {

//            onBackPressedDispatcher.onBackPressed()
            val intent = Intent(this,AuthActivity::class.java)
            startActivity(intent)

        }

        val etPassword = findViewById<EditText>(R.id.password)
        val ivTogglePassword = findViewById<ImageView>(R.id.passwordVisibilityToggle)
        val conPassword = findViewById<EditText>(R.id.confirmpass)
        val passwordVisibilityToggle1 = findViewById<ImageView>(R.id.passwordVisibilityToggle1)

        val visibilityMap = mutableMapOf<EditText, Boolean>(
            etPassword to false,
            conPassword to false
        )

        fun togglePasswordVisibility(editText: EditText, toggleIcon: ImageView) {
            val isVisible = !(visibilityMap[editText] ?: false)
            visibilityMap[editText] = isVisible

            editText.inputType = if (isVisible) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            toggleIcon.setImageResource(
                if (isVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
            )

            editText.setSelection(editText.text.length)
        }

// Set click listeners
        ivTogglePassword.setOnClickListener {
            togglePasswordVisibility(etPassword, ivTogglePassword)
        }

        passwordVisibilityToggle1.setOnClickListener {
            togglePasswordVisibility(conPassword, passwordVisibilityToggle1)
        }


        btnRegister.setOnClickListener {
            val emailInput = email.text.toString().trim()
            val passwordInput = password.text.toString().trim()
            val confirmPasswordInput = confirmpass.text.toString().trim()
            val nameInput = username.text.toString().trim()

            if (emailInput.isEmpty() || passwordInput.isEmpty() || confirmPasswordInput.isEmpty()||nameInput.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (passwordInput.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passwordInput != confirmPasswordInput) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(emailInput, passwordInput)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("user_name", nameInput)
                        editor.putString("user_email", emailInput)
                        editor.apply()
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()

                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed: ${task.exception?.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}