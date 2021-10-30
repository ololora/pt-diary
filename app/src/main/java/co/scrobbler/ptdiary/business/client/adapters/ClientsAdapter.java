package co.scrobbler.ptdiary.business.client.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.business.client.Client;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {
    private Client[] clients;

    public ClientsAdapter(Client[] clients) {
        this.clients = clients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getClientNameTextView().setText(clients[position].name);
    }

    @Override
    public int getItemCount() {
        return clients.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView clientNameTextView;

        public ViewHolder(View view) {
            super(view);
            clientNameTextView = view.findViewById(R.id.client_name);
        }

        public TextView getClientNameTextView() {
            return clientNameTextView;
        }
    }
}
