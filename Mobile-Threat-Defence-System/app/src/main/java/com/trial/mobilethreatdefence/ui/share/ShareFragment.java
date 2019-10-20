package com.trial.mobilethreatdefence.ui.share;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.trial.mobilethreatdefence.R;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    Button contactButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        contactButton = (Button) root.findViewById(R.id.contact_us);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentContactUs=new Intent(Intent.ACTION_SEND);
                String[] recipients={"alphabeta@gamma.com"};
                intentContactUs.putExtra(Intent.EXTRA_EMAIL, recipients);
                intentContactUs.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
                intentContactUs.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
                intentContactUs.setType("text/html");
                intentContactUs.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intentContactUs, "Send mail"));
            }
        });

        return root;
    }
}