package MVC;

import maxPlus.Matrix;

/**
 * Die Klasse Controller stellt die konkrete Strategy der GUI dar, über die die
 * GUI nötige Funktionen durchführen kann.
 * 
 * @author Can
 *
 */
public class Controller implements Strategy {

	private Model[] model;
	private TimeTable timetable;

	/**
	 * Der Konstruktor erzeugt eine View-Instanz, der es als Parameter ein
	 * Model-Array und sich selbst übergibt.
	 * 
	 * @param model Übergabe von mehreren Model Objekten möglich.
	 * @author Can
	 *
	 */
	public Controller(Model... model) {
		this.model = model;
		this.timetable = new TimeTable(FileType.EXCEL, model);
		View view = new View(this, model);
	}

	@Override
	public void calculate(Matrix matrix, String algorithm_name) {
		// TODO Auto-generated method stub
		for (Model model : this.model) {
			if (model.getName().equals(algorithm_name)) {
				model.calculate(matrix);
				break;
			}
		}
	}

	@Override
	public void generateFile(FileType typ) {
		// TODO Auto-generated method stub
		timetable.setFileForm(typ);
		this.timetable.generateFile();
	}

	@Override
	public String[] getModelNames() {
		// TODO Auto-generated method stub
		String[] temp = new String[this.model.length];
		for (int i = 0; i < temp.length; i++)
			temp[i] = this.model[i].getName();
		return temp;
	}
}
