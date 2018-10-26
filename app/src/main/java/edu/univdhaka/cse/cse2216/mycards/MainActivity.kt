package edu.univdhaka.cse.cse2216.mycards

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.cards_row.view.*

class MainActivity : Activity() {

    lateinit var listsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cards: ArrayList<Card> = ArrayList();
        cards.add(Card("1234 5678 9101 1123", "VISA"))
        cards.add(Card("1092 1234 4485 2342", "Master"))
        cards.add(Card("4608 123456 7890", "DCI"))

        listsRecyclerView = findViewById(R.id.card_list)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter = CardListAdapter(cards)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                startActivity(Intent(this, AddCardActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    inner class CardListAdapter(private val cards : ArrayList<Card>) : RecyclerView.Adapter<CardListItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.cards_row, parent, false)

            return CardListItemViewHolder(view)
        }

        override fun getItemCount(): Int {
            return cards.size
        }

        override fun onBindViewHolder(holder: CardListItemViewHolder, position: Int) {
            val card = cards[position]

            holder.cardNumber.text = card.number
            holder.cardType.text = card.type

            holder.itemView.setOnClickListener {
                startActivity(Intent(applicationContext, CardDetailsActivity::class.java).putExtra("card", card))
            }
        }
    }


    class CardListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardNumber = view.card_number
        val cardType = view.card_type
    }
}
