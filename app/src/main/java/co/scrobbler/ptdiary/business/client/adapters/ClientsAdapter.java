package co.scrobbler.ptdiary.business.client.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.business.client.Client;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {
    private List<Client> clients = new ArrayList<>();

    public void setClients(List<Client> clients) {
        this.clients = clients;
        notifyDataSetChanged();
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
        holder.getClientNameTextView().setText(clients.get(position).name);
    }

    @Override
    public int getItemCount() {
        return clients.size();
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
