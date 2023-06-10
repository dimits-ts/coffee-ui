package com.auebds.coffeui.ui.schedule.create;

/**
 * An interface used to execute a fragment-defined action on switching fragments.
 *
 * @author Dimitris Tsirmpas
 */
interface Switchable {

    /**
     * Notify the fragment it's about to be replaced and execute the action associated with it.
     * @throws Exception an exception detailing an unspecified error to be shown to the user
     */
    void onSwitch() throws Exception;
}
