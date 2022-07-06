package main.java.fr.eni.bo;

import java.time.LocalDate;

public class Enchere {

	private int noEnchere;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private int noEncherisseur;
	private int noUtilisateur;
	private int noArticle;

	public Enchere(LocalDate dateEnchere, int montantEnchere, int noArticle, int noUtilisateur, int noEncherisseur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noEncherisseur = noEncherisseur;
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
	}

	public Enchere(int noEnchere, LocalDate dateEnchere, int montantEnchere,int noArticle, int noUtilisateur, int noEncherisseur) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noEncherisseur = noEncherisseur;
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
	}

	public Enchere() {
		super();
	}


	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}

	public int getNoEnchere() {
		return noEnchere;
	}
	
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	public int getNoEncherisseur() {
		return noEncherisseur;
	} 

	public void setNoEncherisseur(int noEncherisseur) {
		this.noEncherisseur = noEncherisseur;
	}

	
	
	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere
				+ ", noEncherisseur=" + noEncherisseur + ", noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle
				+ "]";
	}


	
}
