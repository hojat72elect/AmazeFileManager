

package com.amaze.filemanager.ui.views.preference

import android.content.Context
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.amaze.filemanager.R

/** @author Emmanuel on 17/4/2017, at 22:22.
 */
class PathSwitchPreference(
    context: Context,
    private val onEdit: (PathSwitchPreference) -> Unit,
    private val onDelete: (PathSwitchPreference) -> Unit,
) : Preference(context) {
    var lastItemClicked = -1
        private set

    init {
        widgetLayoutResource = R.layout.namepathswitch_preference
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        holder.itemView.let { view ->
            view.findViewById<View>(R.id.edit).setOnClickListener { onEdit(this) }
            view.findViewById<View>(R.id.delete).setOnClickListener { onDelete(this) }
            view.setOnClickListener(null)
        }

        // Keep this before things that need changing what's on screen
        super.onBindViewHolder(holder)
    }
}
