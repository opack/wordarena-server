package com.slamdunk.wordarena.server.commands;

import com.slamdunk.wordarena.server.commands.lexis.AddWordExecutor;
import com.slamdunk.wordarena.server.commands.lexis.RemoveWordExecutor;
import com.slamdunk.wordarena.server.commands.lexis.ValidateWordExecutor;

public enum Commands {
	/**
	 * Ajouter un mot dans un lexique (fonctionnalité admin)
	 */
	LEXIS_ADD {
		@Override
		public boolean isAdmin() {
			return true;
		}

		@Override
		public CommandExecutor createExecutor() {
			return new AddWordExecutor();
		}
	},
	
	/**
	 * Retirer un mot dans un lexique (fonctionnalité admin)
	 */
	LEXIS_REMOVE {
		@Override
		public boolean isAdmin() {
			return true;
		}

		@Override
		public CommandExecutor createExecutor() {
			return new RemoveWordExecutor();
		}
	},
	
	/**
	 * Vérifier la validité d'un mot
	 */
	LEXIS_VALIDATE {
		@Override
		public CommandExecutor createExecutor() {
			return new ValidateWordExecutor();
		}
	};
	
	/**
	 * Indique si l'accès à cette commande n'est autorisée que pour l'admin
	 * @return
	 */
	public boolean isAdmin() {
		return false;
	}
	
	/**
	 * Méthode qui crée un objet chargé d'exécuté la commande
	 * @return
	 */
	public abstract CommandExecutor createExecutor();
}