package net.maiatoday.hellofirebase;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Firebase myFirebaseRef;
    private EditText mText;
    private TextView mTextView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Firebase.setAndroidContext(getActivity());
        myFirebaseRef = new Firebase(BuildConfig.FIREBASE_URL);
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mTextView = (TextView) v.findViewById(R.id.textView);
        mText = (EditText) v.findViewById(R.id.editText);
        Button b = (Button) v.findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = mText.getText().toString();
                myFirebaseRef.child("message").setValue(s);
            }
        });
        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);
                mTextView.setText(s);
            }

            @Override public void onCancelled(FirebaseError error) { }

        });

        return v;
    }
}
