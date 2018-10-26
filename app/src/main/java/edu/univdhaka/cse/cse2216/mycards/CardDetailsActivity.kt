package edu.univdhaka.cse.cse2216.mycards

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class CardDetailsActivity: Activity() {

    private lateinit var card: Card

    private lateinit var cardTypeLabel: TextView
    private lateinit var bankNameLabel: TextView
    private lateinit var cardNumberLabel: TextView
    private lateinit var cardholderNameLabel: TextView
    private lateinit var expiryDateLabel: TextView
    private lateinit var cvvLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(R.string.card_details)

        setContentView(R.layout.activity_card_details)

        bindWidgets()

        card = intent.extras?.get("card") as Card

        bindValuesToLabels()
    }

    private fun bindWidgets() {
        cardTypeLabel = findViewById(R.id.card_type_label)
        bankNameLabel = findViewById(R.id.bank_name_label)
        cardNumberLabel = findViewById(R.id.card_number_label)
        cardholderNameLabel = findViewById(R.id.cardholder_name_label)
        expiryDateLabel = findViewById(R.id.expiry_date_label)
        cvvLabel = findViewById(R.id.cvv_label)
    }

    private fun bindValuesToLabels() {
        cardTypeLabel.text = card.type
        bankNameLabel.text = card.bankName
        cardNumberLabel.text = card.number
        cardholderNameLabel.text = card.cardholderName
        expiryDateLabel.text = card.expiryDate
        cvvLabel.text = card.cvv.toString()
    }
}
