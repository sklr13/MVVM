package com.example.home

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.domain_entity.RepositoryModel
import com.example.ui.AdapterDelegate

class RepositoryItemDelegate : AdapterDelegate<List<RepositoryModel>>() {
    override fun isForViewType(items: List<RepositoryModel>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: List<RepositoryModel>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as RepositoryViewHolder).bind(items[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return RepositoryViewHolder(createView(parent, R.layout.repo_item))
    }

    private class RepositoryViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(repoModel: RepositoryModel) {
            with(itemView) { findViewById<TextView>(R.id.repos_name) }.text = repoModel.name
        }
    }
}
