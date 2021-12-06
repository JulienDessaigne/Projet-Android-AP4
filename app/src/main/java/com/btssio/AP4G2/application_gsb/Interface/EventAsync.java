package com.btssio.AP4G2.application_gsb.Interface;

import java.util.ArrayList;

/**
 * Created by veschembes on 06/12/2021.
 */

// Force la classe qui l'implemente a avoir les methodes indiquees ci-dessous
// Cette methode sera appelee lorsque la tache asynchrone sera terminee
public interface EventAsync<T> {
    void onTacheTerminee(String resultat);
    void onTacheTerminee(ArrayList<T> resultat);
    void onTacheTerminee(T resultat);
    void onErreur(String message);
}
