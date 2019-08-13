package com.sk.broadband.socketdrawviewclientsample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.SeekBar
import com.sk.broadband.socketdrawview.client.ClientSocketDrawView


class MainActivity : AppCompatActivity() {

    lateinit var clientSocketDrawView: ClientSocketDrawView
    lateinit var cancelButton: Button
    lateinit var eraseButton: Button
    lateinit var modeButton: Button

    lateinit var redBar: SeekBar
    lateinit var greenBar: SeekBar
    lateinit var blueBar: SeekBar
    lateinit var widthBar: SeekBar

    var red = 0
    var green = 0
    var blue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clientSocketDrawView = findViewById(R.id.draw_view)
        cancelButton = findViewById(R.id.cancel_button)
        eraseButton = findViewById(R.id.erase_button)
        modeButton = findViewById(R.id.mode_button)

        redBar = findViewById(R.id.red_bar)
        greenBar = findViewById(R.id.green_bar)
        blueBar = findViewById(R.id.blue_bar)
        widthBar = findViewById(R.id.width_bar)

        cancelButton.setOnClickListener { clientSocketDrawView.undoPrev() }
        eraseButton.setOnClickListener { clientSocketDrawView.eraseAll() }
        modeButton.setOnClickListener {
            clientSocketDrawView.isPath = !clientSocketDrawView.isPath
        }

        val defaultColor = clientSocketDrawView.pathColor
        red = defaultColor shr 16 and 0xff
        green = defaultColor shr 8 and 0xff
        blue = defaultColor and 0xff

        redBar.progress = red
        redBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // onProgress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // onStart
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                red = seekBar.progress
                setPathColor()
            }
        })

        greenBar.progress = green
        greenBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // onProgress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // onStart
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                green = seekBar.progress
                setPathColor()
            }
        })

        blueBar.progress = blue
        blueBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // onProgress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // onStart
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                blue = seekBar.progress
                setPathColor()
            }
        })

        widthBar.progress = clientSocketDrawView.pathStrokeWidth.toInt()
        widthBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // onProgress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // onStart
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                clientSocketDrawView.pathStrokeWidth = seekBar.progress.toFloat()
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        clientSocketDrawView.draw(event!!)
        clientSocketDrawView.drawServer(event)
        return super.onTouchEvent(event)
    }

    private fun setPathColor() {
        clientSocketDrawView.pathColor = Color.rgb(red, green, blue)
    }
}
