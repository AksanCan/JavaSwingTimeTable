package TimeTableStrategy;

import javax.swing.JOptionPane;

import resources.Resources;

public class DefaultGenerator extends GenerateTimeTable{
	
	private static final String MESSAGE = "Die von Ihnen gewünschte Funktion steht momentan nicht zur Verfügung. Bitte entscheiden Sie sich für ein anderes Fileformat";
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
