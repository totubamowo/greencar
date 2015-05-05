package uk.ac.le.utils;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.le.model.Journey;
import uk.ac.le.model.RiderJourney;
import uk.ac.le.service.JourneyManager;
import uk.ac.le.service.RouteManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PeerAllocation {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeerAllocation.class);

    @Autowired
    private JourneyManager journeyManager;

    @Autowired
    private RouteManager routeManager;

    public Allocation allocateRiders(Journey driverJourney) {

        // create an instance of ComputeAllocation for every request to guarantee 'thread safety'
        ComputeAllocation computeAllocation = new ComputeAllocation(driverJourney);

        return computeAllocation.getAllocation();
    }

    private class ComputeAllocation {

        private Journey driverJourney;

        public ComputeAllocation(Journey driverJourney) {
            this.driverJourney = driverJourney;
            findRiders();
            buildGraph();
            computeCombos();
        }

        // result of computation
        private Allocation allocation = new Allocation();

        public Allocation getAllocation() {
            return allocation;
        }

        // reference cost = 4/3*h(s->t)
        private double refCost;

        // convenient list for holding the source, sink and shortest path cost of a rider's journey
        private List<RiderJourney> riderJourneys = new ArrayList<RiderJourney>();

        // finds riders that are individually suitable for peering
        private void findRiders() {
            refCost = 4 / 3 * getCost(driverJourney.getSource(), driverJourney.getSink());

            // get all opposing journeys from other users i.e if journey relates is a driver journey, get rider journeys.
            List<Journey> journeys = journeyManager.getAll(driverJourney);

            // add elements of journeys to riderJourneys using the same index
            for (Journey journey_ : journeys) {
                RiderJourney riderJourney = new RiderJourney(journey_);
                riderJourney.setCost(getCost(riderJourney.getSource(), riderJourney.getSink()));
                riderJourney.setSourceToPickup(getCost(driverJourney.getSource(), riderJourney.getSource()));
                riderJourney.setDropToSink(getCost(riderJourney.getSink(), driverJourney.getSink()));
                riderJourneys.add(journeys.indexOf(journey_), riderJourney);
            }

            for (Iterator<RiderJourney> iterator = riderJourneys.iterator(); iterator.hasNext(); ) {
                RiderJourney riderJourney = iterator.next();
                int index = riderJourneys.indexOf(riderJourney);
                if (riderJourney.getCost() >= refCost) {
                    iterator.remove();// eliminate journey if the cost of journey shortest path is greater than reference cost.
                    journeys.remove(index);
                } else if (riderJourney.getSourceToPickup() >= refCost) {
                    iterator.remove();// eliminate journey if the cost of source-pickup shortest path is greater than reference cost.
                    journeys.remove(index);
                } else if (riderJourney.getDropToSink() >= refCost) {
                    iterator.remove();// eliminate journey if the cost of drop-sink shortest path is greater than reference cost.
                    journeys.remove(index);
                } else if ((riderJourney.getCost() + riderJourney.getSourceToPickup() + riderJourney.getDropToSink()) >= refCost) {
                    iterator.remove();// eliminate journeys if total cost of peering is greater than reference cost.
                    journeys.remove(index);
                }
            }

            // add journeys that can be individually peered with driver to result
            allocation.setJourneys(journeys);
        }

        // number of riders, driver source and sink
        private int n, s, t;

        // graph of relevant paths between driver and riders that are less than reference cost
        private EdgeWeightedDigraph G;

        // computes the combination of riders suitable for peering
        private List<Integer> riders;

        // builds the graph used to find rider combinations that can be peered with driver
        private void buildGraph() {
            n = riderJourneys.size();
            G = new EdgeWeightedDigraph(2 * n + 2);
            s = 0;
            t = G.V() - 1;

            riders = new ArrayList<Integer>();

            // add pickup-drop, source-pickup and drop-sink edges
            for (int si = 1; si <= n; si++) {
                riders.add(si);
                int ti = si + n;
                G.addEdge(new DirectedEdge(si, ti, riderJourneys.get(si - 1).getCost()));
                G.addEdge(new DirectedEdge(s, si, riderJourneys.get(si - 1).getSourceToPickup()));
                G.addEdge(new DirectedEdge(ti, t, riderJourneys.get(si - 1).getDropToSink()));

                for (int sj = 1; sj <= n; sj++) {
                    int tj = sj + n;
                    if (sj != si) {
                        G.addEdge(new DirectedEdge(si, sj, getCost(riderJourneys.get(si - 1).getSource(), riderJourneys.get(sj - 1).getSource())));
                        G.addEdge(new DirectedEdge(ti, sj, getCost(riderJourneys.get(si - 1).getSink(), riderJourneys.get(sj - 1).getSource())));
                    }
                    if (tj != ti) {
                        G.addEdge(new DirectedEdge(ti, tj, getCost(riderJourneys.get(si - 1).getSink(), riderJourneys.get(sj - 1).getSink())));
                        G.addEdge(new DirectedEdge(si, tj, getCost(riderJourneys.get(si - 1).getSource(), riderJourneys.get(sj - 1).getSink())));
                    }
                }
            }
        }

        List<Integer> subRiders;

        // computes the combination of riders suitable for peering
        private void computeCombos() {
            for (List<Integer> list : (PowerSet.powerSet(riders))) {
                subRiders = list;
                // reset variables used in computing path for a subset of riders
                reset();

                // interested only in a combination of two or more riders
                if (list.size() > 1) {
                    for (Integer i : list)
                        activated[i] = true;
                    getPath(s);
                    // add result if there is a path to driver's sink
                    if (visited[t]) {
                        Combo combo = new Combo();
                        combo.setSubRiders(list);
                        int current = nextVertex[s];
                        while (nextVertex[current] != Integer.MIN_VALUE) {
                            int idx = current - 1;
                            combo.getDistTo().add(distTo[current]);
                            combo.getNextTo().add(current <= n ? riderJourneys.get(idx).getSource() : riderJourneys.get(idx - n).getSink());
                            current = nextVertex[current];
                        }
                        allocation.getCombos().add(combo);
                    }
                }
            }
        }

        // activated[v] = true if v has been picked up
        private boolean[] activated;

        // distTo[v] = distance of shortest s->v path
        private double[] distTo;

        // nextVertex[v] = next vertex on path from v on path s to t
        private int[] nextVertex;

        // visited[v] = true if v has been added to path
        private boolean[] visited;

        private void getPath(int v) {
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
                if (w <= n)
                    activated[w + n] = true;
                nextVertex[v] = w;
                distTo[w] = distTo[v] + cost;
                if (distTo[w] < refCost)
                    getPath(w);
            }
        }

        // check if all riders have been dropped
        private boolean activateSink() {
            for (Integer si : subRiders) {
                if (!activated[si + n])
                    return false;
            }
            return true;
        }

        // reset parameters for use each relevant subset of riders
        private void reset() {
            activated = new boolean[G.V()];
            activated[s] = true;

            distTo = new double[G.V()];
            distTo[s] = 0.0;

            nextVertex = new int[G.V()];
            nextVertex[t] = Integer.MIN_VALUE;

            visited = new boolean[G.V()];
        }
    }

    private double getCost(String source, String sink) {
        try {
            Point source_ = GeoToPostcode.postcodeToGeo(source);
            Point sink_ = GeoToPostcode.postcodeToGeo(sink);
            return routeManager.getCost(source_, sink_);
        } catch (Exception ex) {
            return 0.00;
        }
    }
}