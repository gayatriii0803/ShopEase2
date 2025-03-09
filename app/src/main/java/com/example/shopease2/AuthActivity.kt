package com.example.shopease2



import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.FirebaseDatabase


class AuthActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var roleGroup: RadioGroup
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

//    private val auth = FirebaseAuth.getInstance()
//    private val dbRef = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        roleGroup = findViewById(R.id.roleGroup)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener { registerUser() }
        btnLogin.setOnClickListener { loginUser() }
    }

    private fun getSelectedRole(): String {
        return if (findViewById<RadioButton>(R.id.rbCustomer).isChecked) "customer" else "retailer"
    }

    private fun registerUser() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val role = getSelectedRole()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
            return
        }

//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnSuccessListener {
//                val uid = it.user!!.uid
//                val userMap = mapOf("uid" to uid, "email" to email, "role" to role)
//                dbRef.child("users").child(uid).setValue(userMap)
//                Toast.makeText(this, "Registered as $role", Toast.LENGTH_SHORT).show()
//                goToDashboard(role)
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
//            }
    }

    private fun loginUser() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
            return
        }

//        auth.signInWithEmailAndPassword(email, password)
//            .addOnSuccessListener {
//                val uid = it.user!!.uid
//                dbRef.child("users").child(uid).get().addOnSuccessListener { snapshot ->
//                    val role = snapshot.child("role").value.toString()
//                    goToDashboard(role)
//                }
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Login Failed: ${it.message}", Toast.LENGTH_SHORT).show()
//            }
    }

    private fun goToDashboard(role: String) {
        val intent = if (role == "customer") {
            Intent(this,RetailerDashboardActivity::class.java)
        } else {
            Intent(this, RetailerDashboardActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
