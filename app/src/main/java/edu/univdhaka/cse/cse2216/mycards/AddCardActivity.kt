package edu.univdhaka.cse.cse2216.mycards

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class AddCardActivity : Activity() {

    private lateinit var cardTypeInput: Spinner
    private lateinit var bankNameInput: EditText
    private lateinit var cardNumberInput: EditText
    private lateinit var cardholderNameInput: EditText
    private lateinit var expiryDateInput: EditText
    private lateinit var cvvInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(R.string.add_card)

        setContentView(R.layout.activity_add_card)

        bindWidgets()
    }

    private fun bindWidgets() {
        cardTypeInput = findViewById(R.id.card_type_input)
        bankNameInput = findViewById(R.id.bank_name_input)
        cardNumberInput = findViewById(R.id.card_number_input)
        cardholderNameInput = findViewById(R.id.cardholder_name_input)
        expiryDateInput = findViewById(R.id.expiry_date_input)
        cvvInput = findViewById(R.id.cvv_input)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add_card, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                onSave()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onSave() {
        if (validateInput()) {
            saveCard(getCardInstanceFromInput())
        }
    }

    private fun validateInput(): Boolean {
        var allInputsValid = true

        arrayOf(bankNameInput, cardNumberInput, cardholderNameInput, expiryDateInput, cvvInput)
                .forEach { input ->
                    if (input.text.isEmpty()) {
                        showError(input, R.string.required)
                        allInputsValid = false
                    }
                }

        return allInputsValid
    }

    private fun showError(field: EditText, messageRes: Int) {
        field.error = getString(messageRes)
    }

    private fun getCardInstanceFromInput(): Card {
        return Card(
                cardTypeInput.selectedItem.toString(),
                bankNameInput.text.toString(),
                cardNumberInput.text.toString(),
                cardholderNameInput.text.toString(),
                expiryDateInput.text.toString(),
                cvvInput.text.toString().toInt()
        )
    }

    private fun saveCard(card: Card) {
        Log.d("MyCards", "New card added: " + card.toString())

        // TODO: save card to database/server

        Toast.makeText(this, R.string.card_add_success, Toast.LENGTH_SHORT).show()

        finish()
    }
}
