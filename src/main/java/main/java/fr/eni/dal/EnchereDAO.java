package main.java.fr.eni.dal;

import java.time.LocalDate;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Enchere;

public interface EnchereDAO {

	void insertEnchere (Enchere enchere) throws BLLException;
	
	void updateEnchere (Enchere enchere) throws BLLException;
	
	void removeEnchere (Enchere enchere, LocalDate dateEnchere)throws BLLException;
	
	
}
