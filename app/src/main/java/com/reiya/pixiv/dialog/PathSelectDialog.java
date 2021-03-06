package com.reiya.pixiv.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reiya.pixiv.adapter.PathAdapter;

import java.io.File;

import tech.yojigen.pivisionm.R;

/**
 * Created by Administrator on 2015/12/31 0031.
 */
public class PathSelectDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.dialog_path, null);
        String oldPath = Environment.getExternalStorageDirectory() + PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("path", "/Pictures/PivisionM/");
        File path = new File(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(getString(R.string.key_path), oldPath));
        final EditText editText = view.findViewById(R.id.editText);
        editText.setText(path.getPath());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        final PathAdapter adapter = new PathAdapter(getActivity(), path, PathAdapter.getFolders(path));
        adapter.setListener(file -> editText.setText(file.getPath()));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> adapter.up());
        builder.setTitle(getString(R.string.path_to_save_pic))
                .setView(view)
                .setPositiveButton(getString(R.string.positive), (dialog, which) -> {
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                    editor.putString(getString(R.string.key_path), editText.getText().toString());
                    editor.apply();
                })
                .setNegativeButton(R.string.negative, null);
        return builder.create();
    }
}
