package main.java.fr.eni.bll;

import main.java.fr.eni.bo.Retraits;
import main.java.fr.eni.dal.DAOFactory;
import main.java.fr.eni.dal.RetraitsDAO;

public class ManagerRetraitsImpl implements  ManagerRetraits{

    RetraitsDAO retraitsDAO;

    public ManagerRetraitsImpl(){
        retraitsDAO  = DAOFactory.getDaoRetraits();
    }

    public  void insertRetraits(Retraits retraits){
        retraitsDAO.insert(retraits);
    }

    public Retraits selectById(int it){
        Retraits retraits = retraitsDAO.selectById(it);
        return retraits;
    }

    public Retraits updateRetraits(Retraits retraits){
        Retraits retraits1 = retraitsDAO.updateRetraits(retraits);
        return retraits1;
    }
}
