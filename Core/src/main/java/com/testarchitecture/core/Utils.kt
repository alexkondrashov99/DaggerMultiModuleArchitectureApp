package com.testarchitecture.core

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

object Utils {
    fun showPopup(anchorView: View, text: String) {

        val popupView = LayoutInflater.from(anchorView.context).inflate(com.testarchitecture.core.R.layout.popup_layout, null)
        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        // Налаштовуємо текст у попапі
        val textView = popupView.findViewById<TextView>(com.testarchitecture.core.R.id.popup_text_view)
        textView.text = text

        // Вказуємо, що попап можна закрити при кліку поза його межами
        popupWindow.isFocusable = true
        popupWindow.isOutsideTouchable = true

        // Показуємо попап над view
        popupWindow.showAsDropDown(anchorView, 0, 0, Gravity.START)
    }
}