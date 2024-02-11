package com.example.sae302applicationdevauxtristan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.ViewHolder> {
    private List<FileItem> mFileItems;
    private Context mContext;

    public FilesAdapter(Context context, List<FileItem> fileItems) {
        this.mFileItems = fileItems;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tcp_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FileItem fileItem = mFileItems.get(position);
        holder.fileNameView.setText(fileItem.getFileName());
        // Ici, vous pouvez ajouter plus de logique pour afficher d'autres détails, comme la taille du fichier.
    }

    @Override
    public int getItemCount() {
        return mFileItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fileNameView;

        public ViewHolder(View itemView) {
            super(itemView);
            fileNameView = itemView.findViewById(R.id.file_name);
        }
    }
}
