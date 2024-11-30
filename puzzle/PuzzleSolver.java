package puzzle;

import puzzle.Summary;
import puzzle.Puzzle;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class PuzzleSolver {
	// greedy: nur heuristic
	public static Summary greedy(Puzzle puzzle, Heuristic heuristic, boolean detectDouble, int maxDepth, int limit) {		
		PriorityQueue<Puzzle> queue = new PriorityQueue<>((p1,p2) -> {
			int h1 = (heuristic == Heuristic.WRONG_TILES) ? p1.countWrongTiles() : p1.manhattanDist();
			int h2 = (heuristic == Heuristic.WRONG_TILES) ? p2.countWrongTiles() : p2.manhattanDist();
			return Integer.compare(h1, h2);
		});

		Set<Puzzle> visited = detectDouble ? new HashSet<>() : null;

		// Summary initialisieren
		Summary summary = new Summary();
		summary.startState = puzzle;
		summary.algorithm = "Greedy";
		summary.heuristic = heuristic;
		summary.detectDouble = detectDouble;
		summary.maxDepthPermitted = maxDepth;
		summary.limit = limit;

		queue.add(puzzle);
		int numExpansions = 0;
		int maxQueueSize = 0;
		int maxDepthReached = 0;

		Puzzle current = null;
		while(!queue.isEmpty()){
			maxQueueSize = Math.max(maxQueueSize, queue.size());

			// Überprüfen, ob die Limitierung erreicht wurde
			if (limit > 0 && numExpansions >= limit) {
				break;
			}

			// Aktuellen Zustand aus der Queue entfernen
			current = queue.poll();

			// Prüfen, ob das Ziel erreicht ist
			if (current.equals(Puzzle.goal)) {
				summary.isSolution = true;
				summary.finalState = current;
				summary.pathLength = current.path.length();
				summary.path = current.path;
				break;
			}

			// Zustand als besucht markieren
			if (detectDouble && !visited.add(current)) {
				continue; // Überspringen, wenn der Zustand bereits besucht wurde
			}

			// Nachbarzustände erweitern (sofern in erlaubter Tiefe)
			int currentDepth = current.path.length();
			maxDepthReached = Math.max(maxDepthReached, currentDepth);
	
			// Expand
			numExpansions++;
			if (maxDepth == 0 || currentDepth < maxDepth) {
				if (current.canMoveLeft()) {
					Puzzle left = current.moveLeft();
					if (!detectDouble || !visited.contains(left)) {
						queue.add(left);
					}
				}
				if (current.canMoveRight()) {
					Puzzle right = current.moveRight();
					if (!detectDouble || !visited.contains(right)) {
						queue.add(right);
					}
				}
				if (current.canMoveUp()) {
					Puzzle up = current.moveUp();
					if (!detectDouble || !visited.contains(up)) {
						queue.add(up);
					}
				}
				if (current.canMoveDown()) {
					Puzzle down = current.moveDown();
					if (!detectDouble || !visited.contains(down)) {
						queue.add(down);
					}
				}
			}
		}

		if(!summary.isSolution){
			summary.finalState = current;
			summary.pathLength = -1;
			summary.path = null;
		}

		summary.numExpansions = numExpansions;
		summary.maxDepthReached = maxDepthReached;
		summary.queueSize = queue.size();
		summary.maxQueueSize = maxQueueSize;

		return summary;
	}

	// A*: current cost + heuristic
	public static Summary AStar(Puzzle puzzle, Heuristic heuristic, boolean detectDouble, int maxDepth, int limit) {		
		PriorityQueue<Puzzle> queue = new PriorityQueue<>((p1,p2) -> {
			int h1 = (heuristic == Heuristic.WRONG_TILES) ? (p1.path.length() + p1.countWrongTiles()) : (p1.path.length() + p1.manhattanDist());
			int h2 = (heuristic == Heuristic.WRONG_TILES) ? (p2.path.length() + p2.countWrongTiles()) : (p2.path.length() + p2.manhattanDist());
			return Integer.compare(h1, h2);
		});

		Set<Puzzle> visited = detectDouble ? new HashSet<>() : null;

		// Summary initialisieren
		Summary summary = new Summary();
		summary.startState = puzzle;
		summary.algorithm = "Greedy";
		summary.heuristic = heuristic;
		summary.detectDouble = detectDouble;
		summary.maxDepthPermitted = maxDepth;
		summary.limit = limit;

		queue.add(puzzle);
		int numExpansions = 0;
		int maxQueueSize = 0;
		int maxDepthReached = 0;

		Puzzle current = null;
		while(!queue.isEmpty()){
			maxQueueSize = Math.max(maxQueueSize, queue.size());

			// Überprüfen, ob die Limitierung erreicht wurde
			if (limit > 0 && numExpansions >= limit) {
				break;
			}

			// Aktuellen Zustand aus der Queue entfernen
			current = queue.poll();

			// Prüfen, ob das Ziel erreicht ist
			if (current.equals(Puzzle.goal)) {
				summary.isSolution = true;
				summary.finalState = current;
				summary.pathLength = current.path.length();
				summary.path = current.path;
				break;
			}

			// Zustand als besucht markieren
			if (detectDouble && !visited.add(current)) {
				continue; // Überspringen, wenn der Zustand bereits besucht wurde
			}

			// Nachbarzustände erweitern (sofern in erlaubter Tiefe)
			int currentDepth = current.path.length();
			maxDepthReached = Math.max(maxDepthReached, currentDepth);
	
			// Expand
			numExpansions++;
			if (maxDepth == 0 || currentDepth < maxDepth) {
				if (current.canMoveLeft()) {
					Puzzle left = current.moveLeft();
					if (!detectDouble || !visited.contains(left)) {
						queue.add(left);
					}
				}
				if (current.canMoveRight()) {
					Puzzle right = current.moveRight();
					if (!detectDouble || !visited.contains(right)) {
						queue.add(right);
					}
				}
				if (current.canMoveUp()) {
					Puzzle up = current.moveUp();
					if (!detectDouble || !visited.contains(up)) {
						queue.add(up);
					}
				}
				if (current.canMoveDown()) {
					Puzzle down = current.moveDown();
					if (!detectDouble || !visited.contains(down)) {
						queue.add(down);
					}
				}
			}
		}

		if(!summary.isSolution){
			summary.finalState = current;
			summary.pathLength = -1;
			summary.path = null;
		}

		summary.numExpansions = numExpansions;
		summary.maxDepthReached = maxDepthReached;
		summary.queueSize = queue.size();
		summary.maxQueueSize = maxQueueSize;

		return summary;
	}

	// hieran bitte nichts veraendern
	public enum Heuristic {
		WRONG_TILES,
		MANHATTAN
	}
}
