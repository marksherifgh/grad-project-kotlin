package com.example.graduation_project_kotlin

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.*

class Upload : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        val toggle = findViewById<Button>(R.id.toggle)
        val cutoff = findViewById<EditText>(R.id.cutoffFrequency)
        val spinner = findViewById<Spinner>(R.id.graph_spinner)
        val graph = findViewById<GraphView>(R.id.graph)
        var series = LineGraphSeries<DataPoint>()
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.graph_spinner, R.layout.spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        series.isDrawDataPoints = true
        series.setOnDataPointTapListener { series, dataPoint ->
            Toast.makeText(this@Upload, "X = " + dataPoint.x + " Y = " + dataPoint.y, Toast.LENGTH_SHORT).show()
        }
        var x: Double = 0.0
        var y: Double = 0.0
        for (i in 0..50) {
            x += 1
            y = Math.sin(x)
            series.appendData(DataPoint(x, y), true, 200)
        }

        graph.gridLabelRenderer.gridColor = Color.WHITE
        graph.gridLabelRenderer.verticalLabelsColor = Color.WHITE
        graph.gridLabelRenderer.horizontalLabelsColor = Color.WHITE
        graph.gridLabelRenderer.reloadStyles()
        graph.addSeries(series)
        graph.viewport.isScalable = true

        toggle.setOnClickListener {
            if (toggle.text === "Frequency domain") {
                toggle.text = "Time domain"
                cutoff.visibility = View.VISIBLE
            } else {
                toggle.text = "Frequency domain"
                cutoff.visibility = View.INVISIBLE
            }
        }


    }
}