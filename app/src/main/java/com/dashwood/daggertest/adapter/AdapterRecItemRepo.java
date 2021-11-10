package com.dashwood.daggertest.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dashwood.daggertest.databinding.RecItemRepoBinding;
import com.dashwood.daggertest.model.Repo;
import com.dashwood.daggertest.trending.RepoDiffCallback;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecItemRepo extends RecyclerView.Adapter<AdapterRecItemRepo.ViewHolder> {

    private List<Repo> repos = new ArrayList<>();
    private final RepoClickedListener listener;

    public AdapterRecItemRepo(RepoClickedListener listener) {
        this.listener = listener;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecItemRepoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repo repo = repos.get(position);
        holder.binding.txtName.setText(repo.name());
        holder.binding.txtDescription.setText(repo.description());
        holder.binding.txtCount.setText(String.valueOf(repo.stargazersCount()));
        holder.binding.vgRoot.setOnClickListener(v -> listener.onRepoClicked(repo));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public void sendItems(List<Repo> list) {
        if (list == null) {
            repos.clear();
            notifyDataSetChanged();
            return;
        }
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RepoDiffCallback(repos, list));
        repos.clear();
        repos.addAll(list);
        diffResult.dispatchUpdatesTo(this);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private RecItemRepoBinding binding;

        public ViewHolder(@NonNull RecItemRepoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public interface RepoClickedListener {
        void onRepoClicked(Repo repo);
    }
}
