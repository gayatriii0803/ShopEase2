package com.example.shopease2

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(R.layout.fragement_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "User")

        val nameTextView = view.findViewById<TextView>(R.id.profileUsername)
        nameTextView.text = "$userName"
    }
}




