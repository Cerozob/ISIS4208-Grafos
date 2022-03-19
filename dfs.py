
from queue import Queue
import copy


def bfs(graph, source: int):
    # asumir una matriz de adyacencia, si no hay un eje entre i y j se asume que graph[i][j] es None
    queue = Queue()
    visited = [False]*len(graph)
    visited[source] = True
    print(f"visitando vertice {current}")
    queue.put(source)
    while not queue.empty():
        current = queue.get()
        for adj in graph[current]:
            if adj is not None:
                visited[adj] = True
                print(f"visitando vertice {current}")
                queue.put(adj)


def dfs(graph, source: int):
    # asumir una matriz de adyacencia, si no hay un eje entre i y j se asume que graph[i][j] es None
    stack = []
    visited = [False]*len(graph)
    stack.append(source)
    while len(stack) != 0:
        current = stack.pop()
        print(f"visitando vertice {current}")
        if not visited[current]:
            visited[current] = True
            for adj in graph[current]:
                if adj is not None:
                    stack.append(adj)


def floyd_warshall(graph):
    # asumir una matriz de adyacencia, si no hay un eje entre i y j se asume que graph[i][j] es None
    distancia = copy.deepcopy(graph)
    caminos = [[None for x in range(len(graph))] for y in range(len(graph))]
    i, j = 0, 0
    while i < len(graph):
        while j < len(graph[i]):
            if i == j:
                distancia[i][j] = 0
            elif graph[i][j] is None:
                distancia[i][j] = float('inf')
            else:
                distancia[i][j] = graph[i][j]
            j += 1
        i += 1
    i, j, k = 0, 0, 0
    while k < len(graph):
        while i < len(graph):
            while j < len(graph):
                newdistance = (distancia[i][k]+distancia[k][j])
                if distancia[i][j] > newdistance:
                    distancia[i][j] = newdistance
                    caminos[i][j] = caminos[i][k]
                if distancia[i][i] < 0:
                    print("ciclo negativo, saliendo")
                    return None
                j += 1
            i += 1
        k += 1
    return distancia, caminos


def floyd_warshall_camino(caminos, source: int, destination: int):
    camino = []
    if caminos[source][destination] is None:
        return None
    while source != destination:
        source = caminos[source][destination]
        camino.append(source)
    return camino

def bellman_ford(graph, source):
    distance = [float('inf')]*len(graph)
    predecessor = [None]*len(graph)
    distance[source] = 0
    counter = 0
    while counter < len(graph)-1:
        changed = False
        i, j = 0, 0  # matriz de adyacencia
        for i in range(len(graph)):
            for j in range(len(graph)):
                if graph[i][j] is not None:
                    newdistance = distance[i] + graph[i][j]
                    if newdistance < distance[j]:
                        changed = True
                        distance[j] = newdistance
                        predecessor[j] = i
        if not changed:
            return distance, predecessor
        counter += 1

    i, j = 0, 0  # matriz de adyacencia
    for i in range(len(graph)):
        for j in range(len(graph)):
            if graph[i][j] is not None:
                newdistance = distance[i] + graph[i][j]
                if newdistance < distance[j]:
                    print("ciclo negativo, saliendo")
                    return None
    return distance, predecessor