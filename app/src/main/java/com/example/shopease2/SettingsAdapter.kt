package com.example.shopease2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class SettingsAdapter(private val settingsList: List<SettingsItem>) :
    RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    class SettingsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.item_icon)
        val title: TextView = view.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_settings, parent, false)
        return SettingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = settingsList[position]
        holder.icon.setImageResource(item.iconRes)
        holder.title.text = item.title
    }

    override fun getItemCount() = settingsList.size
}
