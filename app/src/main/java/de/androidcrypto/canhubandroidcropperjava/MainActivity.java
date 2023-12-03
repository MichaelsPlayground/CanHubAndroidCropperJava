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
    private CropImageView iv1;
    //private ImageView imageView2;
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
        //imageView2 = findViewById(R.id.croppedImageView);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(("*** btn1 ***"));

                imageUri = null;

                // see for options: https://github.com/CanHub/Android-Image-Cropper/blob/main/cropper/src/main/kotlin/com/canhub/cropper/CropImageView.kt
                CropImageOptions cropImageOptions = new CropImageOptions();
                cropImageOptions.imageSourceIncludeGallery = true;
                cropImageOptions.imageSourceIncludeCamera = false;
                cropImageOptions.cropperLabelText = "hello";
                cropImageOptions.intentChooserTitle = "chooserTitle";
                cropImageOptions.activityTitle = "Activity title";

                cropImageOptions.fixAspectRatio = true;
                cropImageOptions.aspectRatioX = 1;
                cropImageOptions.aspectRatioY = 1;

                //cropImageOptions.guidelines = CropImageView.Guidelines.ON;
                cropImageOptions.guidelines = CropImageView.Guidelines.OFF;
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

                //Bitmap cropped = BitmapFactory.decodeFile(result.getUriFilePath(getApplicationContext(), true));
                //imageView2.setImageBitmap(cropped);

/*
                //iv2.setImageURI(uriContent);
                System.out.println("uriContent: " + uriContent);
                System.out.println("uriFilePath: " + uriFilePath);
*/
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