package components.WithImageFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.tp_cuatrimestral.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

public abstract class WithImageFragment extends Fragment {
    private Bitmap image = null;

    protected Bitmap getImage() {
        return this.image;
    }

    protected void openGallery(int req_code) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), req_code);
    }

    protected void loadImage(String src) {
        Executors.newFixedThreadPool(1).submit(() -> {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                this.image = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            Uri selectedImageUri = data.getData();
            this.image = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImageUri);
        } catch (IOException e) {
            // ToDo: Handle this error.
            e.printStackTrace();
        }
    }

    protected void showImage() {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(getContext());
        LayoutInflater imageInflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = imageInflater.inflate(R.layout.image_viewer, null);
        ImageView image = (ImageView) layout.findViewById(R.id.image_view);
        image.setImageDrawable(new BitmapDrawable(getResources(), this.getImage()));
        imageDialog.setView(layout);
        imageDialog.create();

        final AlertDialog alertDialog = imageDialog.show();

        ((FloatingActionButton) layout.findViewById(R.id.button_close)).setOnClickListener(view -> alertDialog.dismiss());
    }
}
