package de.androidcrypto.canhubandroidcropperjava;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.canhub.cropper.CropImageContract;
import com.canhub.cropper.CropImageContractOptions;
import com.canhub.cropper.CropImageOptions;
import com.canhub.cropper.CropImageView;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<CropImageContractOptions> arl;
    CropImageView iv1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);
*/
        iv1 = findViewById(R.id.cropImageView);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(("*** btn1 ***"));

                imageUri = null;
                CropImageOptions cropImageOptions = new CropImageOptions();
                cropImageOptions.imageSourceIncludeGallery = true;
                cropImageOptions.imageSourceIncludeCamera = true;
                cropImageOptions.guidelines = CropImageView.Guidelines.ON;
                CropImageContractOptions cropImageContractOptions = new CropImageContractOptions(imageUri, cropImageOptions);
                arl.launch(cropImageContractOptions);

            }
        });

        // launcher
        arl = registerForActivityResult(new CropImageContract(), result -> {
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (result != null) {

                // Use the returned uri.
                Uri uriContent = result.getUriContent();
                String uriFilePath = result.getUriFilePath(getApplicationContext(), false);
                //Bitmap cropped = BitmapFactory.decodeFile(uri.getUriFilePath(getApplicationContext(), true));
                iv1.setImageUriAsync(uriContent);

            } else {
                System.out.println("*** error ***");
            }
        });

    }

    /**
     * section for options menu
     */
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

 */
}