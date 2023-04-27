package com.mymusic.reflexionai_assignment.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.mymusic.reflexionai_assignment.R

class PrograssBar( context: Context) {
    private val builder = AlertDialog.Builder(context)
    @SuppressLint("InflateParams")
    private val view = LayoutInflater.from(context).inflate(R.layout.custome_progress_bar, null)
    private val messageTextView = view.findViewById<TextView>(R.id.txtError)
    private lateinit var dialog : AlertDialog


    fun loading(massage: String) {
        messageTextView.text = massage
        builder.setView(view)
        builder.setCancelable(false)
        dialog = builder.create()
    }

    fun showLoder() {
        dialog.show()
    }

    fun hideLoder() {
        dialog.dismiss()
    }

}