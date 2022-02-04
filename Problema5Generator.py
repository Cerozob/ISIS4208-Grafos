
from random import randint

ORDER = 10

with open("Problema5.in", "w+") as f:
    n = randint(1, ORDER)
    nEdges = randint(1, n * (n - 1) // 2)
    f.write(f"{n}\n")
    for i in range(1, nEdges):
        v1 = randint(0, n-1)
        v2 = randint(0, n-1)
        cost = randint(-ORDER, ORDER)
        f.write(f"{v1} {v2} {cost}\n")
    f.write("0")
