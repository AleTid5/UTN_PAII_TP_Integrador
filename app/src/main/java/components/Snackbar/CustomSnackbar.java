package components.Snackbar;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class CustomSnackbar {
    Snackbar snackbar;
    View snackBarView;

    public CustomSnackbar(View view, String message) {
        this.snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        this.snackbar.setDuration(4000);
        this.snackBarView = this.snackbar.getView();
        TextView textView = this.snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
    }

    public void success() {
        this.showSnackbar("#1ad982");
    }

    public void danger() {
        this.showSnackbar("#d91a60");
    }

    private void showSnackbar(String color) {
        this.snackBarView.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SCREEN);
        this.snackbar.show();
    }
}
