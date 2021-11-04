package co.scrobbler.ptdiary.ui.client.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.db.entity.Client;
import io.reactivex.rxjava3.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {
    private List<Client> clients = new ArrayList<>();
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    private final PublishSubject<Long> selectedItemId = PublishSubject.create();

    @SuppressLint("NotifyDataSetChanged")
    public void setClients(List<Client> clients) {
        this.clients = clients;
        notifyDataSetChanged();
    }

    public PublishSubject<Long> getSelectedItemId() {
        return selectedItemId;
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

        compositeSubscription.add(
                RxView.clicks(holder.itemView)
                        .subscribe(view -> selectedItemId.onNext(clients.get(position).id))
        );
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

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        compositeSubscription.unsubscribe();
        super.onDetachedFromRecyclerView(recyclerView);
    }
}
