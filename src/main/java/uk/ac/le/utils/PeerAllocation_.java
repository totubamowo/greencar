package uk.ac.le.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.*;

public class PeerAllocation_ {
	private static double ref = 9.99;

	private static int n = 5; // number of riders
	private static int V = 2 * n + 2;

	private static EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);

	private static int s = 0;
	private static int t = V - 1;

	private static List<Integer> riders;

	private static List<Integer> subRiders;

	public static void main(String[] args) {
        StdOut.printf("refCost = %.2f", ref);
        StdOut.println();
        buildGraph();

		for (List<Integer> list : (PowerSet.powerSet(riders))) {
			subRiders = list;
			reset();

			if (subRiders.size() > 1) {
				for (Integer i : subRiders)
					activated[i] = true;
				getPath(s);
				if (visited[t]) {
					StdOut.println(subRiders);
					int current = s;
					do {
						int next = nextVertex[current];
						StdOut.printf("%d->%d (%.2f)  ", current, next,
								distTo[next]);
						current = next;
					} while (current != t);
					StdOut.println();
				}
			}
		}
	}

	public static void getPath(int v) {
		visited[v] = true;
		activated[t] = activateSink();
		IndexMinPQ<Double> minpq = new IndexMinPQ<Double>(G.V());
		for (DirectedEdge e : G.adj(v)) {
			if (activated[e.to()] & !visited[e.to()])
				minpq.insert(e.to(), e.weight());
		}
		if (!minpq.isEmpty()) {
			int w = minpq.minIndex();
			double cost = minpq.minKey();
			if (w <= n)
				activated[w + n] = true;
			nextVertex[v] = w;
			distTo[w] = distTo[v] + cost;
			if (distTo[w] < ref)
				getPath(w);
		}
	}

	public static boolean activateSink() {
		for (Integer si : subRiders) {
			if (!activated[si + n])
				return false;
		}
		return true;
	}

	public static void reset() {
		activated = new boolean[G.V()];
		activated[s] = true;

		visited = new boolean[G.V()];

		distTo = new double[G.V()];
		nextVertex = new int[G.V()];

		distTo[s] = 0.0;
	}

	// activated[v] = true if v has been picked up
	private static boolean[] activated;

	// distTo[v] = distance of shortest s->v path
	private static double[] distTo;

	// edgeTo[v] = last edge on path from s to v wrong
	private static int[] nextVertex;

	// visited[v] = true if v has been added to path
	private static boolean[] visited;

	public static void buildGraph() {
		riders = new ArrayList<Integer>();
		for (int si = 1; si <= n; si++) {
			int ti = n + si;
			// add start and end edges without check
			// they've passed the test when building the graph.
			addEdge_(G, ref, s, si);
			addEdge_(G, ref, ti, t);
			addEdge_(G, ref, si, ti);

			for (int sj = 1; sj <= n; sj++) {
				int tj = n + sj;
				if (sj != si) {
					addEdge(G, ref, si, sj);
					addEdge(G, ref, ti, sj);
				}
				if (tj != ti) {
					addEdge(G, ref, ti, tj);
					addEdge(G, ref, si, tj);
				}
			}
			riders.add(si);
		}

		StdOut.println(G);
	}

    public static double h(int v, int w) {
        Random rand = new Random();
        double x = v * w * 0.1 * rand.nextInt(9);
        return x == 0 ? 0.1 : x;

    }

    public static void addEdge_(EdgeWeightedDigraph G, double ref, int v, int w) {
        double hvw = h(v, w);
        hvw = hvw > ref ? (int) hvw % (int) ref : hvw;
        G.addEdge(new DirectedEdge(v, w, hvw));
    }

    public static void addEdge(EdgeWeightedDigraph G, double ref, int v, int w) {
        double hvw = h(v, w);
        if (hvw < ref)
            G.addEdge(new DirectedEdge(v, w, hvw));
    }
}