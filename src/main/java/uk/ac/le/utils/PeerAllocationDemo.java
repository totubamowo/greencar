package uk.ac.le.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.*;
import org.springframework.stereotype.Component;

@Component
public class PeerAllocationDemo {
    private double ref;

    private int n, V, s, t;

    private EdgeWeightedDigraph G;

    private static List<Integer> riders;

    private static List<Integer> subRiders;

    DecimalFormat df = new DecimalFormat("#.##");

    public String demoAlgorithm(double ref, int n) {
        this.ref = ref;
        this.n = n;

        V = 2 * n + 2;
        G = new EdgeWeightedDigraph(V);
        s = 0;
        t = V - 1;

        StringBuilder sb = new StringBuilder();
        sb.append("refCost = " + ref);
        sb.append("\n");

        buildGraph();
        sb.append(G.toString());
        sb.append("\n");

        for (List<Integer> list : (PowerSet.powerSet(riders))) {
            subRiders = list;
            reset();

            if (subRiders.size() > 1) {
                for (Integer i : subRiders)
                    activated[i] = true;
                getPath(s);
                if (visited[t]) {
                    sb.append(subRiders.toString());
                    sb.append("\n");
                    int current = s;
                    do {
                        int next = nextVertex[current];
                        sb.append(current + "->" + next + " (" + df.format(distTo[next]) + ") ");
                        current = next;
                    } while (current != t);
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    public void getPath(int v) {
        visited[v] = true;
        activated[t] = activateSink();
        IndexMinPQ<Double> minPQ = new IndexMinPQ<Double>(G.V());
        for (DirectedEdge e : G.adj(v)) {
            if (activated[e.to()] & !visited[e.to()])
                minPQ.insert(e.to(), e.weight());
        }
        if (!minPQ.isEmpty()) {
            int w = minPQ.minIndex();
            double cost = minPQ.minKey();
            if (1 <= w && w <= n)
                activated[w + n] = true;
            nextVertex[v] = w;
            distTo[w] = distTo[v] + cost;
            if (distTo[w] < ref)
                getPath(w);
        }
    }

    public boolean activateSink() {
        for (Integer si : subRiders) {
            if (!visited[si + n])
                return false;
        }
        return true;
    }

    public void reset() {
        activated = new boolean[G.V()];
        activated[s] = true;

        visited = new boolean[G.V()];

        distTo = new double[G.V()];
        nextVertex = new int[G.V()];

        distTo[s] = 0.0;
    }

    // activated[v] = true if v has been picked up
    private boolean[] activated;

    // distTo[v] = distance of shortest s->v path
    private double[] distTo;

    // nextVertex[v] = next vertex on path from v on path s to t
    private int[] nextVertex;

    // visited[v] = true if v has been added to path
    private boolean[] visited;

    public void buildGraph() {
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
    }

    public void buildGraph_() {
        riders = new ArrayList<Integer>();

        for (int si = 1; si <= n; si++) {
            int ti = n + si;
            G.addEdge(new DirectedEdge(s, si, 0));
            G.addEdge(new DirectedEdge(ti, t, 0));
            riders.add(si);
        }

        G.addEdge(new DirectedEdge(1, 2, 5));
        G.addEdge(new DirectedEdge(1, 3, 0.1));
        G.addEdge(new DirectedEdge(2, 1, 5));
        G.addEdge(new DirectedEdge(2, 3, 10));
        G.addEdge(new DirectedEdge(2, 4, 7));
        G.addEdge(new DirectedEdge(3, 2, 10));
        G.addEdge(new DirectedEdge(3, 4, 7));
        G.addEdge(new DirectedEdge(4, 3, 7));

        StdOut.println(G);
    }

    public double h(int v, int w) {
        Random rand = new Random();
        double x = v * w * 0.1 * rand.nextInt(9);
        return x == 0 ? 0.1 : x;

    }

    public void addEdge_(EdgeWeightedDigraph G, double ref, int v, int w) {
        double hvw = h(v, w);
        hvw = hvw > ref ? (int) hvw % (int) ref : hvw;
        G.addEdge(new DirectedEdge(v, w, hvw));
    }

    public void addEdge(EdgeWeightedDigraph G, double ref, int v, int w) {
        double hvw = h(v, w);
        if (hvw < ref)
            G.addEdge(new DirectedEdge(v, w, hvw));
    }
}