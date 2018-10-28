package edu.univdhaka.cse.cse2216.mycards.java.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.univdhaka.cse.cse2216.mycards.R;
import edu.univdhaka.cse.cse2216.mycards.java.domain.Card;

public class CardListActivity extends Activity {

    private List<Card> cards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_list);

        retrieveCards();

        prepareListView();
    }

    private void retrieveCards() {
        // TODO: fetch card list from database/server
        cards.add(new Card("Visa", "Standard Chartered Bank Ltd.", "1234 5678 9101 1123", "SHARAFAT MOSHARRAF", "12/21", 123));
        cards.add(new Card("Master", "Eastern Bank Ltd.", "1092 1234 4485 2342", "SHARAFAT MOSHARRAF", "01/20", 456));
        cards.add(new Card("Discover", "Eastern Bank Ltd.", "4608 123456 7890", "SHARAFAT MOSHARRAF", "08/19", 789));
        cards.add(new Card("American Express", "City Bank Ltd.", "1234 567890 3942", "SHARAFAT MOSHARRAF", "12/18", 101));
    }

    private void prepareListView() {
        RecyclerView cardsRecyclerView = findViewById(R.id.card_list);
        cardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardsRecyclerView.setAdapter(new CardListAdapter(cards));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(this, AddEditCardActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class CardListAdapter extends RecyclerView.Adapter<CardListItemViewHolder> {

        private List<Card> cards;

        CardListAdapter(List<Card> cards) {
            this.cards = cards;
        }

        @Override
        public int getItemCount() {
            return cards.size();
        }

        @NonNull
        @Override
        public CardListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CardListActivity.this).inflate(R.layout.row_card_list, parent, false);

            return new CardListItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CardListItemViewHolder holder, int position) {
            final Card card = cards.get(position);

            holder.cardNumber.setText(card.getNumber());
            holder.cardType.setText(card.getType());
            holder.cardTypeLogo.setImageResource(getLogoResource(card.getType()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CardListActivity.this, CardDetailsActivity.class).putExtra("card", card));
                }
            });
        }

        private int getLogoResource(String type) {
            switch (type) {
                case "Visa": return R.mipmap.ic_visa;
                case "Master": return R.mipmap.ic_master;
                case "Discover": return R.mipmap.ic_discover;
                case "American Express": return R.mipmap.ic_amex;
                default: return R.mipmap.ic_launcher;
            }
        }
    }


    private class CardListItemViewHolder extends RecyclerView.ViewHolder {

        private TextView cardNumber;
        private TextView cardType;
        private ImageView cardTypeLogo;

        CardListItemViewHolder(@NonNull View view) {
            super(view);

            cardNumber = view.findViewById(R.id.card_number);
            cardType = view.findViewById(R.id.card_type);
            cardTypeLogo = view.findViewById(R.id.card_type_logo);
        }
    }
}
