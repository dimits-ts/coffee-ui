package com.auebds.coffeui.ui.schedule.create;

import androidx.fragment.app.Fragment;

/**
 * An interface used to execute a fragment-defined action on switching fragments.
 *
 * @author Dimitris Tsirmpas
 */
abstract class SwitchableFragment extends Fragment {

    public SwitchableFragment() {
        super();
    }

    /**
     * Instruct the fragment that it is about to be switched, and activate its intended action.
     */
    public abstract void onSwitch();
}