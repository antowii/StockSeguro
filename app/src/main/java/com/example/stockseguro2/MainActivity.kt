package com.example.stockseguro2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val categorias = arrayOf(
        "Despensa",
        "Carnes y Pescados",
        "Frutas y Verduras",
        "Frescos y Lácteos",
        "Limpieza y Aseo"
    )

    private val elementosPorCategoria = mapOf(
        "Despensa" to listOf(
            Elementos("Aceite", R.drawable.aceite,"Aceite de Oliva Extra Virgen Lider, 500ml"),
            Elementos("Fideos", R.drawable.fideos, "Pasta Al Huevo Seca Spirali Talliani, 400g"),
            Elementos("Harina", R.drawable.harina, "Harina Con Polvos de Hornear Lider, 1kg"),
            Elementos("Lentejas", R.drawable.lentejas, "Lentejas 4 Mm, 1kg"),
            Elementos("Mostaza", R.drawable.mostaza, "Mostaza Yellow Great Value, 397g")
        ),
        "Carnes y Pescados" to listOf(
            Elementos("Chorizo", R.drawable.chorizo, "Chorizo Parrillero Premium Receta Del Abuelo, 400g"),
            Elementos("Jurel", R.drawable.jurel, "Filetes de Jurel Congelados San José, 500g"),
            Elementos("Posta Paleta", R.drawable.postapaleta, "Posta Paleta Trozo, 700 gr"),
            Elementos("Reineta", R.drawable.reineta, "Filetes de Reineta Congelados Mar Verde, 500g"),
            Elementos("Salchichas", R.drawable.salchichas, "Salchichas Premium Receta Del Abuelo, 10 Unidades")
        ),
        "Frutas y Verduras" to listOf(
            Elementos("Lechuga", R.drawable.lechuga, "Lechuga Escarola Punto Azul, 1 Unidad"),
            Elementos("Naranjas", R.drawable.naranjas, "Naranjas Pangalillo, 1.5kg"),
            Elementos("Paltas", R.drawable.paltas, "Palta Hass Malla, 700 g"),
            Elementos("Papas", R.drawable.papas, "Papas Granel, 500 g (5 a 6 un aprox)"),
            Elementos("Plátanos", R.drawable.platanos, "Plátano Granel, 500 g (3 a 4 un aprox)")
        ),
        "Frescos y Lácteos" to listOf(
            Elementos("Leche", R.drawable.leche, "Leche Natural Entera Soprole, 1lt"),
            Elementos("Queso", R.drawable.queso, "Queso Laminado Gauda Soprole, 500g"),
            Elementos("Yoghurt", R.drawable.yoghurt, "Yoghurt Batido Gold Frutos Secos Tradicional Soprole, 165g"),
            Elementos("Huevos", R.drawable.huevos, "Huevos de Gallina Libre Grande Color Selección, 6 Unidades"),
            Elementos("Salame", R.drawable.salame, "Salame Artesanal Receta Del Abuelo, 100g")
        ),
        "Limpieza y Aseo" to listOf(
            Elementos("Cloro", R.drawable.cloro, "Cloro gel tradicional, 900 ml"),
            Elementos("Confort", R.drawable.confort, "Papel Higiénico Doble Hoja 25 mt, 40 Unidades"),
            Elementos("Suavizante", R.drawable.detergente, "Suavizante Fuzol, 1500 ml"),
            Elementos("Escoba", R.drawable.escoba, "Escobillón Piso Flotante, 1 Unidad"),
            Elementos("Lavaloza", R.drawable.lavaloza, "Lavaloza Limón Doypack, 1 Litro")
        )
    )


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ElementosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinnerCategorias)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = ElementosAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapterSpinner = ArrayAdapter(this, R.layout.spinner_item, categorias)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterSpinner

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val categoriaSeleccionada = categorias[position]
                val elementos = elementosPorCategoria[categoriaSeleccionada] ?: emptyList()
                adapter.updateData(elementos)
                recyclerView.visibility = if (elementos.isEmpty()) View.GONE else View.VISIBLE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                recyclerView.visibility = View.GONE
            }
        }
    }
}