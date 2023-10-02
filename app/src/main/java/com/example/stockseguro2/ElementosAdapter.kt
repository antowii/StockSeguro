package com.example.stockseguro2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ElementosAdapter(private var elementos: List<Elementos>) : RecyclerView.Adapter<ElementosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position >= 0 && position < elementos.size) {
            val elemento = elementos[position]
            holder.bind(elemento)
        }
    }
    override fun getItemCount(): Int {
        return elementos.size
    }
    fun updateData(newData: List<Elementos>) {
        elementos = newData
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val elementoTextView: TextView = itemView.findViewById(R.id.txtNombre)
        private val descripcionTextView: TextView = itemView.findViewById(R.id.txtDescripcion)
        private val fotoImageView: ImageView = itemView.findViewById(R.id.imageviewFoto)
        fun bind(elemento: Elementos) {
            // Configurar los elementos de la vista usando el elemento y el diseño card_layout.xml
            elementoTextView.text = elemento.nombre
            descripcionTextView.text = elemento.descripcion
            // Configurar la imagen en card_layout.xml
            fotoImageView.setImageResource(elemento.imagen)
        }
    }
}