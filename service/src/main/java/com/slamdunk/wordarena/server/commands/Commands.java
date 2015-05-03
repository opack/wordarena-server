package com.slamdunk.wordarena.server.commands;

import com.slamdunk.wordarena.server.commands.lexis.AddWordExecutor;
import com.slamdunk.wordarena.server.commands.lexis.LoadLexisExecutor;
import com.slamdunk.wordarena.server.commands.lexis.RemoveWordExecutor;
import com.slamdunk.wordarena.server.commands.lexis.ValidateWordExecutor;
import com.slamdunk.wordarena.server.commands.server.AllowExternalClientsExecutor;
import com.slamdunk.wordarena.server.commands.server.GetStatusExecutor;
import com.slamdunk.wordarena.server.commands.server.RefuseExternalClientsExecutor;
import com.slamdunk.wordarena.server.commands.server.ShutdownExecutor;

public enum Commands {
	/**
	 * Charge le lexique de la langue indiquée (fonctionnalité admin)
	 */
	LEXIS_LOAD {
		@Override
		public boolean isAdmin() {
			return true;
		}

		@Override
		public CommandExecutor createExecutor() {
			return new LoadLexisExecutor();
		}
	},
	
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
	},
	
	/**
	 * Retourne le status du serveur
	 */
	SERVER_STATUS {
		@Override
		public boolean isAdmin() {
			return true;
		}

		@Override
		public CommandExecutor createExecutor() {
			return new GetStatusExecutor();
		}
	},

	/**
	 * Autorise les clients externes (non-localhost) à se connecter au serveur (fonctionnalité admin)
	 */
	SERVER_START {
		@Override
		public boolean isAdmin() {
			return true;
		}

		@Override
		public CommandExecutor createExecutor() {
			return new AllowExternalClientsExecutor();
		}
	},
	
	/**
	 * Interdit aux clients externes (non-localhost) de se connecter au serveur (fonctionnalité admin)
	 */
	SERVER_STOP {
		@Override
		public boolean isAdmin() {
			return true;
		}

		@Override
		public CommandExecutor createExecutor() {
			return new RefuseExternalClientsExecutor();
		}
	},
	
	/**
	 * Eteint le serveur (cesse d'écouter et termine le programme) (fonctionnalité admin)
	 */
	SERVER_SHUTDOWN {
		@Override
		public boolean isAdmin() {
			return true;
		}

		@Override
		public CommandExecutor createExecutor() {
			return new ShutdownExecutor();
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