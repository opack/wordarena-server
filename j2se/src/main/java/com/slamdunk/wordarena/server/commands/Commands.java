package com.slamdunk.wordarena.server.commands;

public enum Commands {
	/**
	 * Ajouter un mot dans un lexique (fonctionnalité admin)
	 */
	LEXIS_ADD {
		@Override
		public boolean isAdmin() {
			return true;
		}
	},
	
	/**
	 * Vérifier la validité d'un mot
	 */
	LEXIS_VALIDATE;
	
	/**
	 * Indique si l'accès à cette commande n'est autorisée que pour l'admin
	 * @return
	 */
	public boolean isAdmin() {
		return false;
	}
}