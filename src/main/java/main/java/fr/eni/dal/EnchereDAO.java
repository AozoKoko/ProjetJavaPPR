package main.java.fr.eni.dal;

import java.time.LocalDate;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Enchere;

public interface EnchereDAO {

	void insert (Enchere enchere) throws BLLException;
	
	void update (Enchere enchere, LocalDate dateEnchere) throws BLLException;
	
	void remove (Enchere enchere, LocalDate dateEnchere)throws BLLException;
	
	
}
