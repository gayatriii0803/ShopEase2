package com.example.shopease2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var roleGroup: RadioGroup
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var tvForgotPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        roleGroup = findViewById(R.id.roleGroup)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister) // Corrected ID
        tvForgotPassword = findViewById(R.id.tvForgotPassword) // Corrected ID

        btnLogin.setOnClickListener { loginUser() }
        tvRegister.setOnClickListener { goToRegisterScreen() }
        tvForgotPassword.setOnClickListener { goToForgotPasswordScreen() }
    }

    private fun getSelectedRole(): String {
        return if (findViewById<RadioButton>(R.id.rbCustomer).isChecked) "customer" else "retailer"
    }

    private fun loginUser() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val role = getSelectedRole()
        Toast.makeText(this, "Logged in as $role", Toast.LENGTH_SHORT).show()
        goToDashboard(role)
    }

    private fun goToRegisterScreen() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun goToForgotPasswordScreen() {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    private fun goToDashboard(role: String) {
        val intent = if (role == "customer") {
            Intent(this, CustomerDashboardActivity::class.java)
        } else {
            Intent(this, RetailerDashboardActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}