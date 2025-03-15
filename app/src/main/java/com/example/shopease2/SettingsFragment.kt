package com.example.shopease2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SettingsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SettingsAdapter
    private val settingsList = listOf(
        SettingsItem(R.drawable.ic_alerts, "Alerts"),
        SettingsItem(R.drawable.ic_edit, "Edit Profile"),
        SettingsItem(R.drawable.ic_bank, "Bank Data"),
        SettingsItem(R.drawable.ic_share, "Share"),
        SettingsItem(R.drawable.ic_contacts, "Contacts"),
        SettingsItem(R.drawable.ic_privacy, "Privacy"),
        SettingsItem(R.drawable.ic_security, "Two-Factor Authentication"),
        SettingsItem(R.drawable.ic_theme, "Theme"),
        SettingsItem(R.drawable.ic_logout, "Log Out")
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        recyclerView = view.findViewById(R.id.settingsRecyclerView)
        adapter = SettingsAdapter(settingsList)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        return view
    }
}