package TimeTableStrategy;

import javax.swing.JOptionPane;

import resources.Resources;

public class DefaultGenerator extends GenerateTimeTable{
	
	private static final String MESSAGE = "Die von Ihnen gew�nschte Funktion steht momentan nicht zur Verf�gung. Bitte entscheiden Sie sich f�r ein anderes Fileformat";
	private static final String CAPTION = "FEHLER!";

	public DefaultGenerator() {
		super("Gibt eine Fehlermeldung aus");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void generateFile() {
		// TODO Auto-generated method stub
			Resources.pop_up(MESSAGE, CAPTION);
	}

}
