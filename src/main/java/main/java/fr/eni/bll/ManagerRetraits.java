package main.java.fr.eni.bll;

import main.java.fr.eni.bo.Retraits;

public interface ManagerRetraits {

    void insertRetraits(Retraits retraits);

    Retraits selectById(int it);

    Retraits updateRetraits(Retraits retraits);
}
