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
        val list = listOf(
                "Displacement",
                "Velocity",
                "Acceleration"
        )
        val toggle = findViewById<Button>(R.id.toggle)
        val cutoff = findViewById<EditText>(R.id.cutoffFrequency)
        val spinner = findViewById<Spinner>(R.id.graph_spinner)
        val graph = findViewById<GraphView>(R.id.graph)
        val series = LineGraphSeries<DataPoint>()
        val series2 = LineGraphSeries<DataPoint>()
        val series3 = LineGraphSeries<DataPoint>()

        val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        //Series Displacement
        series.isDrawDataPoints = true
        series.setOnDataPointTapListener { series, dataPoint ->
            Toast.makeText(this@Upload, "X = " + dataPoint.x + " Y = " + dataPoint.y, Toast.LENGTH_SHORT).show()
        }
        var x: Double = 0.0
        var y: Double = 0.0
        for (i in 0..50) {
            x += 1
            y = Math.sin(x)
            series.appendData(DataPoint(x, y), true, 1000)
        }

        //Series Velocity
        series2.isDrawDataPoints = true
        series2.setOnDataPointTapListener { series, dataPoint ->
            Toast.makeText(this@Upload, "X = " + dataPoint.x + " Y = " + dataPoint.y, Toast.LENGTH_SHORT).show()
        }
        var o: Double = 0.0
        var p: Double = 0.0
        for (i in 0..50) {
            o += 1
            p = Math.cos(o)
            series2.appendData(DataPoint(o, p), true, 1000)
        }

        //Series Acceleration
        series3.isDrawDataPoints = true
        series3.setOnDataPointTapListener { series, dataPoint ->
            Toast.makeText(this@Upload, "X = " + dataPoint.x + " Y = " + dataPoint.y, Toast.LENGTH_SHORT).show()
        }
        var a: Double = 0.0
        var b: Double = 0.0
        for (i in 0..50) {
            a += 1
            b = -Math.sin(a)
            series3.appendData(DataPoint(a, b), true, 1000)
        }

        graph.gridLabelRenderer.gridColor = Color.WHITE
        graph.gridLabelRenderer.verticalLabelsColor = Color.WHITE
        graph.gridLabelRenderer.horizontalLabelsColor = Color.WHITE
        graph.addSeries(series)
        graph.viewport.isScalable = true
        graph.viewport.isScrollable = true
        graph.gridLabelRenderer.reloadStyles()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                graph.removeSeries(series)
                if (list[position] == "Velocity") {
                    graph.removeSeries(series3)
                    graph.addSeries(series2)

                } else if (list[position] == "Acceleration") {
                    graph.removeSeries(series2)
                    graph.addSeries(series3)

                } else {
                    graph.removeSeries(series2)
                    graph.removeSeries(series3)
                    graph.addSeries(series)

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
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