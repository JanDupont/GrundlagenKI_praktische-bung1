package puzzle;

import puzzle.Puzzle;

public class Main {

	// Angaben zu Ihrer Person:
	public static String nachname = "Dupont"; // Tragen Sie hier Ihren Nachnamen ein
	public static String vorname  = "Jan"; // Tragen Sie hier Ihren Vornamen ein

	// Falls Sie mit weiteren Personen zusammengarbeitet haben, tragen Sie hier bitte die Namen dieser Personen ein:
	public static String gruppe   = "";

	/* Vergessen Sie nicht, die nachfolgenden Behauptungen zu pruefen!
	 * Wahr:   qX = true;
	 * Falsch: qX = false;
	 * Dabei beziehen sich die ersten drei Behauptungen auf die angegebenen Algorithmen im Allgemeinen,
	 * die letzten beiden Behauptungen auf den vorliegenden Anwendungsfall "8-Puzzle".
	 */

	// Behauptung q1: "Greedy findet NICHT immer eine vorhandene Loesung, aber
	//                wenn eine Loesung gefunden wird, ist der Loesungsweg optimal."
	public static Boolean q1 = false; // zu beantworten mit true oder false

	// Behauptung q2: "Greedy findet immer eine Loesung."
	public static Boolean q2 = false; // zu beantworten mit true oder false

	// Behauptung q3: "Wenn A* einen Loesungsweg gefunden hat, ist dieser immer optimal."
	public static Boolean q3 = true; // zu beantworten mit true oder false

	// Behauptung q4: "Jedes Puzzle ist loesbar."
	public static Boolean q4 = true; // zu beantworten mit true oder false

	// Behauptung q5: "Die Methode countWrongTiles() in Puzzle.java ist KEINE zulaessige Heuristik."
	public static Boolean q5 = false; // zu beantworten mit true oder false


	// Hier ist Platz fuer Ihre Tests
	public static void main(String[] args) {
		// Puzzle example = new Puzzle(0, 1, 2, 3, 4, 5, 6, 7, 8);
		// Summary summary = PuzzleSolver.AStar(example, PuzzleSolver.Heuristic.MANHATTAN, false, 0, 0);
		// System.out.println(summary);

		Puzzle exampleInstant = new Puzzle(1,2,3,8,0,4,7,6,5);
		Summary summaryInstant = PuzzleSolver.AStar(exampleInstant, PuzzleSolver.Heuristic.MANHATTAN, false, 0, 0);
		System.out.println(summaryInstant);

		// DLUR
		Puzzle example_DLUR_greedy =  new Puzzle(1,2,3,7,0,4,6,8,5);
		Summary summary_DLUR_greedy = PuzzleSolver.greedy(example_DLUR_greedy, PuzzleSolver.Heuristic.MANHATTAN, false, 0, 0);
		System.out.println(summary_DLUR_greedy);
		Puzzle example_DLUR_AStar =  new Puzzle(1,2,3,7,0,4,6,8,5);
		Summary summary_DLUR_AStar = PuzzleSolver.AStar(example_DLUR_AStar, PuzzleSolver.Heuristic.MANHATTAN, false, 0, 0);
		System.out.println(summary_DLUR_AStar);

		Puzzle example3 =  new Puzzle(2,7,1,6,4,3,0,5,8);
		Summary summary3 = PuzzleSolver.AStar(example3, PuzzleSolver.Heuristic.MANHATTAN, false, 14, 0);
		System.out.println(summary3);
		Puzzle example4 =  new Puzzle(2,7,1,6,4,3,0,5,8);
		Summary summary4 = PuzzleSolver.greedy(example4, PuzzleSolver.Heuristic.MANHATTAN, false, 14, 0);
		System.out.println(summary4);
	}
}
