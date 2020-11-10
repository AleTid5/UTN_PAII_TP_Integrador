package src.Activities.Transporter;

import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentManager;

public abstract class ManageHistoryTransporter {
    private static View root;
    private static View currentContent;
    private static FragmentManager fragmentManager;
    private static FrameLayout frameLayout;

    public static void transport(View root, View currentContent, FragmentManager fragmentManager, FrameLayout frameLayout) {
        ManageHistoryTransporter.root = root;
        ManageHistoryTransporter.currentContent = currentContent;
        ManageHistoryTransporter.fragmentManager = fragmentManager;
        ManageHistoryTransporter.frameLayout = frameLayout;
    }

    public static View getRoot() {
        return root;
    }

    public static View getCurrentContent() {
        return currentContent;
    }

    public static FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public static FrameLayout getFrameLayout() {
        return frameLayout;
    }
}
