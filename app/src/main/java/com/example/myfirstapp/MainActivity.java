package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private static final String TAG = "MainActivity";

    private Button mRequestDownloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // アプリ全体に適用
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);   // 特定のAcitivityのみ適用

//        View view = getWindow().getDecorView();
//        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        View view = new View();
//        view.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                                                != PackageManager.PERMISSION_GRANTED){
//        }

//        mRequestDownloadButton = findViewById(R.id.button_request);
//        mRequestDownloadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String familyName = autoCompleteFamilyName.getText().toString();
//                if (!isValidFamilyName(familyName)) {
//                    familyNameInput.setErrorEnabled(true);
//                    familyNameInput.setError(getString(R.string.invalid_family_name));
//                    Toast.makeText(
//                            MainActivity.this,
//                            R.string.invalid_input,
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                requestDownload(familyName);
//                mRequestDownloadButton.setEnabled(false);
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClipboardManager clipboard =(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("onCreate text", "Hello, onCreate!");
        clipboard.setPrimaryClip(clip);

    }

    private void requestDownload(String familyName) {
//        QueryBuilder queryBuilder = new QueryBuilder(familyName)
//                .withWidth(progressToWidth(mWidthSeekBar.getProgress()))
//                .withWeight(progressToWeight(mWeightSeekBar.getProgress()))
//                .withItalic(progressToItalic(mItalicSeekBar.getProgress()))
//                .withBestEffort(mBestEffort.isChecked());
//        String query = queryBuilder.build();
        String query = "Noto Sans&weight=400&width=100.0&italic=0.0&besteffort=true";

        Log.d(TAG, "Requesting a font. Query: " + query);
//        FontRequest request = new FontRequest(
//                "com.google.android.gms.fonts",
//                "com.google.android.gms",
//                query,
//                R.array.com_google_android_gms_fonts_certs);

//        final ProgressBar progressBar = findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.VISIBLE);

        FontsContractCompat.FontRequestCallback callback = new FontsContractCompat
                .FontRequestCallback() {
            @Override
            public void onTypefaceRetrieved(Typeface typeface) {
//                mDownloadableFontTextView.setTypeface(typeface);
//                progressBar.setVisibility(View.GONE);
                mRequestDownloadButton.setEnabled(true);
            }

            @Override
            public void onTypefaceRequestFailed(int reason) {
//                Toast.makeText(MainActivity.this,
//                        getString(R.string.request_failed, reason), Toast.LENGTH_LONG)
//                        .show();
//                Toast.makeText(MainActivity.this,
//                        getString(R.string.request_failed, reason), Toast.LENGTH_LONG)
//                        .show();
//                progressBar.setVisibility(View.GONE);
                mRequestDownloadButton.setEnabled(true);
            }
        };
//        FontsContractCompat
//                .requestFont(MainActivity.this, request, callback,
//                        getHandlerThreadHandler());
    }



    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void viewACTION_WIRELESS_SETTINGS(View view) {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        startActivity(intent);
    }

    public void viewACTION_WIFI_IP_SETTINGS(View view) {
//        Intent intent = new Intent(Settings.ACTION_WIFI_IP_SETTINGS);
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }

    public void viewACTION_WIFI_SETTINGS(View view) {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
    }

    public void viewSettingPanel(View view) {
        Intent intent = new Intent(Settings.Panel.ACTION_WIFI);
        startActivityForResult(intent, 1);
    }

    public void darkthema(View view) {
        Log.d("BuildConfig.DEBUG","darkmode");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void onclickCopyBtn(View view) {
        //紹介文をコピーする
        ClipboardManager clipboard =(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", "Hello, World!");
        clipboard.setPrimaryClip(clip);

        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

//        // Show Dialog
//        dialog = AlertDialogFragment.newInstance(
//                "",
//                getString(R.string.dlg_msg_copied),
//                getString(R.string.dlg_ok),
//                "", null)
//        dialog.show(fragmentManager!!, "dialog");
    }

}
