package org.geeksforgeeks.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.TextView
import java.io.ByteArrayOutputStream
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mButton = findViewById<Button>(R.id.button_1)
        val mTextView = findViewById<TextView>(R.id.text_view_1)

        mButton.setOnClickListener {
            val mByteArrayOutputStream = ByteArrayOutputStream()
            val mBitmap = getBitmapFromVectorDrawable(this, R.drawable.android)
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, mByteArrayOutputStream)
            val mImageBytes = mByteArrayOutputStream.toByteArray()
            val mImageString = Base64.encodeToString(mImageBytes, Base64.DEFAULT)

            mTextView.text = mImageString
        }
    }

    private fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawableId)
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

}