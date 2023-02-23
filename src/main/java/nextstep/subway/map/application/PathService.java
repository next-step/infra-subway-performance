package nextstep.subway.map.application;

import java.util.ArrayList;
import nextstep.subway.line.domain.Line;
import nextstep.subway.map.domain.SectionEdge;
import nextstep.subway.map.domain.SubwayGraph;
import nextstep.subway.map.domain.SubwayPath;
import nextstep.subway.station.domain.Station;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PathService {
    public SubwayPath findPath(List<Line> lines, Station source, Station target) {
        SubwayGraph graph = new SubwayGraph(SectionEdge.class);
        graph.addVertexWith(lines);
        graph.addEdge(lines);

        // 다익스트라 최단 경로 찾기
        try {
            DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
            GraphPath<Station, SectionEdge> path = dijkstraShortestPath.getPath(source, target);

            return convertSubwayPath(path);
        } catch (IllegalArgumentException ex) {
            //비즈니스 로직 에러 빈리스트 반환 처리
            return new SubwayPath(new ArrayList<>(), new ArrayList<>());
        }

    }

    private SubwayPath convertSubwayPath(GraphPath graphPath) {
        List<SectionEdge> edges = (List<SectionEdge>) graphPath.getEdgeList().stream().collect(Collectors.toList());
        List<Station> stations = graphPath.getVertexList();
        return new SubwayPath(edges, stations);
    }
}
