package com.davidbargados.conecta4

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TableLayout
import androidx.activity.ComponentActivity
import android.widget.Button

class MainActivity : ComponentActivity() {

    val tablero = Array(6) { Array(7) { "." } }
    private var turn = 0;
    private lateinit var cells: Array<Array<ImageView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCol1: Button = findViewById(R.id.buttonCol1)
        val buttonCol2: Button = findViewById(R.id.buttonCol2)
        val buttonCol3: Button = findViewById(R.id.buttonCol3)
        val buttonCol4: Button = findViewById(R.id.buttonCol4)
        val buttonCol5: Button = findViewById(R.id.buttonCol5)
        val buttonCol6: Button = findViewById(R.id.buttonCol6)
        val buttonCol7: Button = findViewById(R.id.buttonCol7)

        buttonCol1.setOnClickListener { ColocarFitxa(0) }
        buttonCol2.setOnClickListener { ColocarFitxa(1) }
        buttonCol3.setOnClickListener { ColocarFitxa(2) }
        buttonCol4.setOnClickListener { ColocarFitxa(3) }
        buttonCol5.setOnClickListener { ColocarFitxa(4) }
        buttonCol6.setOnClickListener { ColocarFitxa(5) }
        buttonCol7.setOnClickListener { ColocarFitxa(6) }

        crearMatriz()
    }

    private fun crearMatriz() {
        val tableLayout: TableLayout = findViewById(R.id.tableConecta4)
        tableLayout.setBackgroundColor(Color.BLUE);
        val rows = 6
        val cols = 7

        cells = Array(rows) { Array(cols) { ImageView(this) } }

        for (row in 0 until rows) {
            val tableRow = TableRow(this)
            for (col in 0 until cols) {
                val cell = ImageView(this)
                cell.setImageResource(R.drawable.ficha) // Ficha vacía inicial
                val layoutParams = TableRow.LayoutParams(0, 100)
                layoutParams.weight = 1f
                layoutParams.setMargins(2, 2, 2, 2) // Márgenes entre celdas
                cell.layoutParams = layoutParams
                cells[row][col] = cell
                tableRow.addView(cell)
            }
            tableLayout.addView(tableRow)
        }
    }

    private fun ColocarFitxa(columna: Int) {
        for (fila in tablero.size - 1 downTo 0) {
            if (tablero[fila][columna] == ".") {
                if (turn % 2 == 0) {
                    tablero[fila][columna] = "R"
                } else {
                    tablero[fila][columna] = "Y"
                }
                pintarFitxa(fila, columna)
                turn++
                break
            }
        }
    }

    private fun pintarFitxa(fila: Int, columna: Int) {
        val cell = cells[fila][columna]

        if (turn % 2 == 1) {
            cell.setImageResource(R.drawable.ficha_roja)
        } else {
            cell.setImageResource(R.drawable.ficha_amarilla)
        }
    }
}
