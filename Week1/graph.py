import matplotlib.pyplot as plt
import numpy as np

bdata_x, bdata_y = np.loadtxt("data/benchmarks_bubblesort.txt")
hdata_x, hdata_y = np.loadtxt("data/benchmarks_heapsort.txt")
qdata_x, qdata_y = np.loadtxt("data/benchmarks_quicksort.txt")

fig, ax = plt.subplots()

ax.scatter(bdata_x, bdata_y, label="bubblesort")
ax.scatter(hdata_x, hdata_y, label="heapsort")
ax.scatter(qdata_x, qdata_y, label="quicksort")

ax.legend()
ax.grid(True)

plt.show()
